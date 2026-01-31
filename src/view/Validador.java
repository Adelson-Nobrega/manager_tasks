package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Validador {

    public Validador() {
    }

    public boolean validaStatus(String status) {
        if (!status.equalsIgnoreCase("EM_ANDAMENTO")
                && !status.equalsIgnoreCase("CONCLUIDO")) {
            System.out.println("Status inválido!");
            return false;
        }
        return true;
    }

    public boolean validaData(String dataLimite, LocalDate hoje, DateTimeFormatter formatter) {
        if (LocalDate.parse(dataLimite, formatter).isBefore(hoje)) {
            System.out.println("Data inválida, anterior à data atual!!!");
            return false;
        }
        return true;
    }

    public boolean validaTitulo(String titulo) {
        if (titulo.length() < 6) {
            System.out.println("Títulos muito curtos não são permitidos!");
            return false;
        }
        return true;
    }
    public void agendarNotificacao(String titulo, String dataLimite, DateTimeFormatter formatter) {
        LocalDate limite = LocalDate.parse(dataLimite, formatter);
        LocalDate hoje = LocalDate.now();

        long diasRestantes = ChronoUnit.DAYS.between(hoje, limite);

        if(diasRestantes >= 3) {
            long delay = diasRestantes - 3; // esperar até faltar 3 dias
            CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.DAYS.sleep(delay);
                    System.out.println("Atenção: A tarefa \"" + titulo + "\"está próxima da data limite! Faltam 3 dias.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        } else if (diasRestantes > 0) {
                System.out.println("A tarefa \"" + titulo + "\"já está próxima da data limite! Faltam apenas" + diasRestantes + "dias.");
            } else {
                System.out.println("A tarefa \"" + titulo + "\"já passou da data limite");
            }
        }

    }

