package br.com.genuino.hrpayroll.resources;

import br.com.genuino.hrpayroll.entities.Payment;
import br.com.genuino.hrpayroll.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable("workerId") Long id, @PathVariable("days") Integer days ){
        Payment payment = service.getPayment(id, days);
        return  ResponseEntity.ok(payment);
    }
}
