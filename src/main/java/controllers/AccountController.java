package controllers;

import controllers.dtos.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AccountService;

import java.util.List;
import java.util.stream.Collectors;

import static controllers.converters.ControllerConverter.fromService;
import static controllers.converters.ControllerConverter.toService;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<AccountDto>> listAllAccounts(){
        List<services.dtos.AccountDto> serviceAccounts = accountService.getAllAccounts();
        return ResponseEntity
                .ok(serviceAccounts
                        .stream()
                        .map(sa -> fromService(sa))
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String username){
        services.dtos.AccountDto serviceDto = accountService.getAccount(username);
        return ResponseEntity
                .ok(fromService(serviceDto));
    }

    @PostMapping()
    public ResponseEntity<AccountDto> createNewAccount(@RequestBody AccountDto accountDto){
        services.dtos.AccountDto dto = toService(accountDto);
        dto = accountService.postAccount(dto);
        accountDto = fromService(dto);
        return ResponseEntity
                .ok(accountDto);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable String username){
        services.dtos.AccountDto dto = accountService.deleteAccount(username);
        return ResponseEntity
                .ok(fromService(dto));
    }

}
