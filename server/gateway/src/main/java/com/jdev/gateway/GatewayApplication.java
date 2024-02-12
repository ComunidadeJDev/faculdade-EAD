package com.jdev.gateway;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Autowired
	private EurekaClient discoveryClient;

	public String serviceUrl() {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("STORIES", false);
		return instance.getHomePageUrl();
	}


}
