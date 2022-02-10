package br.com.letscode;

import java.util.Comparator;

public class OperacaoComparator implements Comparator<OperacaoBancaria> {

    public int compare(OperacaoBancaria o1, OperacaoBancaria o2) {
        return (o1.getDataHoraOperacao().compareTo(o2.getDataHoraOperacao()));
    }
}
