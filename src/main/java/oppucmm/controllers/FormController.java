package oppucmm.controllers;

import io.javalin.Javalin;
import oppucmm.models.Form;
import oppucmm.models.User;
import oppucmm.services.FormService;

import java.io.IOException;
import java.util.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class FormController {
    private FormService formService = FormService.getInstance();
    private Javalin app;
    private Map<String, Object> model = new HashMap<>();
    Boolean onUpdate = false;
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
                    if(onUpdate ){
                        model.put("accion","/formularios/editar");
                    }
                    else {
                        model.put("accion","/formularios/crear");
                    }
                    model.put("forms",formService.explorarTodo());

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

                get("/editar/:id",ctx -> {
                    Form form = formService.buscar(ctx.pathParam("id", Integer.class).get());
                    model.put("form",form);
                    //model.put("onEdit",true);
                    model.put("accion", "/formularios/editar"+form.getId());
                    // las rutas solo estan registradas en el sidebar y por tanto se debe llevar a esta
                    onUpdate = true;
                    ctx.redirect("/formularios");

                    ctx.render("public/form.html",model);
                });

                post("/editar",ctx -> {
                    Form form = new Form(ctx.formParam("name"),
                            ctx.formParam("sector"),
                            ctx.formParam("level")
                    );
                    Form old =(Form)model.get("form");

                    old.setName(form.getName());
                    old.setSector(form.getSector());
                    old.setAcademicLevel(form.getAcademicLevel());
                    formService.editar(old);
                    onUpdate = false;
                    /*model.put("form",null);
                    model.put("forms",formService.explorarTodo());*/
                    ctx.redirect("/formularios");
                    ctx.render("public/form.html",model);
                });


                get("/eliminar/:id",ctx -> {
                    //Form form = formService.buscar(ctx.pathParam("id", Integer.class).get());
                    formService.eliminar(ctx.pathParam("id", Integer.class).get());
                    ctx.redirect("/formularios");
                    ctx.render("public/form.html",model);
                });

            });
        });
    }
    private List<Form> getFormByUser(List<Form> servicioFormulario, User user) {
        List<Form> list = new ArrayList<Form>();
        for (Form f: servicioFormulario) {
            if (f.getUser().equals(user)){
                list.add(f);
            }
        }
        return list;
    }
}
