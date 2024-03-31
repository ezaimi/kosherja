package com.example.kosherja.Controller.RentPaymentController;

import com.example.kosherja.Model.User.FinanceInfoDTO;
import com.example.kosherja.Service.RentPaymentService.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/showInfo/{studentId}")
    public FinanceInfoDTO getFinanceInfo(@PathVariable String studentId) {
        return financeService.getFinanceInfo(studentId);
    }
}
