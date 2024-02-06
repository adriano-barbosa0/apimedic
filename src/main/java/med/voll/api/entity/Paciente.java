package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DadosAtualiza;
import med.voll.api.dto.DadosPaciente;
import med.voll.api.dto.endereco.Endereco;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pacientes")
@Entity(name = "Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;

   public Paciente(DadosPaciente dados){
       this.ativo = true;
       this.nome = dados.nome();
       this.email = dados.email();
       this.cpf = dados.cpf();
       this.telefone = dados.telefone();
       this.endereco = new Endereco(dados.endereco());
   }

   public void AtualizaInformacoes(DadosAtualiza dados) {
       if (dados.nome() != null) {
           this.nome = dados.nome();
       }
       if (dados.telefone() != null) {
           this.telefone = dados.telefone();
       }
       if (dados.endereco() != null) {
           this.endereco.AtualizaEndereco(dados.endereco());
       }
   }
   public void excluir(){
       this.ativo = false;
    }
}

