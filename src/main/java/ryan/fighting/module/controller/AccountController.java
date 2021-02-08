package ryan.fighting.module.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ryan.fighting.module.domain.Account;
import ryan.fighting.module.dto.AccountForm;
import ryan.fighting.module.service.AccountService;
import ryan.fighting.module.validator.AccountFormValidator;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/account")
public class AccountController {

    private final AccountService accountService;
    private final AccountFormValidator accountFormValidator;

    @InitBinder("accountForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountFormValidator);
    }

    @PostMapping(value = "/join")
    public Account saveAccount(@RequestBody @Valid AccountForm accountForm) {
        return accountService.saveAccount(accountForm);
    }
}
