package fipp.silvio.vagasonlinebe.restcontrollers;

import fipp.silvio.vagasonlinebe.entities.Cargo;
import fipp.silvio.vagasonlinebe.entities.Empresa;
import fipp.silvio.vagasonlinebe.entities.Interesse;
import fipp.silvio.vagasonlinebe.entities.Vaga;
import fipp.silvio.vagasonlinebe.services.CargosService;
import fipp.silvio.vagasonlinebe.services.EmpresasService;
import fipp.silvio.vagasonlinebe.services.VagasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/vagas")
@CrossOrigin
public class VagasOnlineRestController {

    @Autowired
    private VagasService vagasService;
    @Autowired
    private EmpresasService empresasService;
    @Autowired
    private CargosService cargosService;


    /***************************************    VAGA **********************************/

    // GET - lista todas as vagas
    @GetMapping
    public ResponseEntity<Object> getAllVagas() {
        return ResponseEntity.ok(vagasService.getAll());
    }

    // GET - busca uma vaga específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getVagaById(@PathVariable String id) {
        return ResponseEntity.ok(vagasService.getById(id));
    }

    // POST - cria nova vaga
    @PostMapping
    public ResponseEntity<Object> createVaga(@RequestBody Vaga vaga) {
        vagasService.create(vaga);
        return ResponseEntity.ok("Vaga criada com sucesso!");
    }

    // PUT - atualiza vaga existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVaga(@PathVariable String id, @RequestBody Vaga vaga) {
        vagasService.update(id, vaga);
        return ResponseEntity.ok("Vaga atualizada com sucesso!");
    }

    // DELETE - remove vaga
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVaga(@PathVariable String id) {
        vagasService.delete(id);
        return ResponseEntity.ok("Vaga removida com sucesso!");
    }

    @PostMapping("vagas/interesse")
    public ResponseEntity<Object> registrarInteresse(@RequestBody Interesse interesse) {
        vagasService.registrarInteresse(interesse);
        return ResponseEntity.ok("Interesse registrado com sucesso!");
    }


    /***************************************    EMPRESA    **********************************/

    // GET - lista todas as empresas
    @GetMapping
    public ResponseEntity<Object> getAllEmpresas() {
        return ResponseEntity.ok(empresasService.getAll());
    }

    // GET - busca uma empresa específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmpresaById(@PathVariable String id) {
        return ResponseEntity.ok(empresasService.getById(id));
    }

    // POST - cria nova empresa
    @PostMapping
    public ResponseEntity<Object> createEmpresa(@RequestBody Empresa empresa) {
        empresasService.create(empresa);
        return ResponseEntity.ok("Empresa criada com sucesso!");
    }

    // PUT - atualiza empresa existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmpresa(@PathVariable String id, @RequestBody Empresa empresa) {
        empresasService.update(id, empresa);
        return ResponseEntity.ok("Empresa atualizada com sucesso!");
    }

    // DELETE - remove empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmpresa(@PathVariable String id) {
        empresasService.delete(id);
        return ResponseEntity.ok("Empresa removida com sucesso!");
    }



    /***************************************    CARGO    **********************************/
    // GET - lista todas os cargos
    @GetMapping
    public ResponseEntity<Object> getAllCargos() {
        return ResponseEntity.ok(cargosService.getAll());
    }

    // GET - busca um cargo específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCargoById(@PathVariable String id) {
        return ResponseEntity.ok(cargosService.getById(id));
    }

    // POST - cria novo cargo
    @PostMapping
    public ResponseEntity<Object> createCargo(@RequestBody Cargo cargo) {
        cargosService.create(cargo);
        return ResponseEntity.ok("Cargo criado com sucesso!");
    }

    // PUT - atualiza cargo existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCargo(@PathVariable String id, @RequestBody Cargo cargo) {
        cargosService.update(id, cargo);
        return ResponseEntity.ok("Cargo atualizado com sucesso!");
    }

    // DELETE - remove cargo
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCargo(@PathVariable String id) {
        cargosService.delete(id);
        return ResponseEntity.ok("Cargo removido com sucesso!");
    }


}

