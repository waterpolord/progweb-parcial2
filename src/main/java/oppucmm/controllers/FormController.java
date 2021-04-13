package oppucmm.controllers;

import io.javalin.Javalin;
import io.javalin.core.security.Role;
import oppucmm.models.Form;
import oppucmm.models.RoleApp;
import oppucmm.models.User;
import oppucmm.services.FormService;
import oppucmm.services.UserService;

import java.io.IOException;
import java.util.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class FormController {
    private FormService formService = FormService.getInstance();
    private UserService userService = UserService.getInstance();
    private Javalin app;
    private Map<String, Object> model = new HashMap<>();
    Boolean onUpdate = false;

    public FormController(Javalin app) {

        //super(app);
        this.app = app;
    }

    //@Override
    public void aplicarRutas() {

        app.config.accessManager((handler, ctx, permittedRoles) -> {


            User user = null;
            if(ctx.sessionAttribute("user") != null){
                user = UserService.getInstance().buscar(ctx.sessionAttribute("user"));

            }
            else{
                handler.handle(ctx);
                return;
            }

            System.out.println("Los roles permitidos: "+permittedRoles.toString());
            if(permittedRoles.isEmpty()){
                handler.handle(ctx);
                return;
            }
            User finalUser = user;
          // Boolean access = false;
           permittedRoles.stream().forEach(role -> {
               Boolean access = finalUser.hasRole(role);
               if(!access){
                   System.out.println("No tiene permiso para acceder..");
                   ctx.status(401).result("No tiene permiso para acceder...");
                   return;
               }
               else {
                   try {
                       handler.handle(ctx);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   return;
               }
            });

            //buscando el permiso del usuario.

            User userTmp = userService.explorarTodo().stream()
                    .filter(u -> u.getUsername().equalsIgnoreCase(finalUser.getUsername()))
                    .findAny()
                    .orElse(null);

            if(userTmp==null){
                System.out.println("Existe el usuario pero sin roles para acceder.");
                ctx.status(401).result("No tiene roles para acceder...");
                return;
            }

        });

        app.routes(() -> {
            path("/formularios", () -> {
                before("/*", ctx -> {
                    // verifica que el usuario este logeado
                    if (ctx.sessionAttribute("user") == null) {
                        //ctx.redirect("/login");
                        ctx.result("no loguado");
                        return;
                    }


                });
                get("/", ctx -> {
                    /*
                    System.out.println("llego patron");
                    if(onUpdate ){
                        model.put("accion","/formularios/editar");
                    }
                    else {
                       // model.put("accion","/formularios/crear");
                    }
                     */
                //    User u1 = Controller.getInstance().getUserByUsername(ctx.sessionAttribute("username"));
                 //   List<Form> f1 = getFormByUser(Controller.getInstance().listForm(), u1);
               //     model.put("user", u1.getUsername());
              //      model.put("forms", f1);
                    // para los botones activos del sidebar
                    if(ctx.sessionAttribute("user") != null) {
                        if (userService.buscar(ctx.sessionAttribute("user")).hasRole(RoleApp.ROLE_ADMIN)) {
                            model.put("admin", true);
                        } else {
                            model.put("admin", false);
                        }
                    }
                    else {
                        model.put("admin",false);
                    }

                    model.put("formulario", "active");

                    model.put("user",ctx.sessionAttribute("user"));
                    model.put("map", "");
                    model.put("report","");

                    ctx.render("public/form.html", model);
                }, Collections.singleton(RoleApp.ROLE_EMPLEADO));

                post("/crear", ctx -> {


                    Form form = new Form(ctx.formParam("name"),
                            ctx.formParam("sector"),
                            ctx.formParam("level")
                    );

                    formService.crear(form);

                    ctx.redirect("/formularios");

                }, Collections.singleton(RoleApp.ROLE_EMPLEADO));


                get("/editar/:id", ctx -> {
                    Form form = formService.buscar(ctx.pathParam("id", Integer.class).get());
                    model.put("form", form);
                    //model.put("onEdit",true);
                    model.put("accion", "/formularios/editar" + form.getId());
                    // las rutas solo estan registradas en el sidebar y por tanto se debe llevar a esta
                    onUpdate = true;
                    ctx.redirect("/formularios");

                    ctx.render("public/form.html", model);
                }, Collections.singleton(RoleApp.ROLE_EMPLEADO));

                post("/editar", ctx -> {
                    Form form = new Form(ctx.formParam("name"),
                            ctx.formParam("sector"),
                            ctx.formParam("level")
                    );
                    Form old = (Form) model.get("form");

                    old.setName(form.getName());
                    old.setSector(form.getSector());
                    old.setAcademicLevel(form.getAcademicLevel());
                    formService.editar(old);
                    onUpdate = false;
                    /*model.put("form",null);
                    model.put("forms",formService.explorarTodo());*/
                    ctx.redirect("/formularios");
                    ctx.render("public/form.html", model);
                }, Collections.singleton(RoleApp.ROLE_EMPLEADO));


                get("/eliminar/:id", ctx -> {
                    //Form form = formService.buscar(ctx.pathParam("id", Integer.class).get());
                    formService.eliminar(ctx.pathParam("id", Integer.class).get());
                    ctx.redirect("/formularios");
                    ctx.render("public/form.html", model);
                }, Collections.singleton(RoleApp.ROLE_EMPLEADO));


            });

            get("/mapa", ctx -> {
                // para los botones activos del sidebar
                model.put("formulario", "");
                model.put("map", "active");
                model.put("report","");

                model.put("formularios",Controller.getInstance().listForm());
                ctx.render("public/maps.html", model);
            }, Collections.singleton(RoleApp.ROLE_ADMIN));

        });
        app.before("/report", ctx -> {
            // verifica que el usuario este logeado
            if (ctx.sessionAttribute("user") == null) {
                ctx.redirect("/login");
                System.out.println("llego patron no ta logueado");
                ctx.result("no loguado");
            }

        });

        app.get("/report", ctx -> {

            // para los botones activos del sidebar
            model.put("formulario", "");
            model.put("map", "");
            model.put("report","active");
            model.put("user", ctx.sessionAttribute("user"));
            model.put("formularios",Controller.getInstance().listForm());
            ctx.render("/public/report.html", model);
        }, Collections.singleton(RoleApp.ROLE_ADMIN));
    }


}
