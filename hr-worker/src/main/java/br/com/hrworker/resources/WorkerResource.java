package br.com.hrworker.resources;

import br.com.hrworker.entities.Worker;
import br.com.hrworker.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    @Autowired
    private WorkerRepository repository;


    @GetMapping
    public ResponseEntity<List<Worker>> findAll(){
        List<Worker> workers = repository.findAll();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable("id") Long id) {
        Optional<Worker> optionalWorker = repository.findById(id);
        return optionalWorker.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
