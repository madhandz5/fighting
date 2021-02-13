package ryan.fighting.module.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ryan.fighting.module.domain.Account;
import ryan.fighting.module.dto.AccountForm;
import ryan.fighting.module.dto.AccountUpdateForm;
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

    public Account findAccount(Long id) {
        return accountRepository.findAccountById(id);
    }

    public Account updateAccount(Long id, @Valid AccountUpdateForm accountUpdateForm) {
        Account account = accountRepository.findAccountById(id);
        account.setId(id);
        account.setName(accountUpdateForm.getName());
        account.setNickname(accountUpdateForm.getNickname());
        account.setEmail(accountUpdateForm.getEmail());
        account.setPassword(passwordEncoder.encode(accountUpdateForm.getPassword()));

        return accountRepository.save(account);
    }
}
