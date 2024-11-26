package PAQUETERedSocial;

import java.util.HashMap;
import java.util.Map;

public class TextoPost extends Post{
    protected Map<String, Post> dataBaseTextPost = new HashMap<>();
    private String texto;

    public TextoPost(String texto ) {

        this.contenido=texto;
    }

    @Override
    public String toString() {
        return " Hora: " + this.hora + " Fecha: " + this.fecha +
                "\nPublicacion de texto: " + this.contenido + "\n" +
                "ID del post: " + this.idPost + "\n"+
                super.toString();
    }

    public void addTextoPost (Post Publicacion){
        this.dataBaseTextPost.put(Publicacion.idPost,Publicacion);

    }

@Override
    public void showPost (){
        toString();
    System.out.println(" Hora: " + this.hora + " Fecha: " + this.fecha + "Publicacion: " + this.contenido);
    }
}





