package ryan.fighting.module.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ryan.fighting.module.dto.AccountForm;
import ryan.fighting.module.repository.AccountRepository;

@Component
@RequiredArgsConstructor
public class AccountFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(AccountForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountForm accountForm = (AccountForm) target;

        if(accountRepository.existsByEmail(accountForm.getEmail())){
            errors.rejectValue("email","invalid.email",new Object[]{accountForm.getEmail()},"이미 사용중인 이메일입니다.");
        }

        if(accountRepository.existsByNickname(accountForm.getNickname())){
            errors.rejectValue("nickname","invalid.nickname",new Object[]{accountForm.getNickname()},"이미 사용중인 닉네임입니다.");
        }


    }
}
