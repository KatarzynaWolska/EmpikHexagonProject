package pl.kwolska.playground.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kwolska.playground.domain.AccountRepository;
import pl.kwolska.playground.domain.TransferService;

@Configuration
public class DomainConfiguration {
  
  @Bean
  public TransferService transferService(AccountRepository accountRepository) {
    return new TransferService(accountRepository);
  }
}
