package com.crud.accenture;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@Data
@NoArgsConstructor
public class FornecedorController {
	
	@Autowired
	FornecedorService fornecedorService;
	
	@GetMapping("/fornecedor")
	public List<Fornecedor> getAllFornecedor() {
		return fornecedorService.buscarTodosFornecedores();
	}
	
	@GetMapping("/fornecedor/{id}")
	public Optional<Fornecedor> getFornecedorById(@PathVariable Long id) {
		return fornecedorService.buscarFornecedorPorId(id);
	}
	
	@PostMapping("/fornecedor")
	public Fornecedor saveFornecedor(@RequestBody Fornecedor fornecedor) {
		return fornecedorService.criarFornecedor(fornecedor);
	}

	@PutMapping("/fornecedor/{id}")
	public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedorData) {
		Optional<Fornecedor> fornecedorOpt = fornecedorService.buscarFornecedorPorId(id);
		if (fornecedorOpt.isPresent()) {
			Fornecedor fornecedor = fornecedorOpt.get();
			fornecedor.setNome(fornecedorData.getNome());
			fornecedor.setCnpj_cpf(fornecedorData.getCnpj_cpf());
			fornecedor.setRg(fornecedorData.getRg());
			fornecedor.setData_nascimento(fornecedorData.getData_nascimento());
			fornecedor.setEmail(fornecedorData.getEmail());
			fornecedor.setCep(fornecedorData.getCep());
			fornecedorService.criarFornecedor(fornecedor);
			return ResponseEntity.ok(fornecedor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/fornecedor/{id}")
	public void deleteFornecedor(@PathVariable Long id) {
		fornecedorService.deletarFornecedor(id);
	}

}
