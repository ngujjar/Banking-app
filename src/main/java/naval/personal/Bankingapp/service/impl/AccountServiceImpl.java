package naval.personal.Bankingapp.service.impl;

import naval.personal.Bankingapp.dto.AccountDto;
import naval.personal.Bankingapp.entity.Account;
import naval.personal.Bankingapp.mapper.AccountMapper;
import naval.personal.Bankingapp.repository.AccountRepository;
import naval.personal.Bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount =accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

  if(account.getBalance() < amount){
      throw new RuntimeException("Insufficient Fund");
  }
  double total = account.getBalance() - amount;
  account.setBalance(total);
 Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
      List<Account> accounts =  accountRepository.findAll();
      accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
              .collect((Collectors.toList()));
        return null;
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        accountRepository.deleteById(id);
    }


}
