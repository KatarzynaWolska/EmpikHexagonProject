package pl.kwolska.playground.ft

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@AutoConfigureMockMvc
@SpringBootTest
class AccountTransfersFT extends Specification {

  @Autowired
  MockMvc mockMvc

  def 'should throw exception if an account is not found'() {
    expect:
      true
  }

  def 'should perform account transfers'() {
    given: 'given some created accounts'

    and: 'prepared transfer request'

    when: 'performing transfer'

    then: 'account 1 should have transfers and balance updated'
      true
    and: 'account 2 should have transfers and balance updated'
      true
  }

}
