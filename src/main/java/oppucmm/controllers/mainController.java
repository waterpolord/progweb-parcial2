package oppucmm.controllers;

import io.javalin.Javalin;
import oppucmm.models.User;
import oppucmm.models.RoleApp;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.javalin.apibuilder.ApiBuilder.*;

public class mainController extends ControladorBase {
    private static String mpCryptoPassword = "BornToFight";

    static String URI = "";

    public mainController(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {
        app.get("/", ctx -> ctx.redirect("/homePage"));
        app.routes(() -> {
            path("/homePage", () -> {
                before("/", ctx -> {
                    if(ctx.sessionAttribute("usuario")==null){
                        ctx.redirect("/login");
                    }
                });
                get("/", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    // aqui van los formularios
                    ctx.render("public/index.html", modelo);
                });
            });
            path("/logout", () -> {
                before("/", ctx -> {
                    if(ctx.sessionAttribute("usuario")==null){
                        ctx.redirect("/login");
                    }
                });
                get("/", ctx -> {
                    System.out.println("\nEliminando cokkie...");
                    ctx.clearCookieStore();
                    ctx.sessionAttribute("usuario", null);
                    ctx.redirect("/login");
                });
            });

        });
        app.get("/login", ctx -> {
            ctx.render("public/page-login.html");
        });
        app.post("/userRegister", ctx -> {
            Map<String, Object> modelo = new HashMap<>();

            String fullNmae = ctx.formParam("fullName");
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            String rol = ctx.formParam("role");
            System.out.println("el valor es> "+ctx.formParam("role"));
            User aux = new User(username, fullNmae, password);
            if(rol!=null){
                if(rol.matches("Administrador"))
                    aux.setRolesList(Set.of(RoleApp.ROLE_ADMIN));
                if(rol.matches("Empleado"))
                    aux.setRolesList(Set.of(RoleApp.ROLE_EMPLEADO));
                else{
                    aux.setRolesList(Set.of(RoleApp.ROLE_VOLUNTARIO));
                }
                if(Controller.getInstance().getUserById(username)==null){
                    Controller.getInstance().addUser(aux);
                    ctx.render("public/index.html", modelo);
                    System.out.println("CREADO");
                }else{
                    modelo.put("Error", "El nombre de usuario ya existe. Intentelo de nuevo! ");
                    ctx.render("public/register.html", modelo);
                    System.out.println("NO SE PUDO CREAR");
                }
            }else{
                System.out.println("Se debe asignar un rol");
            }
        });
        app.post("/enterHomePage", ctx -> {
            String rememberMe = "";
            Map<String, Object> modelo = new HashMap<>();
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            String boton = ctx.formParam("checkbox");
            //Authenticator
            User aux = Controller.getInstance().userAuthenticator(username, password);
            if (aux != null) {
                rememberMe = ctx.formParam("rememberMe");
                if(rememberMe!=null){
                    if(rememberMe.equalsIgnoreCase("ON")){
                        System.out.println("Creando cookie...\n");
                        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
                        encryptor.setPassword(mpCryptoPassword);
                        encryptor.encrypt(aux.getPassword());
                        ctx.cookie("username", aux.getUsername(), 604800);
                        ctx.cookie("password", encryptor.encrypt(aux.getPassword()), 604800);
                    }
                    else {
                        System.out.println("Cookie no pudo ser creada...\n");
                    }
                }
                ctx.sessionAttribute("usuario", username);
                ctx.redirect("/homePage");
            } else {
                modelo.put("Error", "Please check username & password! ");
                ctx.render("public/page-login.html", modelo);
            }
        });
    }
}
