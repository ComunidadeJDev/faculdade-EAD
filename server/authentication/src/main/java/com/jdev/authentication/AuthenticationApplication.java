package com.jdev.authentication;

//import com.netflix.appinfo.InstanceInfo;
//import com.netflix.discovery.EurekaClient;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

//	//config para conectar no eureka
//	@Autowired
//	private EurekaClient discoveryClient;
//
//	public String serviceUrl() {
//		InstanceInfo instance = discoveryClient.getNextServerFromEureka("STORES", false);
//		return instance.getHomePageUrl();
//	}
}
