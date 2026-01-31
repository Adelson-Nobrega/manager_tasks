package service;

import model.Tarefa;

import java.time.Period;
import java.util.List;

public interface PesquisarTarefa {

    boolean buscaId(int id);

    List<Tarefa> buscaTitulo(String titulo);

    List<Tarefa> listarPorData();

    List<Tarefa> buscaStatus(String status);

    List<Tarefa> buscaPeriodo(Period periodo);

}
