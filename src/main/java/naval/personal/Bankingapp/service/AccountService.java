package naval.personal.Bankingapp.service;

import naval.personal.Bankingapp.dto.AccountDto;
import naval.personal.Bankingapp.entity.Account;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

}
