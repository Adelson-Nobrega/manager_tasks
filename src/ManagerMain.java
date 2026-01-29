import service.TarefaService;
import view.MenuPrincipal;

public class ManagerMain {

    void main() {

        var tarefaService = new TarefaService();
        MenuPrincipal menu = new MenuPrincipal(tarefaService, tarefaService, tarefaService);

        menu.run();

    }
}
