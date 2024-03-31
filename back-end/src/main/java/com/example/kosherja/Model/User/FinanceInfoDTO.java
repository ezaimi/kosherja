package com.example.kosherja.Model.User;

import com.example.kosherja.Model.UserDetails.Contract;
import lombok.Data;
import com.example.kosherja.Model.Facilities.Room;
import com.example.kosherja.Model.UserDetails.Documents;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FinanceInfoDTO {

    private Student student;
    private Contract contract;
    private Room room;
    private BigDecimal amountDue;

    private BigDecimal roomPrice;
    private LocalDate lastPaymentDate;
    private LocalDate nextPaymentDate;
    private int remainingMonths;

}
