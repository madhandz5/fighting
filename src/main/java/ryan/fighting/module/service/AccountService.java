package ryan.fighting.module.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ryan.fighting.module.domain.Account;
import ryan.fighting.module.dto.AccountForm;
import ryan.fighting.module.repository.AccountRepository;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account saveAccount(@Valid AccountForm accountForm) {
        Account account = Account.builder()
                .name(accountForm.getName())
                .nickname(accountForm.getNickname())
                .email(accountForm.getEmail())
                .password(passwordEncoder.encode(accountForm.getPassword()))
                .build();
        return accountRepository.save(account);
    }
}
