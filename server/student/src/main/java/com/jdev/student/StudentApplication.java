package com.jdev.student;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Autowired
	private EurekaClient discoveryClient;

	public String serviceUrl() {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("STORES", false);
		return instance.getHomePageUrl();
	}
}
