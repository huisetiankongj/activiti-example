package com.czx.course.shapter01;

import org.activiti.engine.ProcessEngineConfiguration;

public class CreateProcessEngine {

	public static void main(String[] args) {
		ProcessEngineConfiguration config = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("course/shapter01/activiti.cfg.xml");
		//������������
		config.buildProcessEngine();
	}
}
