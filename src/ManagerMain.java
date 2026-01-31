import service.TarefaService;
import view.MenuPrincipal;

void main() {

    var tarefaService = new TarefaService();
    var menu = new MenuPrincipal(tarefaService);

    menu.run();
}
