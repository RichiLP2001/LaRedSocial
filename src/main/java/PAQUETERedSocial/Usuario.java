package PAQUETERedSocial;

import com.campusdual.Utils;

import java.text.Normalizer;
import java.util.*;

/*todo contendra:
       -nombre
       -Lista de los usuarios a los que sigue
       -Lista de post
   */
public class Usuario {
    public String nickName;
    public String name;
    public String surnames;
    public String code;
    public boolean followed=true;
    private Map<String, Usuario> dataFollowedList = new HashMap<>();
    private List<Post> listaPublicaciones = new ArrayList<>();

    //constructor

    public Usuario(String name, String surnames, String nickName) {
        this.name = name;
        this.surnames = surnames;
        this.nickName = nickName;
        this.code = createCode();


    }
    @Override
    public String toString(){
        return "|Usuario " +
                "con nombre='"+ this.name + '\'' +
                "- apellidos='" + this.surnames + '\'' +
                "- nickname='" + this.nickName + '\'' +
                '|';
    }
        //1º NORMALIZAR EL TEXTO
    private static String normalizarTexto(String texto) {
            // convertir en minuscula
        texto = texto.toLowerCase();
            //Normalizar --> quita diacriticos
        texto = Normalizer.normalize(texto,Normalizer.Form.NFD);
            //ELIMINAR COMBINACIONES
        texto=texto.replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}+","");
        return texto;
    }

        //2º generar el codigo y que se asigne al usuario
    public String createCode(){
            //llmamas tecto a minuscula y eliminar diacriticos
        String apellidoPrueba="";
        String nombreNormalizado = normalizarTexto(this.name);
            String primeraLetraNombre = nombreNormalizado.substring(0,1);

            //APELLIDOS-->
        String apellidosNormalizados = normalizarTexto(this.surnames);
            //separar los apellidos por espacio
        String [] partesApellidos = apellidosNormalizados.split(" ");
        if(partesApellidos.length == 1) {
            return primeraLetraNombre + partesApellidos[0];
        } else {
            String primerApellido = partesApellidos[0];
            for (int i=1 ; i <= partesApellidos.length-1 ; i++){
                apellidoPrueba = apellidoPrueba + partesApellidos[i];
            }
            return primeraLetraNombre + primerApellido.charAt(0) + apellidoPrueba;
        }
    }

    public Usuario addFriend(Usuario nuevoAmigo) {
        this.dataFollowedList.put(nuevoAmigo.getCode(), nuevoAmigo);
        return nuevoAmigo;
    }
    public void deleteFriend (String keyContact) {
        this.dataFollowedList.remove(keyContact);
    }
    public void showMyFollowedList(){
        System.out.println("\nLista de usuarios SEGUIDOS: \n");
        for (Map.Entry<String,Usuario> entrada : this.dataFollowedList.entrySet()){
            Usuario alguien = entrada.getValue();
            alguien.showUserDetails();
        }
    }




    public void addPostToMyList (Post publicacion){
        this.listaPublicaciones.add(publicacion);
    }

    public void showMyPosts (){
        for(Post item : this.listaPublicaciones){
            item.showPost();
        }
    }



    public void showUserDetails() {
        System.out.println("Nombre: " + this.name + " - surname: "+
                this.surnames  +
                " -Codigo: " + this.code +
                " -Nickname: " + this.nickName);
//        System.out.println("\nLISTA DE PUBLICACIONES\n");
//        showMyPosts();
    }



    //===============================================================================================================
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public List<Post> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(List<Post> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }

    public Map<String, Usuario> getDataFollowedList() {
        return dataFollowedList;
    }

    public void setDataFollowedList(Map<String, Usuario> dataFollowedList) {
        this.dataFollowedList = dataFollowedList;
    }
}

