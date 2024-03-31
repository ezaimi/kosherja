package com.example.kosherja.Service.RentPaymentService;

import com.example.kosherja.Model.Facilities.Room;
import com.example.kosherja.Model.RentPayment.Payment;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Model.UserDetails.Contract;
import com.example.kosherja.Repo.FacilitiesRepo.RoomRepo;
import com.example.kosherja.Repo.PaymentRepo.PaymentRepository;
import com.example.kosherja.Repo.UserDetailsRepo.ContractRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PaymentService {

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RoomRepo roomRepository;

    @Autowired
    private ContractRepo contractRepo;

    public void makePayment(String studentId, int numberOfMonths) {
        // Retrieve student and update payment information
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            return;
        }

        Room room = roomRepository.findById(student.getRoomId()).orElse(null);
        if (room == null) {
            return;
        }

        // Retrieve contract information
        Contract contract = contractRepo.findById(student.getContractId()).orElse(null);
        if (contract == null) {
            return;
        }

        if (student.getLastPaymentDate() == null || student.getNextPaymentDate() == null) {
            // If not present, set default values or calculate them based on some logic
            student.setLastPaymentDate(LocalDate.now());
            student.setNextPaymentDate(LocalDate.now());
        }

        Payment lastPayment = paymentRepository.findTopByStudentIdOrderByPaymentDateDesc(studentId);
        int lastNumberOfMonths = 0;
        if (lastPayment != null) {
            lastNumberOfMonths = lastPayment.getNumberOfMonths();
        }

        // Calculate the payment amount based on the contract duration
        int contractDuration = contract.getDuration();
        BigDecimal roomPrice = BigDecimal.valueOf(room.getPrice());
        BigDecimal paymentAmount = roomPrice.multiply(BigDecimal.valueOf(numberOfMonths));

        // Update last payment date and next payment date
        student.setLastPaymentDate(LocalDate.now());
        int remainingMonths = contract.getDuration() - numberOfMonths;
        student.setNextPaymentDate(student.getNextPaymentDate().plusMonths(1));


        // Save the updated student information
        studentRepository.save(student);

        // Record the payment in the payment history
        Payment payment = new Payment();
        payment.setStudentId(studentId);
        payment.setAmount(paymentAmount);
        payment.setPaymentDate(LocalDate.now());
        payment.setNumberOfMonths(numberOfMonths + lastNumberOfMonths);
        payment.setRemainingNoOfMonths(remainingMonths);
        // Save the payment entity to the database
        paymentRepository.save(payment);
    }




}
