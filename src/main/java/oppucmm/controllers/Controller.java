package oppucmm.controllers;

import oppucmm.models.Form;
import oppucmm.models.User;
import oppucmm.services.FormService;
import oppucmm.services.UserServices;
import oppucmm.models.RoleApp;

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
        User u1 = new User("admin","admin","admin", Set.of(RoleApp.ROLE_ADMIN));
        UserServices.getInstance().editar(u1);
    }
    /*Create fake form by default*/
    public void createFakeForm(){
        Form f1 = new Form("Samuel Peña","Santiago","Grado");
        FormService.getInstance().crear(f1);
    }

    public User getUserById(String user) {
        return s1.buscar(user);
    }

}
