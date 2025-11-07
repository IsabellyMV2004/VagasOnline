package fipp.silvio.vagasonlinebe.restcontrollers;

import fipp.silvio.vagasonlinebe.entities.Cargo;
import fipp.silvio.vagasonlinebe.services.CargosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/cargos")
@CrossOrigin
public class CargosRestController {

    @Autowired
    private CargosService cargosService;

    @GetMapping
    public ResponseEntity<Object> getAllCargos() {
        return ResponseEntity.ok(cargosService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCargoById(@PathVariable String id) {
        return ResponseEntity.ok(cargosService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createCargo(@RequestBody Cargo cargo) {
        cargosService.create(cargo);
        return ResponseEntity.ok("Cargo criado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCargo(@PathVariable String id, @RequestBody Cargo cargo) {
        cargosService.update(id, cargo);
        return ResponseEntity.ok("Cargo atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCargo(@PathVariable String id) {
        cargosService.delete(id);
        return ResponseEntity.ok("Cargo removido com sucesso!");
    }
}
