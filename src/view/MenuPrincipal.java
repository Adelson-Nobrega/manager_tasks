package view;

import service.AlterarTarefa;
import service.IncluirTarefa;
import service.PesquisarTarefa;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuPrincipal {

    private IncluirTarefa cadastro;
    private AlterarTarefa alteracao;
    private PesquisarTarefa busca;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MenuPrincipal(IncluirTarefa cadastro, AlterarTarefa alteracao, PesquisarTarefa busca) {
        this.cadastro = cadastro;
        this.alteracao = alteracao;
        this.busca = busca;
    }

    public void run() {

        try (Scanner scanner = new Scanner(System.in)) {
            String option;
            do {
                System.out.println("\n***** MANAGER TASKS v1.0 *****\n");
                System.out.println("1 - Cadastrar tarefa");
                System.out.println("2 - Listar tarefas cadastradas");
                System.out.println("3 - Buscar tarefas por status");
                System.out.println("4 - Buscar tarefa por período");
                System.out.println("5 - Sobre");
                System.out.println("0 - Sair");
                System.out.print("\nDigite uma opção válida: ");
                option = scanner.nextLine();
                switch (option) {
                    case "0" -> System.out.println("Obrigado por utilizar o sistema!");
                    case "1" -> novaTarefa(scanner);
                    case "2" -> listarTarefas();
                    case "3" -> buscaPorStatus(scanner);
                    case "4" -> buscaPorPeriodo(scanner);
                    case "5" -> sobre();
                    default -> System.out.println("Opção inválida!!!");
                }
            } while (!option.equalsIgnoreCase("0"));
        } catch (Exception e) {
            e.printStackTrace();
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

    private void buscaPorPeriodo(Scanner scanner) {
    }

    private void buscaPorStatus(Scanner scanner) {
        System.out.print("Digite o status da tarefa: ");
        String status = scanner.nextLine();
    }

    private void listarTarefas() {

    }

    private void novaTarefa(Scanner scanner) {
        System.out.print("Digite o título da tarefa: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a data limite da tarefa (dd/MM/yyyy): ");
        String dataLimite = scanner.nextLine();

    }
}
