package br.com.gruporas.DesafioTecnico.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(
    name = "tabela_tarifa",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"num_faixa_de_consumo", "id_consumo"})
    }
)
public class TabelaTarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_de_vigencia")
    private LocalDate data_de_vigencia;
    
    @Column(name = "num_faixa_de_consumo")
    private Integer num_faixa_de_consumo;
    
    @Column(name = "valor_faixa_de_consumo")
    private BigDecimal valor_faixa_de_consumo;
    
    @ManyToOne
    @JoinColumn(name = "id_consumo")
    private TabelaConsumo id_consumo;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public LocalDate getDataDeVigencia() {
        return this.data_de_vigencia;
    }

    public void setDataDeVigencia(LocalDate data) {
        this.data_de_vigencia = data;
    }
    
    public Integer getNumFaixaConsumo() {
    	return this.num_faixa_de_consumo;
    }
    
    public void setNumFaixaConsumo(Integer num_faixa_de_consumo) {
    	this.num_faixa_de_consumo = num_faixa_de_consumo;
    }
    
    public BigDecimal getValorFaixaConsumo() {
    	return this.valor_faixa_de_consumo;
    }
    
    public void setValorFaixaConsumo(BigDecimal valor_faixa_de_consumo) {
    	this.valor_faixa_de_consumo = valor_faixa_de_consumo;
    }
    
    public TabelaConsumo getConsumo() {
    	return this.id_consumo;
    }
    
    public void setConsumo(TabelaConsumo id_consumo) {
    	this.id_consumo = id_consumo;
    }
    
}