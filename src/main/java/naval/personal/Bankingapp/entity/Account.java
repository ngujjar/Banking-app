package naval.personal.Bankingapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
 @Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;
    private double balance;


}
