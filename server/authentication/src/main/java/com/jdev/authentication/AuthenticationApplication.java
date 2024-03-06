package com.jdev.authentication;

import com.jdev.authentication.model.Role;
import com.jdev.authentication.model.User;
import com.jdev.authentication.repository.RoleRepository;
import com.jdev.authentication.repository.UserRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	//config para conectar no eureka
	@Autowired
	private EurekaClient discoveryClient;

	public String serviceUrl() {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("STORES", false);
		return instance.getHomePageUrl();
	}
}
