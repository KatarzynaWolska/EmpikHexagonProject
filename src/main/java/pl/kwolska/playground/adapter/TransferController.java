package pl.kwolska.playground.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kwolska.playground.domain.Transfer;
import pl.kwolska.playground.domain.TransferService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TransferController {
  
  @Autowired
  private TransferService transferService;
  
  @RequestMapping(value = "/account/{creditAccountId}/transfers", method = RequestMethod.POST)
  public void addUserTransfer(@PathVariable int creditAccountId, Integer debitAccountId, BigDecimal money) {
    transferService.createTransfer(debitAccountId, creditAccountId, money);
  }
  
  @RequestMapping(value = "/account/{accountId}/transfers", method = RequestMethod.GET)
  public List<Transfer> getAccountTransfer(@PathVariable Integer accountId) {
    return transferService.getAccountTransfers(accountId);
  }
}
