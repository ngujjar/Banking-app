package naval.personal.Bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private String accountNumber;
    private String accountType;
    private double balance;
}
