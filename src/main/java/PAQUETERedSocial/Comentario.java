package PAQUETERedSocial;/*todo CLASE COMENTARIOS:
*  -tendran un TEXTO + fecha + propietario del comentario(usuario) */


import java.time.LocalDate;
import java.time.LocalTime;

public class Comentario extends Usuario{
    public String commentText;
    protected LocalDate fecha;
    protected LocalTime hora;





    //constructor
    public Comentario (String commentText, String name, String surnames, String nickName){
        super(name,surnames,nickName);
        this.hora=LocalTime.now();
        this.fecha=LocalDate.now();
        this.commentText=commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
