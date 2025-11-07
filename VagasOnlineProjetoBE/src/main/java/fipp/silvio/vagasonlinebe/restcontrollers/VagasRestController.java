package fipp.silvio.vagasonlinebe.restcontrollers;

import fipp.silvio.vagasonlinebe.entities.Interesse;
import fipp.silvio.vagasonlinebe.entities.Vaga;
import fipp.silvio.vagasonlinebe.services.VagasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/vagas")
@CrossOrigin
public class VagasRestController {

    @Autowired
    private VagasService vagasService;

    @GetMapping
    public ResponseEntity<Object> getAllVagas() {
        return ResponseEntity.ok(vagasService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVagaById(@PathVariable String id) {
        return ResponseEntity.ok(vagasService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createVaga(@RequestBody Vaga vaga) {
        vagasService.create(vaga);
        return ResponseEntity.ok("Vaga criada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVaga(@PathVariable String id, @RequestBody Vaga vaga) {
        vagasService.update(id, vaga);
        return ResponseEntity.ok("Vaga atualizada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVaga(@PathVariable String id) {
        vagasService.delete(id);
        return ResponseEntity.ok("Vaga removida com sucesso!");
    }

    @PostMapping("/interesse")
    public ResponseEntity<Object> registrarInteresse(@RequestBody Interesse interesse) {
        vagasService.registrarInteresse(interesse);
        return ResponseEntity.ok("Interesse registrado com sucesso!");
    }
}
