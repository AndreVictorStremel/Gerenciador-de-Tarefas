package br.com.todolist.gerenciador_de_tarefas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// @Entity: Anotação mais importante. Diz ao JPA: "Esta classe representa uma tabela no banco de dados".
@Entity
public class Tarefa {

    // @Id: Define que o campo 'id' abaixo é a chave primária (Primary Key) da tabela.
    @Id
    // @GeneratedValue: Configura como a chave primária será gerada.
    // strategy = GenerationType.IDENTITY: Diz que o próprio banco de dados (PostgreSQL)
    // será responsável por gerar o valor do ID, auto-incrementando a cada nova tarefa.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // @Column: Usado para especificar detalhes da coluna no banco.
    // nullable = false: A coluna 'descricao' não pode ter valores nulos (NOT NULL).
    // length = 100: Define o tamanho máximo da coluna como 100 caracteres.
    @Column(nullable = false, length = 100)
    private String descricao;

    // Para campos simples como este, o @Column é opcional, mas podemos usá-lo para ser explícito.
    // nullable = false: A coluna 'concluida' não pode ser nula.
    @Column(nullable = false)
    private boolean concluida = false; // Valor padrão inicializado como 'false', conforme o requisito.

    @Column(nullable = false)
    private int prioridade;

    // Getters e Setters: São métodos públicos que permitem que outras partes do código
    // acessem e modifiquem os valores dos campos privados da classe. O Spring/JPA os utiliza
    // intensamente para manipular os dados da entidade.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}
