package PAQUETERedSocial;


import com.campusdual.Utils;

import java.util.ArrayList;
import java.util.List;

public class LaRedSocial {
    //INICIAMOS LA BASE DE DATOS DE USUARIOS
    public List<Usuario> listUser = new ArrayList<>();
    public Usuario usuarioActivo =null;//esta vacio
    public Usuario selectedUser = null;//esta vacio
    public Post selectedPost = null;

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
    public void iniciarSesion() {
        String searchedUser;
        Usuario foundedUser = null;
        int indice;
        searchedUser = Utils.string("Escribe el nickname del usuario: ");

        //ver si el usuario existe
        //y si existe --> que e usuario activo sea igual a ese.
        for (Usuario itemUser : this.listUser) {
            if (itemUser.getNickName().equalsIgnoreCase(searchedUser)) {
                foundedUser = itemUser;
                break;
            }
        }
        if (foundedUser != null) {
            this.usuarioActivo = foundedUser;
            System.out.println(this.usuarioActivo + "Usuario ya registrado...iniciando sesion");
            menuInicial(this.usuarioActivo);
            //
// buscar la posicion y luego seleccionar dicho elemento
//            indice=this.listUser.indexOf(searchedUser);//busco el indice del elemento encontrado
//            this.usuarioActivo=this.listUser.get(indice);//selecciono el elemento y doy ese valor al usuarioActivo
//            menuInicial(this.usuarioActivo); // pasa al menu inicial un vez inciada la sesion
        } else {
            System.out.println(searchedUser + "Usuario no registrado.\n" +
                    "Si desea registrarse escribe 'si' ");
            String eleccion=Utils.string("Escribe tu opcion: ");
            if((eleccion=="si")||(eleccion=="Si")||(eleccion=="SI")){
            registrarSesion();
            }else{
                pantallaInicio();
            }
        }
    }




