package ryan.fighting.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ryan.fighting.module.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
