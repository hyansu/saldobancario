package br.com.letscode;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ContaBancaria {

    private String id;
    private String banco;
    private String agencia;
    private String conta;


}

