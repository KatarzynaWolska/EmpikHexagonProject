package pl.kwolska.playground.ft

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import pl.kwolska.playground.adapter.api.TransferDto
import pl.kwolska.playground.adapter.storage.AccountEntity
import pl.kwolska.playground.adapter.storage.JpaAccountRepository
import pl.kwolska.playground.adapter.storage.JpaTransferRepository
import pl.kwolska.playground.adapter.storage.TransferEntity
import spock.lang.Specification

import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

@AutoConfigureMockMvc
@SpringBootTest
class AccountTransfersFT extends Specification {

  @Autowired
  MockMvc mockMvc
  
  @Autowired
  JpaAccountRepository accountRepository
  
  @Autowired
  JpaTransferRepository transferRepository

  def 'should throw exception if an account is not found'() {
    expect:
      mockMvc.perform(MockMvcRequestBuilders.post('/accounts/1/transfers'))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
  }

  def 'should perform account transfers'() {
    given: 'given some created accounts'
      def account1 = new AccountEntity(1)
      def account2 = new AccountEntity(2)
    
      accountRepository.save(account1)
      accountRepository.save(account2)
    
    and: 'created transfers'
      def transferEntity1 = new TransferEntity(UUID.randomUUID().toString(), 1, 2, 500.00, LocalDateTime.now())
      def transferEntity2 = new TransferEntity(UUID.randomUUID().toString(), 2, 1, 50.00, LocalDateTime.now())
    
      transferRepository.save(transferEntity1)
      transferRepository.save(transferEntity2)
    
    and: 'prepared transfer request'
      def transferRequest1 = new TransferDto(2, 400.00)
      def transferRequest2 = new TransferDto(1, 20.00)
    
    when: 'performing transfer'
      mockPost(path: '/accounts/1/transfers', request: transferRequest1)
      mockPost(path: '/accounts/2/transfers', request: transferRequest2)
      
    then: 'account 1 should have transfers and balance updated'
      def responseAccount1 = mockGet('/accounts/1')
      def parsedResponseAccount1 = parse(responseAccount1)
      
      responseAccount1.status == 200
      parsedResponseAccount1.balance == 50.0
      parsedResponseAccount1.transfers[0].debitAccountId == 2
      parsedResponseAccount1.transfers[0].creditAccountId == 1
      parsedResponseAccount1.transfers[0].money == 50.0
    
    and: 'account 2 should have transfers and balance updated'
      def responseAccount2 = mockGet('/accounts/2')
      def parsedResponseAccount2 = parse(responseAccount2)
  
      responseAccount2.status == 200
      parsedResponseAccount2.balance == -50.0
      parsedResponseAccount2.transfers[0].debitAccountId == 1
      parsedResponseAccount2.transfers[0].creditAccountId == 2
      parsedResponseAccount2.transfers[0].money == 500.0
  }
  
  def parse(def response) {
    new JsonSlurper().parseText(response.getContentAsString(StandardCharsets.UTF_8))
  }
  
  String toJson(def request) {
    JsonOutput.toJson(request)
  }
  
  MockHttpServletResponse mockPost(params) {
    mockMvc.perform(MockMvcRequestBuilders.post(params.path)
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(params.request)))
        .andReturn()
        .response
  }
  
  MockHttpServletResponse mockGet(path) {
    mockMvc.perform(MockMvcRequestBuilders.get(path))
        .andReturn()
        .response
  }
}
