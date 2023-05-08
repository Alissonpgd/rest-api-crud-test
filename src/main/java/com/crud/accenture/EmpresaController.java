package com.crud.accenture;

import java.util.List;
import java.util.Optional;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.crud.accenture.service.EmpresaService;

@Getter
@Setter
@RestController
@AllArgsConstructor
@Data
@NoArgsConstructor
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;


	@GetMapping("/empresa")
	public List<Empresa> getAllEmpresa() {
		return empresaService.buscarTodasEmpresas();
	}
	
	@GetMapping("/empresa/{id}")
	public Optional<Empresa> getEmpresaById(@PathVariable Long id) {
		return empresaService.buscarEmpresaPorId(id);
	}
	
	@PutMapping("/empresa/{id}")
	public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @RequestBody Empresa empresaData) {
	    Optional<Empresa> empresaOpt = empresaService.buscarEmpresaPorId(id);
	    if (empresaOpt.isPresent()) {
	        Empresa empresa = empresaOpt.get();
	        empresa.setNomeFantasia(empresaData.getNomeFantasia());
	        empresa.setCnpj(empresaData.getCnpj());
	        empresa.setCep(empresaData.getCep());
			empresaService.criarEmpresa(empresa);
	        return ResponseEntity.ok(empresa);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping("/empresa")
	public Empresa saveEmpresa(@RequestBody Empresa empresa) {
		return empresaService.criarEmpresa(empresa);
	}
	
	@DeleteMapping("/empresa/{id}")
	public void deleteEmpresa(@PathVariable Long id) {
		empresaService.deletarEmpresa(id);
	}
	
}

