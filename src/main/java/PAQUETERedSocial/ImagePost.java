package PAQUETERedSocial;

import java.util.HashMap;
import java.util.Map;

public class ImagePost extends Post{
    protected Map<String, Object> dataBaseImagePost = new HashMap<>();
    private String title;
    public int width;
    public int height;



    public ImagePost (String titleText, int width, int height) {
        this.title = titleText;
        if (width<=0 || height <=0){
            throw new IllegalArgumentException("La anchura y altura deben ser mayores a cero");
        }
        this.width = width;
        this.height = height;
        this.contenido=toString();
    }

    @Override
    public String toString(){
        return "Hora: " + this.hora + " Fecha: " + this.fecha + "\nimagen:\n"
                +"Título: " + this.title + "\n" +
                "Anchura: " + this.width + "px\n" +
                "Altura: " + this.height + " px\n" +
                "ID del post: " + this.idPost +"\n" +
                super.toString();
    }
    public Map<String, Object> getDataBaseImagePost() {
        return dataBaseImagePost;
    }

    public void setDataBaseImagePost(Map<String, Object> dataBaseImagePost) {
        this.dataBaseImagePost = dataBaseImagePost;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void showPost(){
        toString();
        System.out.println(" Hora: " + this.hora + " Fecha: " + this.fecha + "\nimagen: " +
                "Título: " + this.title + "\n" +
                "Anchura: " + this.width + "px\n" +
                "Altura: " + this.height + " px\n" );

    }
}
