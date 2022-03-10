package br.com.genuino.hrpayroll.services;

import br.com.genuino.hrpayroll.dto.Worker;
import br.com.genuino.hrpayroll.entities.Payment;
import br.com.genuino.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    @Value("${hr-worker.host}")
    private String host;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(Long workerId, int days){
        Map<String, String> uriVariables =  new HashMap<>();
        uriVariables.put("id", workerId.toString());
        Worker worker = restTemplate.getForObject(host + "/workers/{id}", Worker.class, uriVariables);
        if (worker != null)
            return new Payment(worker.getName(), worker.getDailyIncome(), days);
        else
            return null;
    }
}
