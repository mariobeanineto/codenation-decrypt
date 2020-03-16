package com.codenation.decrypt;

import com.codenation.decrypt.service.DecryptService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration
public class DecryptApplication {

	static private DecryptService decryptService;

	public DecryptApplication(DecryptService decryptService) {
		this.decryptService = decryptService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DecryptApplication.class, args);
		decryptService.doDecrypt();
	}

}
