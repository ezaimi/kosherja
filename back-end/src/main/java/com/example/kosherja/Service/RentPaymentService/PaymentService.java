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
import java.util.List;

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

    public void makePayment(String studentId, int numberOfMonthsPaid) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            return;
        }

        Room room = roomRepository.findById(student.getRoomId()).orElse(null);
        if (room == null) {
            return;
        }

        Contract contract = contractRepo.findById(student.getContractId()).orElse(null);
        if (contract == null) {
            return;
        }

        if (student.getLastPaymentDate() == null || student.getNextPaymentDate() == null) {
            // if not present, set default values or calculate them based on some logic
            student.setLastPaymentDate(LocalDate.now());
            student.setNextPaymentDate(LocalDate.now());

        }

        List<Payment> payments = paymentRepository.findByStudentId(studentId);

        // calc the total no of months paid by the student
        int totalMonthsPaid = payments.stream().mapToInt(Payment::getNumberOfMonthsPaid).sum();


        //calc the payment amount based on the contract duration
        int contractDuration = contract.getDuration();
        BigDecimal roomPrice = BigDecimal.valueOf(room.getPrice());
        BigDecimal amountPaid = roomPrice.multiply(BigDecimal.valueOf(numberOfMonthsPaid));

        //calc the remaining no of months to pay
        int remainingMonthsToPay = contractDuration - (totalMonthsPaid + numberOfMonthsPaid);

//
//        // Update last payment date and next payment date
        student.setLastPaymentDate(LocalDate.now());
//        int remainingMonths = contractDuration - (numberOfMonthsPaid + lastNumberOfMonths);
        student.setNextPaymentDate(student.getNextPaymentDate().plusMonths(1));
//        student.setLastPaymentDate(LocalDate.now());
//
//        // Save the updated student information
        studentRepository.save(student);
//
//        // Record the payment in the payment history
        Payment payment = new Payment();
        payment.setStudentId(studentId);
        payment.setAmount(amountPaid);
        payment.setPaymentDate(LocalDate.now());
        payment.setNumberOfMonthsPaid(numberOfMonthsPaid);
        payment.setNumberOfMonthsPaidTotal(totalMonthsPaid + numberOfMonthsPaid);
        payment.setRemainingNoOfMonthsToPay(remainingMonthsToPay);
//        // Save the payment entity to the database
        paymentRepository.save(payment);
    }


    //method in student dashboard to show all the payments a student has made (bills)
    public List<Payment> getAllPayments(String studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));


        return paymentRepository.findByStudentId(studentId);

    }
}
