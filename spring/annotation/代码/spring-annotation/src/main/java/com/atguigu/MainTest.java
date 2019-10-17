package com.atguigu;

import com.atguigu.config.MainConfigTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;

@SpringBootApplication
public class MainTest {
	
	public static void main(String[] args) {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//		Person bean = (Person) applicationContext.getBean("person");
//		System.out.println(bean);
		
//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
//		Person bean = applicationContext.getBean(Person.class);
//		System.out.println(bean);
//
//		String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
//		for (String name : namesForType) {
//			System.out.println(name);
//		}
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
//
//		String[] names = context.getBeanDefinitionNames();
//		for (String name: names
//			 ) {
//			System.out.println(name);
//		}

		SpringApplication.run(MainTest.class,args);

	}

}
