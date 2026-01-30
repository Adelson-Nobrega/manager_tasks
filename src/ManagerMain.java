import service.TarefaService;
import view.MenuPrincipal;

public class ManagerMain {

    void main() {

        var tarefaService = new TarefaService();
        var menu = new MenuPrincipal(tarefaService);

        menu.run();
    }
}
