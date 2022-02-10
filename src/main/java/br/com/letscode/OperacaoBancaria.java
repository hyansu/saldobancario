package br.com.letscode;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class OperacaoBancaria {

    private String operador;
    private String tipo;
    private BigDecimal valor;
    private String dataHoraOperacao;
    private ContaBancaria contaBancaria;

    public String toString(){

        return "Operador: " + this.operador + "\n" +
                "Tipo: " + this.tipo + "\n" +
                "Valor: " + this.valor + "\n" +
                "Data e Hora: " + this.dataHoraOperacao + "\n" +
                "Conta Bancaria: " + this.contaBancaria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperacaoBancaria that = (OperacaoBancaria) o;

        if (!operador.equals(that.operador)) return false;
        if (!tipo.equals(that.tipo)) return false;
        if (!valor.equals(that.valor)) return false;
        if (!dataHoraOperacao.equals(that.dataHoraOperacao)) return false;
        return contaBancaria.equals(that.contaBancaria);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
