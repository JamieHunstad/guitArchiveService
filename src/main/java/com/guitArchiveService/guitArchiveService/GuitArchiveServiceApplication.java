package com.guitArchiveService.guitArchiveService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GuitArchiveServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuitArchiveServiceApplication.class, args);
	}
	@Configuration
	public class WebConfig
	{
		@Bean
		public WebMvcConfigurer corsConfigurer()
		{
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedOrigins("https://guitararchive-app-3f4567fa06fe.herokuapp.com/")
							.allowedMethods("GET", "POST", "PUT", "DELETE")
							.allowedHeaders("*")
							.allowCredentials(true)
							.exposedHeaders("Authorization");
				}
			};
		}
	}

}
