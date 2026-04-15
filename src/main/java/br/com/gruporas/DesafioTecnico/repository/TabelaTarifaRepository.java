package br.com.gruporas.DesafioTecnico.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.gruporas.DesafioTecnico.model.TabelaTarifa;

@Repository
public interface TabelaTarifaRepository extends JpaRepository<TabelaTarifa, Long> {

    @Query("SELECT t.valor_faixa_de_consumo "
            + "FROM TabelaTarifa t "
            + "WHERE t.id_consumo.categoria LIKE 'INDUSTRIAL' "
            + "AND t.num_faixa_de_consumo = 1")
    public BigDecimal findByValorFaixa1Industrial();

    @Query("SELECT t.valor_faixa_de_consumo "
            + "FROM TabelaTarifa t "
            + "WHERE t.id_consumo.categoria LIKE 'INDUSTRIAL' "
            + "AND t.num_faixa_de_consumo = 2")
    public BigDecimal findByValorFaixa2Industrial();

    @Query("SELECT t.valor_faixa_de_consumo "
            + "FROM TabelaTarifa t "
            + "WHERE t.id_consumo.categoria LIKE 'INDUSTRIAL' "
            + "AND t.num_faixa_de_consumo = 3")
    public BigDecimal findByValorFaixa3Industrial();

    @Query("SELECT t.valor_faixa_de_consumo "
            + "FROM TabelaTarifa t "
            + "WHERE t.id_consumo.categoria LIKE 'INDUSTRIAL' "
            + "AND t.num_faixa_de_consumo = 4")
    public BigDecimal findByValorFaixa4Industrial();
}