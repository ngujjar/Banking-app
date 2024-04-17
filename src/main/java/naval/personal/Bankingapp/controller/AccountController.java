package naval.personal.Bankingapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import naval.personal.Bankingapp.dto.AccountDto;
import naval.personal.Bankingapp.service.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add account form page (for browsers)
    @GetMapping("/add")
    public String addAccountForm() {
        return "add-account"; // Thymeleaf template name
    }

    // Process add account form submission (for browsers)
    @PostMapping("/add")
    public String addAccount(@ModelAttribute AccountDto accountDto) {
        accountService.createAccount(accountDto);
        return "redirect:/accounts"; // Redirect to the account list page
    }

    // Process add account request (for Postman)
    @PostMapping("/add/json")
    @ResponseBody
    public ResponseEntity<AccountDto> addAccountJson(@RequestBody AccountDto accountDto) {
        AccountDto createdAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    // Account details page (for browsers)
    @GetMapping("/{id}")
    public String getAccountById(@PathVariable Long id, Model model) {
        AccountDto accountDto = accountService.getAccountById(id);
        model.addAttribute("account", accountDto);
        return "account-details"; // Thymeleaf template name
    }

    // Get account details (for Postman)
    @GetMapping("/{id}/json")
    @ResponseBody
    public ResponseEntity<AccountDto> getAccountJson(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    // Deposit form page (for browsers)
    @GetMapping("/{id}/deposit")
    public String depositForm(@PathVariable Long id, Model model) {
        model.addAttribute("accountId", id);
        return "deposit-form"; // Thymeleaf template name
    }

    // Process deposit form submission (for browsers)
    @PostMapping("/{id}/deposit")
    public String deposit(@PathVariable Long id, @RequestParam Double amount) {
        accountService.deposit(id, amount);
        return "redirect:/accounts/{id}"; // Redirect to account details page
    }

    // Process deposit request (for Postman)
    @PostMapping("/{id}/deposit/json")
    @ResponseBody
    public ResponseEntity<AccountDto> depositJson(@PathVariable Long id, @RequestParam Double amount) {
        AccountDto updatedAccount = accountService.deposit(id, amount);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    // Withdrawal form page (for browsers)
    @GetMapping("/{id}/withdraw")
    public String withdrawForm(@PathVariable Long id, Model model) {
        model.addAttribute("accountId", id);
        return "withdraw-form"; // Thymeleaf template name
    }

    // Process withdrawal form submission (for browsers)
    @PostMapping("/{id}/withdraw")
    public String withdraw(@PathVariable Long id, @RequestParam Double amount) {
        accountService.withdraw(id, amount);
        return "redirect:/accounts/{id}"; // Redirect to account details page
    }

    // Process withdrawal request (for Postman)
    @PostMapping("/{id}/withdraw/json")
    @ResponseBody
    public ResponseEntity<AccountDto> withdrawJson(@PathVariable Long id, @RequestParam Double amount) {
        AccountDto updatedAccount = accountService.withdraw(id, amount);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    // Account list page (for browsers)
    @GetMapping
    public String getAllAccounts(Model model) {
        List<AccountDto> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "account-list"; // Thymeleaf template name
    }

    // Get all accounts (for Postman)
    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<List<AccountDto>> getAllAccountsJson() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // Delete account (for browsers)
    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/accounts"; // Redirect to account list page
    }

    // Delete account (for Postman)
    @DeleteMapping("/{id}/json")
    @ResponseBody
    public ResponseEntity<Void> deleteAccountJson(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
