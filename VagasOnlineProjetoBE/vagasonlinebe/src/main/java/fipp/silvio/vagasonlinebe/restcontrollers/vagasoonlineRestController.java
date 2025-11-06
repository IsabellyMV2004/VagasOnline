package fipp.silvio.vagasonlinebe.restcontrollers;

import fipp.silvio.vagasonlinebe.services.VagasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "apis")
public class vagasoonlineRestController {
    @Autowired
    VagasService vagasService;

    @GetMapping("vagas/get- all")
    public ResponseEntity<Object> getAllVagas(){
        return ResponseEntity.ok(vagasService.getAll());
    }
}
