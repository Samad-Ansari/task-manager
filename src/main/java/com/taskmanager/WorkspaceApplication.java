package com.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WorkspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkspaceApplication.class, args);
	}

}
