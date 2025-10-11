package br.com.todolist.gerenciador_de_tarefas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.todolist.gerenciador_de_tarefas.model.Tarefa;

// JpaRepository<Tarefa, Long>: Informamos que este repositório vai gerenciar
// a entidade 'Tarefa', e que o tipo da chave primária da Tarefa é 'Long'.
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // Spring Data JPA Query Methods: Mágica pura!
    // Apenas por declarar um método com este nome, o Spring Data JPA automaticamente
    // cria uma consulta SQL "SELECT * FROM tarefa WHERE descricao LIKE '%?%'".
    List<Tarefa> findByDescricaoContaining(String descricao);

    // Da mesma forma, este método cria a consulta "SELECT * FROM tarefa WHERE concluida = ?".
    // Será usado no endpoint para listar apenas as tarefas pendentes.
    List<Tarefa> findByConcluida(boolean concluida);
}