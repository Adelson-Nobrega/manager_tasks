import java.time.LocalDate;

public class Tarefa {

        private String titulo;
        private String descricao;
        private LocalDate dataLimite;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

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