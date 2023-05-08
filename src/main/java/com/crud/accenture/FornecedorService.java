package com.crud.accenture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.accenture.service.EmpresaService;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;

    public List<Fornecedor> buscarTodosFornecedores() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarFornecedorPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor criarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor adicionarFornecedor(Fornecedor fornecedor) {
        Optional<Empresa> empresaOpt = empresaService.buscarEmpresaPorId(fornecedor.getEmpresa().getId());
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            List<Fornecedor> fornecedores = empresa.getFornecedores();
            fornecedores.add(fornecedor);
            empresa.setFornecedores(fornecedores);
            empresaService.criarEmpresa(empresa);

            // Verifica se a empresa está no Paraná
            if ("PR".equals(empresa.getEstado())) {
                // Verifica se o fornecedor é menor de idade
                if (fornecedor.isPessoaFisica()) {
                    if (fornecedor.getRg() == null || fornecedor.getData_nascimento() == null) {
                        throw new IllegalArgumentException("Para cadastro de pessoa física é necessário preencher o RG e a data de nascimento");
                    }

                    LocalDate hoje = LocalDate.now();
                    LocalDate dataNascimento = fornecedor.getData_nascimento();
                    if (Period.between(dataNascimento, hoje).getYears() < 18) {
                        throw new RuntimeException("Não é permitido cadastrar fornecedor pessoa física menor de idade no Paraná");
                    }
                }
            }

            return fornecedorRepository.save(fornecedor);
        } else {
            return null;
        }
    }



    public Fornecedor atualizarFornecedor(Long id, Fornecedor fornecedorAtualizada) {
        Optional<Fornecedor> fornecedorOpt = fornecedorRepository.findById(id);
        if (fornecedorOpt.isPresent()) {
            Fornecedor fornecedor = fornecedorOpt.get();
            fornecedor.setNome(fornecedorAtualizada.getNome());
            fornecedor.setCnpj_cpf(fornecedorAtualizada.getCnpj_cpf());
            fornecedor.setRg(fornecedorAtualizada.getRg());
            fornecedor.setData_nascimento(fornecedorAtualizada.getData_nascimento());
            fornecedor.setEmail(fornecedorAtualizada.getEmail());
            fornecedor.setCep(fornecedorAtualizada.getCep());
            fornecedorRepository.save(fornecedor);
            return fornecedor;
        } else {
            return null;
        }
    }

    public void deletarFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }

    private static final String CEP_API_URL = "http://cep.la/";

    public boolean validarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = CEP_API_URL + cep;

        try {
            // Faz a requisição à API
            String response = restTemplate.getForObject(url, String.class);

            // Verifica se a resposta contém o CEP buscado
            if (response.contains(cep)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // Caso ocorra algum erro na requisição, retorna falso
            return false;
        }
    }

}
