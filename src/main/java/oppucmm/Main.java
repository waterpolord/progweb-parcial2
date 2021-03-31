package oppucmm;

import io.javalin.Javalin;
import oppucmm.controllers.mainController;
import oppucmm.encapsulations.Controller;
import oppucmm.services.DataBaseServices;

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
        Javalin app = Javalin.create(config ->{
            config.addStaticFiles("/public");
            config.enableCorsForAllOrigins();
        }).start(7000);
        //Create fake user
        Controller.getInstance().createFakeUser();
        new mainController(app).aplicarRutas();
    }
    public static String getModoConexion() {
        return modoConexion;
    }
}
