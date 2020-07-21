package pl.kwolska.playground.adapter.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.model.Transfer;
import pl.kwolska.playground.domain.TransferService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@AllArgsConstructor
public class TransferController {
  
  private final TransferService transferService;

  // todo 5: tu powinien @RequestBody (idki, money)
  @RequestMapping(value = "/accounts/{creditAccountId}/transfers", method = RequestMethod.POST)
  public void addUserTransfer(@PathVariable int creditAccountId, @RequestBody TransferDto transferDto) {
    transferService.createTransfer(transferDto.getDebitAccountId(), creditAccountId, transferDto.getMoney());
  }
  
  @RequestMapping(value = "/createAccounts", method = RequestMethod.POST)
  public void createAccounts() {
    transferService.createAccounts();
  }

  // todo 4: /accounts/{id} - id, balance, transfery
  @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
  public ResponseEntity<Account> findAccountById(@PathVariable Integer accountId) {
    return transferService.findAccountById(accountId)
        .map(account -> new ResponseEntity<>(account, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @RequestMapping(value = "/accounts/{accountId}/transfers", method = RequestMethod.GET)
  public List<Transfer> getAccountTransfer(@PathVariable Integer accountId) {
    return transferService.findAccountTransfers(accountId);
  }
  
  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public String handleException(EntityNotFoundException exception) {
    return exception.getMessage();
  }
}
