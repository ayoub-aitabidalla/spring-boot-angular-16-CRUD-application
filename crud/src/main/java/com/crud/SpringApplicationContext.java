package com.crud;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {

	
	private static ApplicationContext CONTEXT;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CONTEXT = applicationContext;
	}
	
	//ON UTULISE CETTE METHODE POUR INSTANCIER UN OBJET D'UNE CLASSE
	public static Object getBean(String beanName)
	{
		return CONTEXT.getBean(beanName);
		
	}

}
