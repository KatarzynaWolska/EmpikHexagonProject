package pl.kwolska.playground.adapter.api;

import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import pl.kwolska.playground.domain.model.Transfer;
import pl.kwolska.playground.domain.TransferService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class TransferController {
  
  private final TransferService transferService;

  // todo 5: tu powinien @RequestBody (idki, money)
  @RequestMapping(value = "/account/{creditAccountId}/transfers", method = RequestMethod.POST)
  public void addUserTransfer(@PathVariable int creditAccountId, Integer debitAccountId, BigDecimal money) {
    transferService.createTransfer(debitAccountId, creditAccountId, money);
  }

  // todo 4: /accounts/{id} - id, balance, transfery

  @RequestMapping(value = "/account/{accountId}/transfers", method = RequestMethod.GET)
  public List<Transfer> getAccountTransfer(@PathVariable Integer accountId) {
    return transferService.findAccountTransfers(accountId);
  }
}
