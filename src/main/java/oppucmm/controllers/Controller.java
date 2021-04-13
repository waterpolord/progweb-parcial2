package oppucmm.controllers;

import oppucmm.models.Form;
import oppucmm.models.User;
import oppucmm.services.FormService;
import oppucmm.services.UserService;
import oppucmm.models.RoleApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Controller {
    private static Controller controller;
    /*User services*/
    private final UserService s1 = new UserService();


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
        User u1 = new User("admin","admin","admin", Set.of(RoleApp.ROLE_ADMIN,RoleApp.ROLE_EMPLEADO));
        UserService.getInstance().editar(u1);
    }
    /*Create fake form by default*/
    public void createFakeForm(){
        Form f1 = new Form("Samuel Peña","Santiago","Grado");
        FormService.getInstance().crear(f1);
    }
    /*Add forms*/
    public boolean addForm(Form f1){ return FormService.getInstance().crear(f1); }
    /*Delete form*/
    public boolean deleteForm(Form f1){return FormService.getInstance().editar(f1);}
    /*Update form*/
    public boolean updateForm(Form f1){return FormService.getInstance().editar(f1);}
    /*Search user by username*/
    public User getUserByUsername(String user) { return s1.buscar(user); }
    /*Search form by id*/
    public Form getFormById(int id) { return FormService.getInstance().buscar(id); }
    /*List form from form services*/
    public List<Form> listForm(){ return FormService.getInstance().explorarTodo(); }

}
