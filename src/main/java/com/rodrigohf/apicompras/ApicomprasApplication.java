package com.rodrigohf.apicompras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigohf.apicompras.services.S3Service;

@SpringBootApplication
public class ApicomprasApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3;

	public static void main(String[] args) {
		SpringApplication.run(ApicomprasApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		s3.uploadFile("C:\\Users\\rodri\\OneDrive\\Documentos\\work.png");
	}

}
