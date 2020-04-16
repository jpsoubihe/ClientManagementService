package controllers;

import controllers.dtos.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AccountService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static converters.AccountConverter.fromService;
import static converters.AccountConverter.toService;

@RestController
@RequestMapping("/v1/account")
@Slf4j
public class AccountController {

//    TESTING CHERRY PICK 2 - WRONG!!!

    @Autowired
    AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<AccountDto>> listAllAccounts() {
        List<services.dtos.AccountDto> serviceAccounts = accountService.getAllAccounts();
        return ResponseEntity
                .ok(serviceAccounts
                        .stream()
                        .map(sa -> fromService(sa))
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountDto> getAccount(
            @PathVariable String username) {
        services.dtos.AccountDto serviceDto =
                accountService.getAccount(username);
        return ResponseEntity
                .ok(fromService(serviceDto));
    }

    @PostMapping()
    public ResponseEntity<AccountDto> createNewAccount(
            @RequestBody AccountDto accountDto) {
        services.dtos.AccountDto dto =
                toService(accountDto);

        dto = accountService.postAccount(dto);

        accountDto = fromService(dto);

        return ResponseEntity
                .ok(accountDto);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<AccountDto> deleteAccount(
            @PathVariable String username) {

        services.dtos.AccountDto serviceDto =
                accountService.deleteAccount(username);

        return ResponseEntity
                .ok(fromService(serviceDto));
    }

    @GetMapping("/user/{username}/{publishDate}")
    public ResponseEntity<AccountDto> getAccountWithDate(
            @PathVariable String username,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate publishDate) {

        log.info(publishDate.toString());

//        log.info(publishDate.toString());

        services.dtos.AccountDto serviceDto =
                accountService.getAccount(username);

        return ResponseEntity
                .ok(fromService(serviceDto));
    }

}
