import io.javalin.Javalin;
import java.sql.SQLException;

public class Main {
    private static String modoConexion = "";
    public static void main(String[] args) throws SQLException {
        //Creando la instancia del servidor.
        Javalin app = Javalin.create(config ->{
            config.addStaticFiles("/public"); 
        }).start(7000);
    }
    public static String getModoConexion() {
        return modoConexion;
    }

}
