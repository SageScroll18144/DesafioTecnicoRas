package br.com.gruporas.DesafioTecnico.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gruporas.DesafioTecnico.model.TabelaTarifa;
import br.com.gruporas.DesafioTecnico.service.TabelaTarifaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TabelaTarifaController {

    @Autowired
    private TabelaTarifaService service;
    
    @GetMapping("/api/tabelas-tarifarias")
    public List<TabelaTarifa> listarTodos() {
        return service.listarTodos();
    }
    
    @PostMapping("/api/tabelas-tarifarias")
    public TabelaTarifa salvar(@RequestBody TabelaTarifa consumo) {
        return service.salvar(consumo);
    }
    
    @DeleteMapping("/api/tabelas-tarifarias/{id}")
    public void delete(@PathVariable Long id) {
        service.deletarPorId(id);
    }
    
    @PostMapping("/api/calculus")
    public Map<String, Object> calcular(@RequestBody Map<String, Object> body) {
        
        String categoria = (String) body.get("categoria");
        Integer consumo  = (Integer) body.get("consumo");

        return service.gerarRelatorioCalculo(categoria, consumo);
    }
}