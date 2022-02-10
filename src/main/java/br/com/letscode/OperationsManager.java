package br.com.letscode;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@ToString
public class OperationsManager {

    private List<String> listOperation;
    private Map<String, Set<OperacaoBancaria>> operacoes = new HashMap();
    private Comparator<OperacaoBancaria> operacaoComp = new OperacaoComparator();

    public OperationsManager(List<String> listOperation) {
        this.listOperation = listOperation;
    }

    public void createOperations(){

        for (int i = 1; i < listOperation.size(); i++) {

            String[] separeteData = this.listOperation.get(i).split(",");

            ContaBancaria conta = new ContaBancaria(separeteData[1], separeteData[2], separeteData[3], separeteData[4]);

            BigDecimal valor = new BigDecimal(separeteData[7]);
            OperacaoBancaria operacao = new OperacaoBancaria(separeteData[5], separeteData[6], valor, separeteData[0], conta);

            if(!this.operacoes.containsKey(separeteData[1])){
                Set<OperacaoBancaria> listOperations = new TreeSet<>(operacaoComp);
                this.operacoes.put(separeteData[1], listOperations);
                this.operacoes.get(separeteData[1]).add(operacao);
            }else{
                this.operacoes.get(separeteData[1]).add(operacao);
            }
        }
    }

    public void showOperations(){
        System.out.println(operacoes);
        operacoes.forEach((key, list) -> {
            System.out.println(key);
            System.out.println(list.size());
        });
    }
}
