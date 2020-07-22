package pl.kwolska.playground.adapter.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kwolska.playground.domain.AccountNotFoundException;
import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.model.Transfer;
import pl.kwolska.playground.domain.TransferService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@AllArgsConstructor
public class TransferController {
  
  private final TransferService transferService;
  
  @RequestMapping(value = "/transfers", method = RequestMethod.POST)
  public void addTransfer(@RequestBody NewTransferRequest transferRequest) {
    transferService.createTransfer(transferRequest.getDebitAccountId(), transferRequest.getCreditAccountId(), transferRequest.getMoney());
  }
  
  @ExceptionHandler(AccountNotFoundException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public String handleException(AccountNotFoundException exception) {
    return exception.getMessage();
  }
}
