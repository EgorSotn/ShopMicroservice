package ru.sotn.transactionservice.controller;

import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sotn.transactionservice.service.TransactionService;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class TransactionOrderController {
    private final TransactionService transactionService;

    @PostMapping("/api/charge")
    public Charge startTransaction(@RequestParam(name = "email") String email, HttpServletRequest request){
        String token = request.getHeader("token");

        try {
            return transactionService.charge(email,token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
