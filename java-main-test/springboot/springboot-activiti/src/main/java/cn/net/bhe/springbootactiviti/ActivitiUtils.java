package cn.net.bhe.springbootactiviti;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 主要涉及到的表有：
 * act_re_deployment   部署表，包含部署时的name属性等
 * act_re_procdef      流程定义表，包含流程定义id（可以用来启动流程）、bpmn文件中的流程key、bpmn文件路径等
 * 
 * act_ru_execution    流程实例表
 * act_ru_task         流程任务表，包含正在执行的任务信息
 * act_ru_variable     流程变量表
 * 
 * act_hi_actinst      历史活动信息表，可以在此表中查看截止到当前活动的流程链
 */
@Component
public class ActivitiUtils {
    
    @Autowired
    private ProcessEngine processEngine;
    
    /*
     * 用RepositoryService部署流程
     * 部署后可在act_re_procdef查看相关信息
     * 
     * 供参考：可以重复部署，每次部署都会产生一个新版本记录
     */
    public Deployment deploy(String classpathResource, String name) {
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment() 
                .name(name) // 部署名称
                .addClasspathResource(classpathResource) // bpmn文件路径
                .deploy();
        return deployment;
    }
    
    /*
     * 使用RuntimeService启动流程
     */
    public ProcessInstance startProcessInstance(String processDefinitionKey, String processDefinitionId, Map<String, Object> vars) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = null;
        
        // 根据流程key启动，key就是bpmn文件中定义的id
        // 如果key对应的流程有多个版本，启动的将会是最新版本的流程
        if (processDefinitionKey != null && processDefinitionKey.length() > 0) {
            processInstance = runtimeService.startProcessInstanceByKey(
                    processDefinitionKey, 
                    // 流程变量会自动注入到bpmn文件中定义的表达式中
                    // 例第一个节点assignee = ${assignee}，该节点激活后assignee被替换为流程变量中存储的assignee值
                    vars);
        } 
        // 根据流程定义id启动
        // 供参考：流程定义id = 流程key_版本号_uuid
        else if (processDefinitionId != null && processDefinitionId.length() > 0) {
            processInstance = runtimeService.startProcessInstanceById(processDefinitionId, vars);
        }
        return processInstance;
    }
    
    /*
     * 使用RuntimeService查询processInstance
     * 流程结束后，act_ru_execution对应的记录被移除，processInstance查询为空
     */
    public ProcessInstance processInstanceQuery(String processInstanceId) {
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceQuery() 
                .processInstanceId(processInstanceId)
                .singleResult();
        return processInstance;
    }
    
    /*
     * 使用TaskService查询task
     * 数据源于act_ru_task，表中存储的是等待执行链的第一个任务
     */
    public List<Task> taskQuery(String assignee) {
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService
                .createTaskQuery()
                // 查询条件
                .taskAssignee(assignee)
//                .taskCandidateUser(candidateUser) 
//                .processDefinitionId("myProcess_1:1:15150967-0e1e-11eb-b36e-005056c00001") 
//                .processInstanceId(processInstanceId) 
//                .executionId(executionId) 
                // 排序
                .orderByTaskCreateTime().asc() // 创建时间升序排列
                // 返回结果集
//                .singleResult() // 返回惟一结果集
//                .count() // 返回结果集的数量
//                .listPage(firstResult, maxResults); // 分页查询
                .list();// 返回列表
        return list;
    }
    
    /*
     * 使用TaskService执行task
     * 某个任务执行之后，act_ru_task对应的记录被移除，并插入对应的下一个任务
     */
    public void complete(String taskId, Map<String, Object> vars) {
        TaskService taskService = processEngine.getTaskService();
        for (Entry<String, Object> entry : vars.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            taskService.setVariable(taskId, name, value);
        }
        taskService.complete(taskId);
    }
    
    /*
     * 使用HistoryService查询historicTaskInstance
     */
    public List<HistoricTaskInstance> historicTaskInstanceQuery() {
        List<HistoricTaskInstance> list = processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .list();
        return list;
    }
    
}
