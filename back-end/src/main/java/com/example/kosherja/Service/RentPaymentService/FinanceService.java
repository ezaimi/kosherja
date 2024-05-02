package com.example.kosherja.Service.RentPaymentService;

import com.example.kosherja.Model.Facilities.Room;
import com.example.kosherja.Model.RentPayment.Payment;
import com.example.kosherja.Model.User.FinanceInfoDTO;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Model.UserDetails.Contract;
import com.example.kosherja.Repo.FacilitiesRepo.RoomRepo;
import com.example.kosherja.Repo.PaymentRepo.PaymentRepository;
import com.example.kosherja.Repo.UserDetailsRepo.ContractRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class FinanceService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private ContractRepo contractRepo;

    @Autowired
    private PaymentRepository paymentRepository;

    public FinanceInfoDTO getFinanceInfo(String studentId) {
        Student student = studentRepo.findById(studentId).orElse(null);
        if (student == null) {
            return null;
        }

        Room room = roomRepo.findById(student.getRoomId()).orElse(null);
        if (room == null) {
            return null;
        }

        Contract contract = contractRepo.findById(student.getContractId()).orElse(null);
        if (contract == null) {
            return null; // or throw an exception
        }


        FinanceInfoDTO financeInfoDTO = new FinanceInfoDTO();
        financeInfoDTO.setStudent(student);
        financeInfoDTO.setRoom(room);
        financeInfoDTO.setContract(contract);
        financeInfoDTO.setRoomPrice(BigDecimal.valueOf(room.getPrice()));


        //retrieving all payments
        List<Payment> payments = paymentRepository.findAll();

        // Filter payments for the given student
        List<Payment> studentPayments = payments.stream()
                .filter(payment -> studentId.equals(payment.getStudentId()))
                .toList();

        // Calculate total number of months from payments
        int numberOfMonthsPaid = studentPayments.stream()
                .mapToInt(Payment::getNumberOfMonthsPaid)
                .sum();

        // checking if the student has made any payment yet (if it is not the first payment)
        if (student.getLastPaymentDate() != null && student.getNextPaymentDate() != null) {
            financeInfoDTO.setLastPaymentDate(student.getLastPaymentDate());
            financeInfoDTO.setNextPaymentDate(student.getNextPaymentDate());

            // calc remainingMonths
            LocalDate currentDate = LocalDate.now();
            //long monthsUntilNextPayment = ChronoUnit.MONTHS.between(currentDate, student.getNextPaymentDate());
            long remainingMonths = contract.getDuration() - numberOfMonthsPaid;

            financeInfoDTO.setRemainingMonths((int) remainingMonths);

            // calc amountDue
            BigDecimal roomPrice = BigDecimal.valueOf(room.getPrice());

            BigDecimal amountDue = BigDecimal.valueOf(room.getPrice())
                    .multiply(BigDecimal.valueOf(contract.getDuration()))
                    .subtract(roomPrice.multiply(BigDecimal.valueOf(numberOfMonthsPaid)));
            financeInfoDTO.setAmountDue(amountDue);
        } else {
            //  lastPaymentDate and nextPaymentDate to default values
            financeInfoDTO.setLastPaymentDate(null);
            financeInfoDTO.setNextPaymentDate(null);

            // calc remainingMonths and amountDue based on contract duration
            financeInfoDTO.setRemainingMonths(contract.getDuration());
            BigDecimal amountDue = BigDecimal.valueOf(room.getPrice()).multiply(BigDecimal.valueOf(contract.getDuration()));
            financeInfoDTO.setAmountDue(amountDue);
        }

        return financeInfoDTO;
    }

}
