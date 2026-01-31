package view;

import model.Status;
import model.Tarefa;
import service.TarefaService;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuPrincipal {

    private final Validador validador = new Validador();
    private final TarefaService tarefaService;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MenuPrincipal(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    public void run() {

        try (Scanner scanner = new Scanner(System.in)) {
            String option;
            do {
                System.out.println("\n***** MANAGER TASKS v1.0 *****\n");
                System.out.println("1 - Cadastrar tarefa");
                System.out.println("2 - Alterar status da tarefa");
                System.out.println("3 - Listar por data limite");
                System.out.println("4 - Buscar tarefas por status");
                System.out.println("5 - Buscar tarefas por período");
                System.out.println("6 - Buscar tarefas por título");
                System.out.println("7 - Sobre");
                System.out.println("0 - Sair");
                System.out.print("\nDigite uma opção válida: ");
                option = scanner.nextLine();
                switch (option) {
                    case "0" -> System.out.println("Obrigado por utilizar o sistema!");
                    case "1" -> novaTarefa(scanner);
                    case "2" -> alteraStatus(scanner);
                    case "3" -> exibeListaData();
                    case "4" -> pesquisaStatus(scanner);
                    case "5" -> pesquisaPeriodo(scanner);
                    case "6" -> pesquisaTitulo(scanner);
                    case "7" -> sobre();
                    default -> System.out.println("Opção inválida!!!");
                }
            } while (!option.equalsIgnoreCase("0"));
        } catch (Exception e) {
            System.out.println("Falha na inicialização do sistema. Tente novamente!");
        }
    }

    private void sobre() {
        System.out.println("""
                \n Projeto de conclusão do módulo Técnicas de Programação I
                da Trilha Dev Back-End Java I do programa CaixaVerso,
                solicitado pelo professor William César.
                Trata-se de um Gerenciador de Tarefas Inteligentes para
                ajudar profissionais a organizarem melhor seu dia a dia.
                O projeto foi desenvolvido pela Equipe MMA, formada pelos(as)
                alunos(as) Michelle Cabral, Matheus Reis e Adelson Nóbrega.
                "Lutando com garra e determinação para ser um Javanauta"
               \s""");
    }

    private void novaTarefa(Scanner scanner) {
        String titulo;
        do {
            System.out.print("Digite o título da tarefa (maior que 5 caracteres): ");
            titulo = scanner.nextLine();
        } while (!validador.validaTitulo(titulo));
        System.out.print("Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine();
        String dataLimite;
        var hoje = LocalDate.now();
        do {
            System.out.print("Digite a data limite da tarefa (dd/MM/yyyy): ");
            dataLimite = scanner.nextLine();
        } while (!validador.validaData(dataLimite, hoje, FORMATTER));
        var tarefa = new Tarefa(titulo, descricao, LocalDate.parse(dataLimite, FORMATTER));
        tarefaService.cadastrar(tarefa);

        validador.agendarNotificacao(titulo, dataLimite, FORMATTER);

    }

    private void alteraStatus(Scanner scanner) {
        int id;
        do {
            System.out.print("Digite o ID da tarefa: ");
            id = Integer.parseInt(scanner.nextLine());
        } while (!tarefaService.buscaId(id));
        String status;
        do {
            System.out.print("Escolha o novo status da tarefa (EM_ANDAMENTO ou CONCLUIDO): ");
            status = scanner.nextLine().toUpperCase();
        } while (!validador.validaStatus(status));
        Status statusEnum = Status.valueOf(status);
        tarefaService.alterarStatus(id, statusEnum);
    }

    private void exibeListaData() {
        formataExibicao();
        tarefaService.listarPorData().forEach(this::exibeTarefa);
    }

    private void pesquisaStatus(Scanner scanner) {
        System.out.print("Digite o status da tarefa (PENDENTE, EM_ANDAMENTO ou CONCLUIDO): ");
        String status = scanner.nextLine().toUpperCase();
        var tarefas = tarefaService.buscaStatus(status);
        if (tarefas.isEmpty()) {
            formataExibicao();
            return;
        }
        formataExibicao();
        tarefas.forEach(this::exibeTarefa);
    }

    private void pesquisaPeriodo(Scanner scanner) {
        System.out.print("Digite a quantidade de dias a frente para buscar todas as tarefas não concluidas nesse período: ");
        int dias = Integer.parseInt(scanner.nextLine());
        var tarefas = tarefaService.buscaPeriodo(Period.ofDays(dias + 1));
        if (tarefas.isEmpty()) {
            formataExibicao();
            return;
        }
        formataExibicao();
        tarefas.forEach(this::exibeTarefa);
    }

    private void pesquisaTitulo(Scanner scanner) {
        System.out.print("Digite o título da tarefa ou parte dele: ");
        String titulo = scanner.nextLine();
        var tarefas = tarefaService.buscaTitulo(titulo.toLowerCase());
        if (tarefas.isEmpty()) {
            formataExibicao();
            return;
        }
        formataExibicao();
        tarefas.forEach(this::exibeTarefa);
    }

    private String alinhaEsquerda(String texto, int size) {
        return String.format("%-" + size + "s", texto);
    }

    private void formataExibicao() {
        System.out.print(alinhaEsquerda("\nId", 4));
        System.out.print(" | ");
        System.out.print(alinhaEsquerda("Título", 15));
        System.out.print(" | ");
        System.out.print(alinhaEsquerda("Descrição", 60));
        System.out.print(" | ");
        System.out.print(alinhaEsquerda("Data Limite", 11));
        System.out.print(" |");
        System.out.print(alinhaEsquerda("Status", 12));
        System.out.println(" |");
    }

    private void exibeTarefa(Tarefa tarefa) {
        System.out.print(alinhaEsquerda(String.valueOf(tarefa.getId()), 3));
        System.out.print(" | ");
        System.out.print(alinhaEsquerda(tarefa.getTitulo(), 15));
        System.out.print(" | ");
        System.out.print(alinhaEsquerda(tarefa.getDescricao(), 60));
        System.out.print(" | ");
        System.out.print(alinhaEsquerda(tarefa.getDataLimite().format(FORMATTER), 11));
        System.out.print(" |");
        System.out.print(alinhaEsquerda(tarefa.getStatus().toString(), 12));
        System.out.println(" |");
    }

}