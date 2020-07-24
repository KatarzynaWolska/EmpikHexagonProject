package pl.kwolska.playground.adapter.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/health")
public class HealthController {

  @ResponseStatus(value = OK)
  @RequestMapping
  public void health() {

  }

}
