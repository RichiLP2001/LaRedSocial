package PAQUETERedSocial;

import java.util.HashMap;
import java.util.Map;

public class TextoPost extends Post{
    protected Map<String, Post> dataBaseTextPost = new HashMap<>();
    private String texto;

    public TextoPost(String texto ) {

        this.contenido=texto;
    }

    public void addTextoPost (Post Publicacion){
        this.dataBaseTextPost.put(Publicacion.idPost,Publicacion);

    }

@Override
    public void showPost (){
    System.out.println(" Hora: " + this.hora + " Fecha:" + this.fecha + "Publicacion: " + this.contenido);
    }
}





