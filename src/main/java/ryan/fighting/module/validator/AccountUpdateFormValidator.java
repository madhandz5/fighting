package ryan.fighting.module.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ryan.fighting.module.dto.AccountForm;
import ryan.fighting.module.dto.AccountUpdateForm;
import ryan.fighting.module.repository.AccountRepository;

@Component
@RequiredArgsConstructor
public class AccountUpdateFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(AccountUpdateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountUpdateForm accountUpdateForm = (AccountUpdateForm) target;

        if(accountRepository.existsByEmail(accountUpdateForm.getEmail())){
            errors.rejectValue("email","invalid.email",new Object[]{accountUpdateForm.getEmail()},"이미 사용중인 이메일입니다.");
        }

        if(accountRepository.existsByNickname(accountUpdateForm.getNickname())){
            errors.rejectValue("nickname","invalid.nickname",new Object[]{accountUpdateForm.getNickname()},"이미 사용중인 닉네임입니다.");
        }


    }
}
