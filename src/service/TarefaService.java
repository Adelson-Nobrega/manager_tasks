package service;

import model.Status;
import model.Tarefa;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
                .ifPresent(t -> t.setStatus(status));
    }

    @Override
    public boolean buscaId(int id) {
        var localiza = tarefas.stream()
                              .anyMatch(t -> t.getId() == id);
        if (!localiza) {
            System.out.println("ID não encontrado!");
        }
        return localiza;
    }

    @Override
    public List<Tarefa> buscaTitulo(String titulo) {
        return tarefas.stream()
                      .filter(t -> t.getTitulo().toLowerCase().contains(titulo))
                      .toList();
    }

    @Override
    public List<Tarefa> listarPorData() {
        return tarefas.stream()
                      .sorted(Comparator.comparing(Tarefa::getDataLimite))
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
        return tarefas.stream()
                      .filter(t -> t.getDataLimite().isBefore(LocalDate.now().plus(periodo)))
                      .toList();
    }
}
