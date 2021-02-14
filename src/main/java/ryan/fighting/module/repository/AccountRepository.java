package ryan.fighting.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ryan.fighting.module.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

    Account findAccountById(Long id);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
