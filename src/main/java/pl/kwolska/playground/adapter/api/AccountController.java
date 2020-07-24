package pl.kwolska.playground.adapter.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.kwolska.playground.domain.TransferService;
import pl.kwolska.playground.domain.model.Transfer;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AccountController {
  
  private final TransferService transferService;
  
  @RequestMapping(value = "/createAccounts", method = RequestMethod.POST)
  public void createAccounts() {
    transferService.createAccounts();
  }
  
  @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
  public ResponseEntity<AccountDto> findAccountById(@PathVariable Integer accountId) {
    return transferService.findAccountById(accountId)
        .map(account -> new AccountDto(account.getId(), account.calculateBalance(),
            account.getTransfers().stream().map(transfer ->
                new TransferDto(transfer.getDebitAccountId(), transfer.getCreditAccountId(), transfer.getMoney()))
                .collect(Collectors.toList())))
        .map(accountDto -> new ResponseEntity<>(accountDto, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  
  @RequestMapping(value = "/accounts/{accountId}/transfers", method = RequestMethod.GET)
  public List<Transfer> getAccountTransfer(@PathVariable Integer accountId) {
    return transferService.findAccountTransfers(accountId);
  }
  
}
