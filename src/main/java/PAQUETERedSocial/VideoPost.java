package PAQUETERedSocial;

public class VideoPost extends Post {
    private String title;
    public int quality;
    public int seconds;

    public VideoPost(String title, int quality, int seconds) {
        this.title = title;
        if (quality<=0 || seconds <=0){
            throw new IllegalArgumentException("La calidad y duracion del video deben ser mayores a 0");
        }
        this.quality = quality;
        this.seconds = seconds;
        this.contenido=toString();
    }
    @Override
    public String toString(){
        return "Hora: " + this.hora + "Fecha: " + this.fecha + "\nVIDEO\n"+
                "Titulo: " + this.title + "\n" +
                "Calidad del video: " + this.quality + "\n"+
                "Duracion del video: " + this.seconds + "Segundos" + "\n"
                ;
    }

}