    public void menuInicial(Usuario usuarioIniciado){
        this.usuarioActivo=usuarioIniciado;

        int eleccion;
        do{
            System.out.println("\n====MENU INICIAL====\n" +
                    "1). Lista de Usuarios Registrados.\n" +
                    "2). Seleccionar un usuario.\n" +
                    "3). Menu de Posts\n" +
                    "4). Ver mis Posts.\n"+
                    "5). Ver mi lista de seguidos.\n"+
                    "6). Pantalla de Posts en LA RED.\n"+
                    "7). Ver mi usuario activo.\n"+
                    "8). Comentar un post.\n");
            eleccion=Utils.integer("Selecciona una opcion: \n");
            switch (eleccion+0){
                case 1:
                    System.out.println("OPCION 1). Lista de Usuarios Registrados.\n");
                    for(int i=0; i < this.listUser.size(); i ++){
                        System.out.println(this.listUser.get(i) + "ID del usuario: " + i);

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
                    System.out.println("OPCION 3). Publicar un post.\n");
                            menuPost(this.usuarioActivo);
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
                case 6:
                    //Llamo a un metodo que imprime toda lalista de post de cada usuario
                    postsScreen(usuarioIniciado);
                    break;
                case 7:
                    this.usuarioActivo.showUserDetails();
                    break;
                case 8:
                    //lamamos a metodo que selecciona el OTROusuario
                    // luego que solicite el ID del post del usuario en la lista
                    //y que agregue un comentario mas a esa lista de comentarios de dicho post
                    String searchedUser;
                    int position;
                    System.out.println("OPCION 8). Seleccionar un usuario.\n");
                    searchedUser = Utils.string("\nEscribe el nickname del usuario que quieres seleccionar: ");
                    Usuario userToComment = null;
                    //ver si el usuario existe
                    //y si existe --> que e usuario activo sea igual a ese.
                    for (Usuario usuario : this.listUser){
                        if(usuario.getNickName().equalsIgnoreCase(searchedUser)){
                            userToComment = usuario;
                            break;
                        }
                    }
                    if (userToComment!=null){
                        this.selectedUser=userToComment;
                        System.out.println("Usuario encontrado: " + this.selectedUser);
                        makeComment(this.selectedUser);
//
                    }else{
                        System.out.println("Usuario no encontrado en el registro.");
                    }
                    break;

            }
        }while (!(eleccion==0));
    }
    public void makeComment(Usuario theUser){
        String idPost;
        idPost=Utils.string("Escribe el Nº ID del post del\n usuario que quieres comentar: ");
        this.selectedUser = theUser;
        Post foundedPost = null;
        for(Post itemPost : this.selectedUser.getListaPublicaciones()){
            if(itemPost.getIdPost().equalsIgnoreCase(idPost)){
                foundedPost = itemPost;
                break;
            }
        }
        if (foundedPost!=null){
            this.selectedPost=foundedPost;
            String textComment=Utils.string("Escribe el comentario que deseas publicar en el post.");
            Comentario nuevoComentario = new Comentario(textComment,this.usuarioActivo.getNickName());
            this.selectedPost.addCommentToPost(nuevoComentario);
        }else {
            System.out.println("Post no encontrado en el registro.");
        }
        menuInicial(this.usuarioActivo);
    }


    public void postsScreen(Usuario usuarioIniciado){
        for(int i=0; i < this.listUser.size(); i ++){
            System.out.println("=========="+"Publicaciones de"+"===========\n"
                    + this.listUser.get(i)+"\n");//recorra listUser y muestre cada usuario de esa lista
            for(int j=0 ; j < this.listUser.get(i).getListaPublicaciones().size();j++){
                System.out.println(this.listUser.get(i).getListaPublicaciones().get(j) + "\n" );
                    showComments(this.listUser.get(i).getListaPublicaciones().get(j));//seleccione cada publicacion de cada usuario
            }
        }
        String laNothing =Utils.string("Pulsa enter para volver al menu");
        menuInicial(usuarioIniciado);
    }
    public void showComments(Post thePost){
        for(int i=0 ; i < thePost.getCommentsList().size() ; i++){
            System.out.println("........."+ "Comentario: " + thePost.getCommentsList().get(i));
        }
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
            eleccion=Utils.integer("Escribe tu eleccion: ");
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
    public void menuPost(Usuario usuarioActivo){
        this.usuarioActivo = usuarioActivo;
        int eleccion;
        do {
            System.out.println("\n=====MENUPOST=====" +
                    "\n=====INDICA EL TIPO DE POST QUE QUIERES REALIZAR=====" +
                    "\n1). Texto." +
                    "\n2). Imagen." +
                    "\n3). Video."+
                    "\n4). Ver mis posts." +
                    "\n5). Volver al menu inicial.");
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

                case 3: // pendiente hacer post de videos
                    System.out.println("OPCION 3). Post de video");
                    try {
                        String text = Utils.string("Escribe el título del vídeo: ");
                        int quality = Utils.integer("Escribe la calidad del video en numero de pixeles: ");
                        int seconds = Utils.integer("Determina la duracion del video en segundos: ");

                        Post elVideoPost = new VideoPost(text,quality,seconds);
                        this.usuarioActivo.addPostToMyList(elVideoPost);
                        System.out.println("\n!Publicacion de video creada y publicaad exitosamente¡");
                    }catch (Exception e){
                        System.out.println("Error al subir el video: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("OPCION 4). VER MIS POSTS");
                    this.usuarioActivo.showMyPosts();
                    break;

                case 5:
                    menuInicial(this.usuarioActivo);
                    break;

                default:
                    break;
            }
        }while(!(eleccion==0));
    }

    public static void main(String[] args) {
        LaRedSocial objetoPrueba = new LaRedSocial();

        Usuario usuario1 = new Usuario("Javier","López","JAVI2000");
        Usuario usuario2 = new Usuario("Carlos","Fernandez-Simón","Carlos1999");
        Usuario usuario3 = new Usuario("Jose Manuel","Soria","JManuel1996");
        Usuario usuario4 = new Usuario("Santiago","Fernández Rocha","Sago96");
        Usuario usuario5 = new Usuario("Esteban","Serrano del Río","EsteMan123");
        Usuario usuario6 = new Usuario("Fernando Miguel","Juan de los Santos Requejo León","FM123");
        Usuario miUsuario = new Usuario("Ricardo","López","Ri2001");


        objetoPrueba.listUser.add(usuario1);
        objetoPrueba.listUser.add(usuario2);
        objetoPrueba.listUser.add(usuario3);
        objetoPrueba.listUser.add(usuario4);
        objetoPrueba.listUser.add(usuario5);
        objetoPrueba.listUser.add(usuario6);
        objetoPrueba.listUser.add(miUsuario);

        //POSTS
        Post post1 = new TextoPost("ESTE ES MI PRIMER POST");
        Post post2 = new TextoPost("POST DE TEXTO !!!");
        Post post3 = new ImagePost("MI POST DE IMAGEN",100,200);
        Post post4 = new ImagePost("MI POST DE IMAGEN",100,200);
        Post post5 = new ImagePost("IMAGEN 2.0",100,200);
        Post post6 = new ImagePost("IMAGEN 3.0",500,300);
        Post miPostInicial = new ImagePost("MI IMAGEN",100,111);
        //POSTS SUBIDDOS POR USUARIOS.

        usuario1.addPostToMyList(post1);
        usuario2.addPostToMyList(post2);
        usuario3.addPostToMyList(post3);
        usuario4.addPostToMyList(post4);
        usuario5.addPostToMyList(post5);
        usuario6.addPostToMyList(post6);
        miUsuario.addPostToMyList(miPostInicial);

        //EL INICIO DE PANTALLA

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
