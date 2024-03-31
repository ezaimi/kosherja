package com.example.kosherja.Repo.PaymentRepo;

import com.example.kosherja.Model.RentPayment.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    Payment findTopByStudentIdOrderByPaymentDateDesc(String studentId);

}
