package cn.net.bhe.springbootactiviti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiTest {

    @Autowired
    private ProcessEngine processEngine;

    /*
     * 用RepositoryService部署流程
     * 部署后可在act_re_procdef查看相关信息
     * 
     * 供参考：可以重复部署，每次部署都会产生一个新版本记录
     */
    @Test
    public void deploy() {
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment() 
                .name("请假流程") 
                .addClasspathResource("processes/MyProcess.bpmn") // bpmn文件路径
                .deploy();
        System.err.println("部署id：" + deployment.getId());
        System.err.println("部署名称：" + deployment.getName());
    }
    
    /*
     * 使用RuntimeService启动流程
     */
    @Test
    public void startProcessInstance() {
        // 定义流程变量
        // 流程变量会自动注入到bpmn文件中定义的表达式中，例如第一个任务assignee = ${user}，流程启动后assignee = 张三
        Map<String, Object> var = new HashMap<>();
        var.put("user", "张三");
        ProcessInstance processInstance = processEngine.getRuntimeService()
                // 根据流程key启动，key就是bpmn文件中定义的id
                // 如果key对应的流程有多个版本，启动的将会是最新版本的流程
                .startProcessInstanceByKey("myProcess_1", var);
                // 根据流程定义id启动
                // 供参考：流程定义id = 流程key_版本号_uuid
//                .startProcessInstanceById("myProcess_1:1:15150967-0e1e-11eb-b36e-005056c00001", var); 
        System.err.println("流程实例id：" + processInstance.getId());
        System.err.println("流程定义id：" + processInstance.getProcessDefinitionId());
    }
    
    /*
     * 使用RuntimeService查询processInstance
     */
    @Test
    public void processInstanceQuery() {
        String processInstanceId = "b3e16f5d-0e20-11eb-a955-005056c00001";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceQuery() 
                .processInstanceId(processInstanceId)
                .singleResult();
        // 流程结束后，act_ru_execution对应的记录被移除，查询为空
        if (processInstance == null) {
            System.err.println("流程已经结束，id：" + processInstanceId);
        } else {
            System.err.println("流程没有结束，id：" + processInstanceId);
        }
    }

    /*
     * 使用TaskService查询task
     * 数据源于act_ru_task，表中存储的是等待执行链的第一个任务
     */
    @Test
    public void taskQuery() {
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService
                .createTaskQuery()
                // 查询条件
//                .taskCandidateUser(candidateUser) 
                .processDefinitionId("myProcess_1:1:15150967-0e1e-11eb-b36e-005056c00001") 
//                .processInstanceId(processInstanceId) 
//                .executionId(executionId) 
                // 排序
                .orderByTaskCreateTime().asc() // 使用创建时间的升序排列
                // 返回结果集
//                .singleResult() // 返回惟一结果集
//                .count() // 返回结果集的数量
//                .listPage(firstResult, maxResults); // 分页查询
                .list();// 返回列表
        if (list != null) {
            for (Task task : list) {
                System.err.println("任务id：" + task.getId());
                System.err.println("任务名称：" + task.getName());
                System.err.println("任务的创建时间：" + task.getCreateTime());
                System.err.println("任务的办理人：" + task.getAssignee());
                System.err.println("流程实例id：" + task.getProcessInstanceId());
                System.err.println("执行对象id：" + task.getExecutionId());
                System.err.println("流程定义id：" + task.getProcessDefinitionId());
                System.err.println("任务变量：" + taskService.getVariableInstances(task.getId()));
            }
        }
    }

    /*
     * 使用TaskService执行task
     * 某个任务执行之后，act_ru_task对应的记录被移除，并插入对应的下一个任务
     */
    @Test
    public void complete() {
        String taskId = "b9044163-0e23-11eb-a071-005056c00001";
        processEngine.getTaskService()
                .complete(taskId);
        System.err.println("任务已完成，id：" + taskId);
    }
    
    /*
     * 使用HistoryService查询historicTaskInstance
     */
    @Test
    public void historicTaskInstanceQuery() {
        List<HistoricTaskInstance> list = processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .list();
        if (list != null) {
            for (HistoricTaskInstance historicTaskInstance : list) {
                System.err.println(
                        "id：" + historicTaskInstance.getId() + "\r\n" +
                        "名称：" + historicTaskInstance.getName() + "\r\n" +
                        "流程id：" + historicTaskInstance.getProcessInstanceId() + "\r\n" +
                        "开始时间：" + historicTaskInstance.getStartTime() + "\r\n" +
                        "结束时间：" + historicTaskInstance.getEndTime() + "\r\n" +
                        "持续时间：" + historicTaskInstance.getDurationInMillis() + "\r\n");
            }
        }
    }
    
}
