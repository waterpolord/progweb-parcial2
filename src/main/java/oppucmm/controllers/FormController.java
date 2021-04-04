package oppucmm.controllers;

import io.javalin.Javalin;
import oppucmm.models.Form;
import oppucmm.services.FormService;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;

public class FormController {
    private FormService formService = FormService.getInstance();
    private Javalin app;
    private Map<String, Object> model = new HashMap<>();

    public FormController(Javalin app) {
        //super(app);
        this.app = app;
    }

    //@Override
    public void aplicarRutas() {
        app.routes(() -> {
            path("/formularios", () -> {
                before("/*",ctx -> {
                    // verifica que el usuario este logeado
                    if(ctx.sessionAttribute("usuario")==null){
                        //ctx.redirect("/login");
                        System.out.println("llego patron no ta logueado");
                        ctx.result("no loguado");
                    }
                    System.out.println("a mi no me impolta si ta logueado en vdd");

                });
                get("/",ctx -> {
                    System.out.println("llego patron");
                    model.put("forms",formService.explorarTodo());
                    model.put("accion","/formularios/crear");
                    model.put("save","GUARDAR FORMULARIO");
                    ctx.render("public/form.html",model);
                });

                post("/crear", ctx -> {


                    Form form = new Form(ctx.formParam("name"),
                            ctx.formParam("sector"),
                            ctx.formParam("level")
                            );

                    formService.crear(form);

                    ctx.redirect("/formularios");

                });


                post("/maps",ctx -> {
                    System.out.println("llego patron");
                    ctx.result("comming soon");
                });

                post("/editar",ctx -> {
                    System.out.println("llego patron");
                    ctx.result("comming soon");
                });


                post("/eliminar",ctx -> {
                    System.out.println("llego patron");
                    ctx.result("comming soon");
                });

            });
        });
    }
}
