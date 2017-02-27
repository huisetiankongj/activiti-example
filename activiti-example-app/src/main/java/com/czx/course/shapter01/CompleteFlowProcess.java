package com.czx.course.shapter01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;

public class CompleteFlowProcess {

	private static RepositoryService repositoryService;
	private static RuntimeService runtimeService;
	private static TaskService taskService;
	
	//��ʼ����������
	private static void initProcessEngine(){
		ProcessEngineConfiguration config = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("course/shapter01/activiti.cfg.xml");
		//������������
		ProcessEngine processEngine = config.buildProcessEngine();
		//��ȡ����service
		repositoryService = processEngine.getRepositoryService();
		runtimeService = processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
	}
	
	//��������
	private static void deployFlow() throws FileNotFoundException{
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("D:/Workspaces/activiti-example/activiti-example-app/src/main/resources/course/leave.zip"));
		repositoryService.createDeployment()
						 .name("�������")
						 .addZipInputStream(zipInputStream)
						 .deploy();
						 
	}
	
	//��ȡ������Ϣ
	private static void getDeployment(String key){
		List<Deployment> deploymentList = repositoryService.createDeploymentQuery().processDefinitionKey("leave").list();
		for(Deployment d :deploymentList){
			System.out.println(d.getId()+"====="+d.getName());
		}
	}
	//��ȡ����ʵ��
	private static void getInstanceDefine(String key){
		List<ProcessDefinition> deploymentList = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).list();
		for(ProcessDefinition pd :deploymentList){
			System.out.println(pd.getId()+"====="+pd.getName());
		}
	}
	
	
	//����һ������ʵ��
	private static void createFlowInstance(String processDefinitionKey){
		runtimeService.startProcessInstanceByKey(processDefinitionKey);
	}
	
	//��ȡ����ʵ��
	private static ProcessInstance getFlowInstance(String processInstanceId){
		ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		System.out.println(instance.getProcessDefinitionKey()+"===="+instance.getProcessInstanceId());
		return instance;
	}
	
	
	//��ȡ��ǰ�û���������
	private static List<Task> getTaskByPerson(String assignee){
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).list();
		return taskList;
	}
	//ִ������ʵ��
	private static void completeTask(String taskId){
		taskService.complete(taskId);
	}
	
	
	public static void main(String[] args) {
		try {
			//��ʼ����������
			initProcessEngine();
			deployFlow();
			/*String key = "leave";
			getDeployment(key);
			getInstanceDefine(key);*/
			
			/*//��������ʵ��
			String processDefinitionKey = "leave";
			createFlowInstance(processDefinitionKey);*/
			
			/*//��ѯ������ʵ��
			String processInstanceId = "5001";
			ProcessInstance instance = getFlowInstance(processInstanceId);*/
			
			/*//��ѯ��������
			String assignee = "����";
			List<Task> taskList = getTaskByPerson(assignee);
			for(Task t : taskList){
				System.out.println("taskId:"+t.getId()+"  taskName:"+t.getName());
			}*/
			
			/*//ִ�и�������
			String taskId = "7502";
			completeTask(taskId);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
