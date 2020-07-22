package pl.kwolska.playground.domain;

public class AccountNotFoundException extends RuntimeException {
  public AccountNotFoundException() {
    super("Account not found");
  }
}
