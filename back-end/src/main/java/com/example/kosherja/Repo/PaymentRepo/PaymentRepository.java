package com.example.kosherja.Repo.PaymentRepo;

import com.example.kosherja.Model.RentPayment.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    Payment findTopByStudentIdOrderByPaymentDateAsc(String studentId);

    List<Payment> findByStudentId(String studentId);
}
