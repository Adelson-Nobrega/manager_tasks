package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
}