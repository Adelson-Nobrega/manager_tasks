package service;

import model.Status;
import model.Tarefa;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarefaService implements IncluirTarefa, PesquisarTarefa, AlterarTarefa {

    private final List<Tarefa> tarefas;

    public TarefaService() {
        tarefas = new ArrayList<>();
    }

    @Override
    public void cadastrar(Tarefa tarefa) {
        int id = tarefas.size() + 1;
        tarefa.setId(id);
        tarefas.add(tarefa);
    }

    @Override
    public void alterarStatus(int id, Status status) {
        tarefas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .get()
                .setStatus(status);
    }

    @Override
    public Optional<Tarefa> buscaTitulo(String titulo) {
        return tarefas.stream()
                      .filter(t -> t.getTitulo().equalsIgnoreCase(titulo))
                      .findFirst();
    }

    @Override
    public List<Tarefa> listarPorData() {
        return tarefas.stream()
                      .sorted((t1, t2) -> t1.getDataLimite().compareTo(t2.getDataLimite()))
                      .toList();
    }

    @Override
    public List<Tarefa> buscaStatus(String status) {
        return tarefas.stream()
                      .filter(t -> t.getStatus().toString().equalsIgnoreCase(status))
                      .toList();
    }

    @Override
    public List<Tarefa> buscaPeriodo(Period periodo) {
        return tarefas.stream().toList();
    }
}
