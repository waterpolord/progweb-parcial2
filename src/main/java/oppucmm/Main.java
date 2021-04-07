package oppucmm;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import oppucmm.controllers.FormController;
import oppucmm.controllers.UserController;
import oppucmm.controllers.Controller;
import oppucmm.services.connect.DataBaseServices;

import java.sql.SQLException;

public class Main {
    private static String modoConexion = "";
    public static void main(String[] args) throws SQLException {
        String msg = "Software ORM - JPA";
        System.out.println(msg);
        if(args.length >=1){
            modoConexion = args[0];
            System.out.println("Modo de Operacion: "+modoConexion);
        }
        if(modoConexion.isEmpty()){
            //Database init
            DataBaseServices.getInstance().InciarBD();
        }
        //Creando la instancia del servidor.
        /*Javalin app = Javalin.create(config ->{
            config.addStaticFiles("/public");
            config.enableCorsForAllOrigins();
        }).start(7000);*/
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/rutas"));
            config.enableCorsForAllOrigins();
            JavalinRenderer.register(JavalinThymeleaf.INSTANCE, ".html");
        }).start(7000);
        //Create fake user
        Controller.getInstance().createFakeUser();
        //Create fake form
        Controller.getInstance().createFakeForm();

        new UserController(app).aplicarRutas();
        new FormController(app).aplicarRutas();
    }
    public static String getModoConexion() {
        return modoConexion;
    }
}