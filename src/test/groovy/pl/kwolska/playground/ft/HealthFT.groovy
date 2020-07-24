package pl.kwolska.playground.ft

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest
class HealthFT extends Specification {

  @Autowired
  MockMvc mockMvc

  def 'should return 200 OK for healtheck'() {
    expect:
      mockMvc.perform(get('/health')).andExpect(status().isOk())
  }

}
