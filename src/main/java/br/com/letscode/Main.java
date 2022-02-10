package br.com.letscode;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        FileManager file = new FileManager("data.csv");
        List<String> fileContent = file.readFile();

        OperationsManager operations = new OperationsManager(fileContent);
        operations.createOperations();
        Map<String, Set<OperacaoBancaria>> operationList = operations.getOperacoes();
        file.createBankStatement(operationList);
    }
}
