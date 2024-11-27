package PAQUETERedSocial;/*todo CLASE COMENTARIOS:
*  -tendran un TEXTO + fecha + propietario del comentario(usuario) */


import java.time.LocalDate;
import java.time.LocalTime;

public class Comentario{
    public String commentText;
    protected LocalDate fecha;
    protected LocalTime hora;
    protected String usuario;





    //constructor
    public Comentario (String commentText, String nombreusuario){
        this.hora=LocalTime.now();
        this.fecha=LocalDate.now();
        this.commentText=commentText;
        this.usuario=nombreusuario;
    }

    @Override
    public String toString(){
        return "Hora: " + this.hora + " Fecha: " + this.fecha + "\ncomentario:\n"
                +"Usuario: " + this.usuario + "\n" +
                "texto: " + this.commentText + "\n";
                }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
