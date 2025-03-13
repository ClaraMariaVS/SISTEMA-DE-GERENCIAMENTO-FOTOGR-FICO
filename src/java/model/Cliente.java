
package model;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cliente {
    private int idCliente;
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private String telefone;
    private Date dataCadastro;
    private int status;
}
