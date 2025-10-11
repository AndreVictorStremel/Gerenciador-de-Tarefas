package br.com.todolist.gerenciador_de_tarefas.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import de RequestMapping removido porque o contexto é definido em application.properties
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.todolist.gerenciador_de_tarefas.model.Tarefa;
import br.com.todolist.gerenciador_de_tarefas.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas-api")
public class TarefaController {

    // @Autowired: Injeção de dependência. Pede para o Spring criar uma instância
    // de TarefaRepository e "injetá-la" aqui para podermos usá-la.
    @Autowired
    private TarefaRepository tarefaRepository;

    // Endpoint: Criar uma nova tarefa
    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa novaTarefa) {
        // @RequestBody: Pega o JSON enviado no corpo da requisição e o transforma em um objeto Tarefa.
        Tarefa tarefaSalva = tarefaRepository.save(novaTarefa);
        return new ResponseEntity<>(tarefaSalva, HttpStatus.CREATED); // Retorna a tarefa salva e o status 201 Created
    }

    // Endpoint: Listar todas as tarefas (com filtro opcional)
    @GetMapping
    public ResponseEntity<List<Tarefa>> listar(@RequestParam(required = false) String descricao) {
        // @RequestParam: Pega um parâmetro da URL (ex: /?descricao=Estudar)
        List<Tarefa> tarefas;
        if (descricao == null) {
            tarefas = tarefaRepository.findAll(); // Se não houver filtro, busca todas.
        } else {
            tarefas = tarefaRepository.findByDescricaoContaining(descricao); // Se houver filtro, usa nosso método customizado.
        }

        if (tarefas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content se a lista estiver vazia
        }
        return ResponseEntity.ok(tarefas); // Retorna a lista e o status 200 OK.
    }

    // Endpoint: Obter uma tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obterPorId(@PathVariable Long id) {
        // @PathVariable: Pega o valor {id} da URL (ex: /123)
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        return tarefa.map(ResponseEntity::ok)
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Se não encontrar, retorna 404 Not Found
    }

    // Endpoint: Listar apenas as tarefas pendentes
    @GetMapping("/pendentes")
    public ResponseEntity<List<Tarefa>> listarPendentes() {
        List<Tarefa> tarefasPendentes = tarefaRepository.findByConcluida(false); // Usa nosso método customizado
        if (tarefasPendentes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(tarefasPendentes);
    }

    // Endpoint: Atualizar uma tarefa completa por ID
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        return tarefaRepository.findById(id)
                .map(tarefaExistente -> {
                    tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
                    tarefaExistente.setPrioridade(tarefaAtualizada.getPrioridade());
                    tarefaExistente.setConcluida(tarefaAtualizada.isConcluida());
                    Tarefa tarefaSalva = tarefaRepository.save(tarefaExistente);
                    return ResponseEntity.ok(tarefaSalva);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Se não encontrar, retorna 404.
    }
    
    // Endpoint: Marcar uma tarefa como concluída
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> marcarComoConcluida(@PathVariable Long id) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setConcluida(true); // Altera apenas o campo 'concluida'
                    Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);
                    return ResponseEntity.ok(tarefaAtualizada);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint: Deletar uma tarefa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!tarefaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se tentar deletar algo que não existe.
        }
        tarefaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content em caso de sucesso
    }
}