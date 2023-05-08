package com.crud.accenture.service;

import java.util.List;
import java.util.Optional;

import com.crud.accenture.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.accenture.Empresa;


@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> buscarTodasEmpresas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public Empresa criarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa atualizarEmpresa(Long id, Empresa empresaAtualizada) {
        Optional<Empresa> empresaOpt = empresaRepository.findById(id);
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            empresa.setNomeFantasia(empresaAtualizada.getNomeFantasia());
            empresa.setCnpj(empresaAtualizada.getCnpj());
            empresa.setCep(empresaAtualizada.getCep());
            empresaRepository.save(empresa);
            return empresa;
        } else {
            return null;
        }
    }

    public void deletarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

}
