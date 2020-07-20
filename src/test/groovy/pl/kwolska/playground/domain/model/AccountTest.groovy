package pl.kwolska.playground.domain.model

import spock.lang.Specification

import static java.time.LocalDateTime.now

class AccountTest extends Specification {

  def 'should calculate balance'() {
    given:
      def account1 = new Account(1, [], null)
      def account2 = new Account(2, [], null)

    and:
      def transfer1 = new Transfer(1, account1, account2, 1000.0, now())
      def transfer2 = new Transfer(2, account1, account2, 1000.0, now())
      def transfer3 = new Transfer(3, account2, account1, 500.0, now())
      def transfer4 = new Transfer(4, account2, account1, 250.0, now())

    and:
      account1.setTransfers([transfer1, transfer2, transfer3, transfer4])
      account2.setTransfers([transfer1, transfer2, transfer3, transfer4])

    when:
      def balance1 = account1.calculateBalance()
      def balance2 = account2.calculateBalance()

    then:
      balance1 == 1250.0
      balance2 == -1250.0
  }

}
