package naval.personal.Bankingapp.repository;

import naval.personal.Bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
