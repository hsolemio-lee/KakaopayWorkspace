package com.kakaopay.inquiry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Slf4j
public class InquiryApplication {

	public static void main(String[] args) {

		SpringApplication.run(InquiryApplication.class, args);

		log.info("--------------------------------------------");
		log.info("       Welcome to Sol App");
		log.info("       Application start successfully");
		log.info("--------------------------------------------");
	}

}
