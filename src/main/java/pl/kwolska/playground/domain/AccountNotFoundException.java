package pl.kwolska.playground.domain;

public class AccountNotFoundException extends Exception {
  public AccountNotFoundException() {
    super("Account not found");
  }
}
