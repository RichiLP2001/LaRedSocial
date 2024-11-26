package PAQUETERedSocial;/*todo POST podra ser:
*  -fecha y lista de comentarios
*  - TEXTO que contenga un String
*   - imagen: con titulo y dimensiones
*   - video: con titulo + calidad + duracion en segundos
*   - dur  */

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Post   {
    protected LocalDate fecha;
    protected LocalTime hora;
    protected String idPost;
    protected String contenido;
    private List<Comentario> commentsList = new ArrayList<>();
//    public BufferedImage imagen;
//    public String texto;
//    public String comentario;





//    CONSTRUCTOR:
    public Post (){
        this.hora=LocalTime.now();
        this.fecha=LocalDate.now();
        this.idPost=createIdPost();
        this.contenido=contenido;
    }


    //Generar una clave de cada post para el mapa
    public String createIdPost (){
        String id = UUID.randomUUID().toString();
        return id;
    }
    public void addCommentToPost(Comentario texto){
        this.commentsList.add(texto);
    }

    public void showPost (){
        System.out.println(" Hora: " + this.hora + " Fecha:" + this.fecha + "Publicacion: " + this.contenido);
    }







}
