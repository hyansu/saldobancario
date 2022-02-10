package br.com.letscode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class FileManager {

    private String fileName;

    public List<String> readFile(){
        try {
            Path path = Paths.get(this.fileName);
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createBankStatement(Map<String, Set<OperacaoBancaria>> operationlist){

        StringBuilder fileContent = new StringBuilder("");

        for (String key: operationlist.keySet()) {
            Path filePath = Paths.get("extrato/"+ key +".txt");
            Path directory = Paths.get("extrato/");

            fileContent.append("\n").append("EXTRATO - ID da Conta Bancária: ").append(key).append("\n\n");
            fileContent.append(listOperationsOnFile(operationlist.get(key)));

            try {
                if(directory.toFile().isDirectory()){
                    Files.writeString(filePath, fileContent);
                }else{
                    Files.createDirectories(directory);
                    Files.writeString(filePath, fileContent);
                }

                fileContent.delete(0, fileContent.length());
                System.out.println("Arquivo " +filePath.getFileName() + " gerado com sucesso!");

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public String listOperationsOnFile(Set<OperacaoBancaria> operations){

        StringBuilder content = new StringBuilder("Operação Bancária");

        content.append("\n")
                .append(alignString("Data e Hora", 20))
                .append(alignString("Operador", 15))
                .append(alignString("Banco", 12))
                .append(alignString("Agência", 9))
                .append(alignString("Conta", 11))
                .append(alignString("Tipo", 11))
                .append(alignString("Valor", 13))
                .append("\n");

        int count = 1;
        BigDecimal saldo = new BigDecimal(0);
        for(OperacaoBancaria item : operations){
            content.append(item.getDataHoraOperacao()).append(" | ")
                    .append(alignString(item.getOperador(), 12)).append(" | ")
                    .append(item.getContaBancaria().getBanco()).append(" | ")
                    .append(item.getContaBancaria().getAgencia()).append(" | ")
                    .append(item.getContaBancaria().getConta()).append(" | ")
                    .append(alignString(item.getTipo(), 8)).append(" | ")
                    .append(item.getValor()).append("\n");

            if(count < operations.size() ){
                saldo = Objects.equals(item.getTipo(), "DEPOSITO") ? saldo.add(item.getValor()): saldo.subtract(item.getValor());
                saldo = saldo.add(item.getValor());
                count++;
            }else{
                saldo = Objects.equals(item.getTipo(), "DEPOSITO") ? saldo.add(item.getValor()): saldo.subtract(item.getValor());
                content.append("\n").append("Saldo:    ").append(saldo).append("\n");
            }
        }

        return content.toString();

    }

    public String alignString(String word, int width){
        int addspaces = width - word.length();
        return word + " ".repeat(addspaces);
    }

    public void writeFile(String filename, Set<OperacaoBancaria> operations){

    }
}
