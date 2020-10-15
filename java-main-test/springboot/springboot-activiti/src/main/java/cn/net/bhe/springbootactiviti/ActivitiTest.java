package cn.net.bhe.springbootactiviti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ActivitiTest {
    
    @Autowired
    ActivitiUtils activitiUtils;
    
    @Test
    public void deploy() {
        Deployment deployment = activitiUtils.deploy(
                "processes/MyProcess.bpmn", 
                "请假流程"); 
        System.err.println("部署id：" + deployment.getId());
        System.err.println("部署名称：" + deployment.getName());
    }
    
    @Test
    public void startProcessInstance() {
        ProcessInstance processInstance = null;
        
        Map<String, Object> vars = new HashMap<String, Object>();
        // 指定下一个节点的受托人assignee
        // assignee可作为筛选条件来查询任务
        // 例此处指定assignee = "张三"，该值会传入下一个节点并注入到对应表达式中，查询时若指定条件assignee = "张三"，则只会查出assignee = "张三"的任务
        vars.put("assignee", "张三");
        
        processInstance = activitiUtils.startProcessInstance("myProcess_1", null, vars);
//        processInstance = startProcessInstance(null, "myProcess_1:1:15150967-0e1e-11eb-b36e-005056c00001", vars);
        
        System.err.println("流程实例id：" + processInstance.getId());
        System.err.println("流程定义id：" + processInstance.getProcessDefinitionId());
    }
    
    @Test
    public void processInstanceQuery() {
        String processInstanceId = "05ba1f4e-0ee7-11eb-a636-005056c00001";
        ProcessInstance processInstance = activitiUtils.processInstanceQuery(processInstanceId);
        
        if (processInstance == null) {
            System.err.println("流程已经结束，id：" + processInstanceId);
        } else {
            System.err.println("流程没有结束，id：" + processInstanceId);
        }
    }
    
    @Test
    public void taskQuery() {
        List<Task> list = activitiUtils.taskQuery("张三");
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                System.err.println("任务id：" + task.getId());
                System.err.println("任务名称：" + task.getName());
                System.err.println("任务的创建时间：" + task.getCreateTime());
                System.err.println("任务的办理人：" + task.getAssignee());
                System.err.println("流程实例id：" + task.getProcessInstanceId());
                System.err.println("执行对象id：" + task.getExecutionId());
                System.err.println("流程定义id：" + task.getProcessDefinitionId());
            }
        } else {
            System.err.println("没有任务");
        }
    }
    
    @Test
    public void complete() {
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("assignee", "");
        vars.put("verify", 1); // 指定网关条件值
        String taskId = "fae0e9bb-0ee7-11eb-9dd5-005056c00001";
        activitiUtils.complete(taskId, vars);
        System.err.println("任务已执行，id：" + taskId);
    }
    
    @Test
    public void historicTaskInstanceQuery() {
        List<HistoricTaskInstance> list = activitiUtils.historicTaskInstanceQuery();
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
