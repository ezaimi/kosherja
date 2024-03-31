package com.example.kosherja.Controller.RentPaymentController;

import com.example.kosherja.Service.RentPaymentService.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/finance")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/makePayment/{studentId}/{numberOfMonths}")
    public ResponseEntity<String> makePayment(@PathVariable String studentId, @PathVariable int numberOfMonths) {
        try {
            paymentService.makePayment(studentId, numberOfMonths);
            return ResponseEntity.ok("Payment successful.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed.");
        }
    }


}