package naval.personal.Bankingapp.mapper;

import naval.personal.Bankingapp.dto.AccountDto;
import naval.personal.Bankingapp.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }
    
    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                null, // Set accountNumber to null or assign a value if it's available
                null, // Set accountType to null or assign a value if it's available
                account.getBalance()
        );
        return accountDto;
    }
}
