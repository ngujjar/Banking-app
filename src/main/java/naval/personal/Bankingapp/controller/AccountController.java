package naval.personal.Bankingapp.controller;

import naval.personal.Bankingapp.dto.AccountDto;
import naval.personal.Bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }
    //ADD REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //GET ACCOUNT API

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountbyId(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    // DEPOSIT REST API
    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto> deposit(@PathVariable Long id ,
                                               @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
       AccountDto accountDto = accountService.deposit(id, request.get("amounyt"));
       return ResponseEntity.ok(accountDto);
    }

    //WITHDRAW REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw (@PathVariable Long id,
                                                @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // GET ALL ACCOUNTS REST API
    public  ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // DELETE ACCOUNT REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully");
    }
}
