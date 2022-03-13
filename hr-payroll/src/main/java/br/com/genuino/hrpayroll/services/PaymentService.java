package br.com.genuino.hrpayroll.services;

import br.com.genuino.hrpayroll.dto.Worker;
import br.com.genuino.hrpayroll.entities.Payment;
import br.com.genuino.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(Long workerId, int days){
        Worker worker = workerFeignClient.findById(workerId).getBody();
        if (worker != null)
            return new Payment(worker.getName(), worker.getDailyIncome(), days);
        else
            return null;
    }
}
