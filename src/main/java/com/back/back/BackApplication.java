package com.back.back;

import com.back.back.service.StorageService;
import com.back.back.util.StorageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;
import javax.transaction.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(StorageProperties.class)
public class BackApplication{

	private final StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void doSomethingAfterStartup() {
		storageService.deleteAll();
		storageService.init();
	}

}
