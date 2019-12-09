package controllers;

import controllers.dtos.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.AccountService;

import java.util.List;
import java.util.stream.Collectors;

import static controllers.converters.ControllerConverter.fromService;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<AccountDto>> listAllAccounts(){
        List<services.dtos.AccountDto> serviceAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(serviceAccounts.stream().map(sa -> fromService(sa)).collect(Collectors.toList()));
    }
}
