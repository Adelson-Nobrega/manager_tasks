package service;

import model.Tarefa;

import java.time.Period;
import java.util.List;
import java.util.Optional;

public interface PesquisarTarefa {

    Optional<Tarefa> buscaTitulo(String titulo);

    List<Tarefa> listarPorData();

    List<Tarefa> buscaStatus(String status);

    List<Tarefa> buscaPeriodo(Period periodo);

}
