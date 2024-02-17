package com.jdev.course;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.netflix.discovery.EurekaClient;

@SpringBootApplication
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

	//config para conectar no eureka
	@Autowired
	private EurekaClient discoveryClient;

	public String serviceUrl() {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("STORES", false);
		return instance.getHomePageUrl();
	}

}
