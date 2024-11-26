package PAQUETERedSocial;


import com.campusdual.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LaRedSocial {
    //INICIAMOS LA BASE DE DATOS DE USUARIOS
    public List<Usuario> listUser = new ArrayList<>();
    public Usuario usuarioActivo =null;//esta vacio
    public Usuario selectedUser = null;//esta vacio

    public void showListUser(){
        System.out.println();
    }
    public void pantallaInicio(){
        System.out.println("=======PANTALLA DE INICIO======\n" + "1).Registrarse.\n" + "2).inciar sesion\n" + "3).Salir de la aplicacion\n" );
        int eleccion;
        eleccion= Utils.integer("Escribe tu eleccion: ");
        //en caso de que inicie sesion
        if (!(eleccion<1 || eleccion>2)){
            switch (eleccion+0){
                case 1:
                    System.out.println("1). OPCION: registrarse");
                    registrarSesion();
                    break;
                case 2:
                    System.out.println("2). OPCION: inicar sesion");
                    iniciarSesion();
                    break;
            }
        }else{
            System.out.println("Gracias por visitar nuestra pagina.\nWeb finalizada.");
        }

    }
    public  Usuario registrarSesion(){
        String nombre;
        String surnames;
        String nickname;
        nombre=Utils.string("\n===PANTALLA DE REGISTRO===\n" + "Escribe tu nombre: ");
        surnames=Utils.string("Escribe tus apellidos: ");
        nickname=Utils.string("Elige tu apodo: ");
        Usuario nuevoUsuario = new Usuario(nombre,surnames,nickname);
        this.listUser.add(nuevoUsuario);
        this.usuarioActivo=nuevoUsuario;
        menuInicial(this.usuarioActivo);
        return  nuevoUsuario;
    }
    public void iniciarSesion(){
        String nombreBuscado;
        nombreBuscado = Utils.string("Escribe tu nombre de usuario: ");
        int indice;
        //ver si el usuario existe
        //y si existe --> que e usuario activo sea igual a ese.
        if (this.listUser.contains(nombreBuscado)){
            System.out.println(nombreBuscado + "Usuario ya registrado...iniciando sesion");
            //buscar la posicion y luego seleccionar dicho elemento
            indice=this.listUser.indexOf(nombreBuscado);//busco el indice del elemento encontrado
            this.usuarioActivo=this.listUser.get(indice);//selecciono el elemento y doy ese valor al usuarioActivo
            menuInicial(this.usuarioActivo); // pasa al menu inicial un vez inciada la sesion
        }else{
            System.out.println(nombreBuscado + "Usuario no registrado.");
            registrarSesion();
        }
    }



    public void menuInicial(Usuario usuarioIniciado){


        int eleccion;
        do{
            System.out.println("\n====MENU INICIAL====\n" +
                    "1). Lista de Usuarios Registrados.\n" +
                    "2). Seleccionar un usuario.\n" +
                    "3). Publicar un Post\n" +
                    "4). Ver mis Posts.\n"+
                    "5). Ver mi lista de seguidos.\n");
            eleccion=Utils.integer("Selecciona una opcion: \n");
            switch (eleccion+0){
                case 1:
                    System.out.println("OPCION 1). Lista de Usuarios Registrados.\n");
                    for(int i=0; i < this.listUser.size(); i ++){
                        System.out.println(this.listUser.get(i) + "ID del usuario: ");

                    }
                    break;
                case 2:
                    String selectedUser;
                    int indice;
                    System.out.println("OPCION 2). Seleccionar un usuario.\n");
                    selectedUser = Utils.string("\nEscribe el nickname del usuario que quieres seleccionar: ");
                    Usuario foundedUser = null;
                    //ver si el usuario existe
                    //y si existe --> que e usuario activo sea igual a ese.
                    for (Usuario usuario : this.listUser){
                        if(usuario.getNickName().equalsIgnoreCase(selectedUser)){
                            foundedUser = usuario;
                            break;
                        }
                    }
                    if (foundedUser!=null){
                        this.selectedUser=foundedUser;
                        System.out.println("Usuario encontrado: " + this.selectedUser);
                        menuOtherUser(this.selectedUser);
//
                    }else{
                        System.out.println("Usuario no encontrado en el registro.");
                    }
                    break;
                case 3:
                    System.out.println("OPCION 3). Publicar un post.\n +" +
                            "\n=====INDICA EL TIPO DE POST UE QUIERES REALIZAR=====\n"+
                            "\n1). ");
                            menuPost();
                    break;

                case 4:
                    System.out.println("OPCION 4). VER MIS POSTS");
                        this.usuarioActivo.showMyPosts();
                    break;


                case 5:
                    System.out.println("\n====Usuarios que sigues====");
                    if(this.usuarioActivo.getDataFollowedList().isEmpty()){
                        System.out.println("!No sigues a ningun usuario¡");
                    }else {
                    this.usuarioActivo.showMyFollowedList();}
                    break;
            }
        }while (!(eleccion==0));
    }

    public void menuOtherUser(Usuario otherUser){
        //para seleccionar el otro usuario

        int eleccion;
        do {
            System.out.println("\n======MENU DE USUARIOS======\n"+
                    "\n1). Añadir a lista de seguidos." +
                    "\n2). Dejar de seguir a este usuario." +
                    "\n3). Ver Post del usuario."+
                    "\n4). Volver al menu inicial.");
            eleccion=Utils.integer("Escribe tu eleccion");
            switch (eleccion){
                case 1:
                    System.out.println("\n 1).Añadir a lista de seguidos.");
                    this.usuarioActivo.addFriend(this.selectedUser);
                    System.out.println("Ahora sigues al usuario. " + this.selectedUser.name + " - Nickname: " + this.selectedUser.nickName);
                    break;
                case 2:
                    System.out.println("\n2). Dejar de seguir a este usuario.");
                    this.usuarioActivo.deleteFriend(this.selectedUser.getCode());
                    System.out.println("Has dejado de seguir a... " + this.selectedUser.name + " - Nickname: " + this.selectedUser.nickName);

                    break;
                case 3:
                    System.out.println("\n3). Ver Post del usuario:" + this.selectedUser);
                    this.selectedUser.showMyPosts();
                    break;
                case 4:
                    menuInicial(this.usuarioActivo);
                    break;
            }
        }while(eleccion!=0);
    }
    public void menuPost(){
        int eleccion;
        do {
            System.out.println("\n=====MENUPOST=====" +
                    "=====INDICA EL TIPO DE POST UE QUIERES REALIZAR=====" +
                    "\n1). Texto." +
                    "\n2). Imagen." +
                    "\n3). Video"+
                    "\n4). Volver al menu inicial.");
            eleccion =Utils.integer("Teclea tu eleccion: ");
            switch (eleccion+0) {
                case 1:
                    String textPublication;
                    textPublication = Utils.string("Escribe tu publicación: ");
                    Post nuevopost = new TextoPost(textPublication);
                    this.usuarioActivo.addPostToMyList(nuevopost);
                    System.out.println("!Texto publicado¡");
                    break;
                case 2:
                    try {//configuracion para la imagen
                        String text = Utils.string("Escribe el titulo de tu imagen: ");
                        int width = Utils.integer("Define el ancho de tu imagen: ");
                        int height = Utils.integer("Define el alto de tu imagen: ");

                        Post imagenPost = new ImagePost(text,width,height);
                        this.usuarioActivo.addPostToMyList(imagenPost);
                        System.out.println("\n!Publicacion de imagen creada exitosamente¡");
                    }catch (Exception e) {
                        System.out.println("Error al subir la imagen: " + e.getMessage());
                    }
                    break;

                case 3:

                    break;
                case 4:
                    menuInicial(this.usuarioActivo);
                    break;

                default:
                    break;
            }
        }while(!(eleccion==0));
    }

    public static void main(String[] args) {
        LaRedSocial objetoPrueba = new LaRedSocial();

        Usuario usuario1 = new Usuario("Javier","López","_____");
        Usuario usuario2 = new Usuario("Carlos","Fernandez-Simón","LELELELE");
        Usuario usuario3 = new Usuario("Jose Manuel","Soria","LILILILI");
        Usuario usuario4 = new Usuario("Santiago","Fernández Rocha","LOLOLOLO");
        Usuario usuario5 = new Usuario("Esteban","Serrano del Río","LULULULULU");
        Usuario usuario6 = new Usuario("Fernando Miguel","Juan de los Santos Requejo León","XXXXXXXXXX");

        objetoPrueba.listUser.add(usuario1);
        objetoPrueba.listUser.add(usuario2);
        objetoPrueba.listUser.add(usuario3);
        objetoPrueba.listUser.add(usuario4);
        objetoPrueba.listUser.add(usuario5);
        objetoPrueba.listUser.add(usuario6);



        objetoPrueba.pantallaInicio();







       // CREAMOS BASE DE DATOS DE TODOS LOS USUARIOS DE LA RED



//      baseDatosUsuarios.showDataBaseUsers();
//INICIO DE SESION:

//        //todo registro a mi usuario
//        Usuario usuarioPrueba = new Usuario("Richi","López Peña","RLP2001");
//        //todo veo la lista de los usuarios que yo sigo
//        usuarioPrueba.showMyFollowedList();
//
//        System.out.println("Agrego al usuario2: ");
//        usuarioPrueba.addFriend(usuario2);
//
//        System.out.println("IMPRIMO MI LISTA DE SEGUIDOS");
//        usuarioPrueba.showMyFollowedList();
//
//        Post publicacion1 = new TextoPost("HOLA,\n ESTA ES MI PRIMERA PUBLICACION DE TEXTO");
//
//        // Configuraciones para la imagen
//        String text = "Hola, mundo!";
//        int width = 300;
//        int height = 100;
//        Post publicacionImagen = new ImagePost(text,width,height);
//
//        usuarioPrueba.addPostToMyList(publicacion1);












    }
}
