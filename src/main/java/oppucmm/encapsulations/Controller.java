package oppucmm.encapsulations;

import oppucmm.services.UserServices;
import oppucmm.utilities.RolesApp;

import java.util.Set;

public class Controller {
    private static Controller controller;
    /*User services*/
    private final UserServices s1 = new UserServices();


    /*Controller*/
    private Controller(){
       // createFakeUser();
    }
    /*Singleton pattern*/
    public static Controller getInstance(){
        if (controller == null){
            controller = new Controller();
        }
        return controller;
    }
    /*Add user*/
    public boolean addUser(User u1){
        return s1.crear(u1);
    }
    /*Create fake user by default*/
    public void createFakeUser(){
        if(s1.buscar("admin") == null){
            s1.crear(new User("admin","admin","admin", Set.of(RolesApp.ROLE_ADMIN)));
        }
    }

    public User getUserById(String user) {
        return s1.buscar(user);
    }

    public User userAuthenticator(String username, String password) {
        User u1 = getUserById(username);
        if(u1!=null){
            if(!u1.getPassword().equals(password)){
                System.out.println("No se pudo  autentificar el usuario de forma correcta! \n");
                return null;
            }
        }
        return u1;
    }
}
