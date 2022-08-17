package com.library.libraryapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class LibraryapiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Scheduled(cron = "0 54 15 1/1 * ?")
	public void testeAgendamentoTarefas(){
		System.out.printf("Agendamento de tarefas funcionando com sucesso!");
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryapiApplication.class, args);
	}

}
