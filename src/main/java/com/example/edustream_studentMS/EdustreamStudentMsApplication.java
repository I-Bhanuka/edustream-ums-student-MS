package com.example.edustream_studentMS;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class EdustreamStudentMsApplication implements CommandLineRunner {

	@Value("${server.port}")
	private int port;

	public static void main(String[] args) {
		SpringApplication.run(EdustreamStudentMsApplication.class, args);
	}

	// To run some code after the application starts.
	@Override
	public void run(String... args) throws Exception {
		log.info("EduStream Student Microservice started");
		log.info("Student Microservice is running at http://localhost:{}", port);

	}

}
