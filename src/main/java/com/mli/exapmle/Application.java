package com.mli.exapmle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync  // 啟用非同步支持（雖然我們用 CompletableFuture，但可備用）
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
