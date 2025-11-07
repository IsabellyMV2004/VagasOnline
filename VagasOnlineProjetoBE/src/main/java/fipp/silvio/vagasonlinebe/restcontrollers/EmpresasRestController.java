package fipp.silvio.vagasonlinebe.restcontrollers;

import fipp.silvio.vagasonlinebe.entities.Empresa;
import fipp.silvio.vagasonlinebe.services.EmpresasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/empresas")
@CrossOrigin
public class EmpresasRestController {

    @Autowired
    private EmpresasService empresasService;

    @GetMapping
    public ResponseEntity<Object> getAllEmpresas() {
        return ResponseEntity.ok(empresasService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmpresaById(@PathVariable String id) {
        return ResponseEntity.ok(empresasService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createEmpresa(@RequestBody Empresa empresa) {
        empresasService.create(empresa);
        return ResponseEntity.ok("Empresa criada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmpresa(@PathVariable String id, @RequestBody Empresa empresa) {
        empresasService.update(id, empresa);
        return ResponseEntity.ok("Empresa atualizada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmpresa(@PathVariable String id) {
        empresasService.delete(id);
        return ResponseEntity.ok("Empresa removida com sucesso!");
    }
}
