package com.example.kosherja.Model.RentPayment;

import com.example.kosherja.Model.User.Student;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;
    private String studentId;
    private BigDecimal amount;
    private LocalDate paymentDate;
    //number of paid months
    private int numberOfMonthsPaid;
    private int numberOfMonthsPaidTotal;
    private int remainingNoOfMonthsToPay;


}
