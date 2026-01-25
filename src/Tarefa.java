import java.time.LocalDate;

public class Tarefa {

        private String titulo;
        private String descricao;
        private LocalDate dataLimite;


        public Tarefa (String titulo, String descricao, LocalDate dataLimite){
            this.titulo = titulo;
            this.descricao = descricao;
            this.dataLimite = dataLimite;

    System.out.println("Testando o comando pull");

            }
        @Override
        public String toString (){
            return "Título: " + titulo +
                    "\nDescrição: " + descricao +
                    "\nData Limite: " + dataLimite +
                    "\n-------------------------";
        }
    }