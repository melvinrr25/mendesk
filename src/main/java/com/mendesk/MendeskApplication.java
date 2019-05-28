package com.mendesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.mendesk.repository.CommentRepo;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MendeskApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MendeskApplication.class, args);
	}

	@Autowired
	CommentRepo repository;

	@Override
	public void run(String... args) throws Exception {
		// repository.save(new Request("UI issue", "This is the content of the ticket",
		// "Open", "Urgent"));
		// System.out.println(repository.findAll());
		// System.out.println(c.getRequest());
	}

}
