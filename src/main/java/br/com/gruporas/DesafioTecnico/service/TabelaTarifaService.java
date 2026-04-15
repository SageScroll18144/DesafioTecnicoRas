package br.com.gruporas.DesafioTecnico.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gruporas.DesafioTecnico.model.TabelaTarifa;
import br.com.gruporas.DesafioTecnico.repository.TabelaTarifaRepository;
import jakarta.transaction.Transactional;

@Service
public class TabelaTarifaService {

    @Autowired
    private TabelaTarifaRepository repository;

    public TabelaTarifa salvar(TabelaTarifa tarifa) {
        return repository.save(tarifa);
    }
    
    public List<TabelaTarifa> listarTodos() {
        return repository.findAll();
    }
    
    @Transactional
    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
    
    public Map<String, Object> gerarRelatorioCalculo(String categoria, Integer consumo) {
    	Map<String, Object> ans = new LinkedHashMap<>();
    	
    	BigDecimal valor_faixa_1 = repository.findByValorFaixa1Industrial();
    	BigDecimal valor_faixa_2 = repository.findByValorFaixa2Industrial();
    	BigDecimal valor_faixa_3 = repository.findByValorFaixa3Industrial();
    	BigDecimal valor_faixa_4 = repository.findByValorFaixa4Industrial();
    	
    	BigDecimal valor_total = BigDecimal.ZERO;
    	
    	List<Map<String, Object>> detalhamento = new ArrayList<>();
    	int intervalos_inicio[] = {0, 11, 21, 31};
    	int intervalos_fim[] = {10, 20, 30, 99999};
    	BigDecimal valores_faixa[] = {valor_faixa_1, valor_faixa_2, valor_faixa_3, valor_faixa_4};
    	
     	for (int i = 0;i < ((consumo.intValue() / 10) < 3 ? (consumo.intValue() / 10) : 3); i++) {
    		Map<String, Object> item = new LinkedHashMap<>();
    		BigDecimal subtotal = valores_faixa[i].multiply(BigDecimal.TEN);
    		
    		item.put("faixa", Map.of("inicio", intervalos_inicio[i], "fim", intervalos_fim[i]));
            item.put("m3Cobrados", 10);
            item.put("valorUnitario", valores_faixa[i]);
            item.put("subtotal", subtotal);
            
            valor_total = valor_total.add(subtotal);
            detalhamento.add(item);
    	}
     	Map<String, Object> item = new LinkedHashMap<>();
     	BigDecimal valor_unitario  = (consumo.intValue() <= 39) ? valores_faixa[(consumo.intValue() / 10)] : valores_faixa[3];
     	BigDecimal subtotal  = (consumo.intValue() <= 39) ? valores_faixa[(consumo.intValue() / 10)].multiply(BigDecimal.valueOf(consumo.intValue() % 10)) : valores_faixa[3].multiply(BigDecimal.valueOf(consumo.intValue() - 30));
     	int m3Cobrados = (consumo.intValue() <= 39) ? (consumo.intValue() % 10) : (consumo.intValue() - 30);
     			
     	item.put("faixa", Map.of("inicio", intervalos_inicio[(Integer.min(consumo.intValue() / 10, 3))], "fim", intervalos_fim[(Integer.min(consumo.intValue() / 10, 3))]));
     	item.put("m3Cobrados", m3Cobrados);
        item.put("valorUnitario", valor_unitario);
        item.put("subtotal", subtotal);
        
        valor_total = valor_total.add(subtotal);
        detalhamento.add(item);
     	
    	ans.put("categoria", categoria);
        ans.put("consumoTotal", consumo);
        ans.put("valorTotal", valor_total);
        ans.put("detalhamento", detalhamento);

        return ans;	
    }
}