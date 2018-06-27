package io.pivotal.workshop;

import io.pivotal.workshop.domain.Snippet;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class SnippetProducerApplication {

	private final RabbitTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(SnippetProducerApplication.class, args);
	}

	@Autowired
	public SnippetProducerApplication(RabbitTemplate template) {
		this.template = template;
	}

	@Scheduled(fixedDelay = 1000)
	public void schedule() {
		Snippet snippet = new Snippet("Javascript: Alert", "alert('Hi there!')");

		template.convertAndSend("spring-boot", snippet);

	}
}
