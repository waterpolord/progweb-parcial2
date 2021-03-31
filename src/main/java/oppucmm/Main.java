package oppucmm;

import io.javalin.Javalin;
import oppucmm.services.DataBaseServices;

import java.sql.SQLException;

public class Main {
    private static String modoConexion = "";
    public static void main(String[] args) throws SQLException {
        //Creando la instancia del servidor.
        Javalin app = Javalin.create(config ->{
            config.addStaticFiles("/public");
            config.enableCorsForAllOrigins();
        }).start(7000);
        DataBaseServices.getInstance().InciarBD();
    }
    public static String getModoConexion() {
        return modoConexion;
    }
}
