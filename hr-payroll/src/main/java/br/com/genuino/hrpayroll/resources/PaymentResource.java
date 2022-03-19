package br.com.genuino.hrpayroll.resources;

import br.com.genuino.hrpayroll.entities.Payment;
import br.com.genuino.hrpayroll.services.PaymentService;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    @Autowired
    private PaymentService service;
    private static final String PAYMENT_FALLBACK = "paymentFallback";


    @GetMapping("/{workerId}/days/{days}")
    @CircuitBreaker(name = PAYMENT_FALLBACK, fallbackMethod = "getPaymentAlternative")
    public ResponseEntity<Payment> getPayment(@PathVariable("workerId") Long id, @PathVariable("days") Integer days ){
        Payment payment = service.getPayment(id, days);
        return  ResponseEntity.ok(payment);
    }

    public ResponseEntity<Payment> getPaymentAlternative(Long id, Integer days, Throwable t ){
        Payment payment = new Payment("Brann", 400.0, days);
        return  ResponseEntity.ok(payment);
    }
}
