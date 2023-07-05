/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unsl.SparkProductoSQL2o.producto;

/**
 *
 * @author franco
 */


import java.text.SimpleDateFormat;
import spark.Request;
import spark.Response;
import spark.Route;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import java.lang.System;
import java.util.ArrayList;
import java.util.Date;

import java.util.HashMap; 
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import util.Path;
import util.ViewUtil;

public class UserController{
    
    
   
   
  /* 
   
    public static Route 
        getLogin = (Request req, Response res) -> {
     
            HashMap model = new HashMap();
            
            //LLAMADO AL MENU   
            
            if(req.queryParams("pass")!=null && req.queryParams("email")!=null)
            {
                UserDAO uDAO = new UserDAO();
                List<User> user = uDAO.verificarPersona(req.queryParams("email"),req.queryParams("pass"));
                if(user.size() > 0){
                    //CREAR SEASION/COOKIE
                    model.put("template", "templates/main.vsl");
                    User usuarioLogeado = user.get(0);
                    req.session(true);                     // Crear y retornar la sesion
                    req.session().attribute("id", usuarioLogeado.getId() );       // Seteamos atributo
                    req.session().attribute("email", usuarioLogeado.getEmail() ); // Seteamos atributo
                    res.redirect("/index");
                }else{
                    model.put("template", "templates/login.vsl");
                    model.put("request",req);
                    model.put("error", "La contraseña o el email es incorrecto.");
                }
                
            }else{
                model.put("email","");
                model.put("template", "templates/login.vsl");   
            }
            
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vsl")); 
        }; 
    
    public static Route 
            Logout = (Request req, Response res) -> {
     
            req.session().removeAttribute("id");
            req.session().removeAttribute("email");
            res.redirect("/index");
            return null;
        };
*/
         public static Route adminVision = (Request request, Response response) -> {
                //Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
                
                //UserDAO uDAO = new UserDAO();
                //List<User> res = uDAO.selectAll();
                User u = new User();
                HashMap model = new HashMap();
                
                model.put("usuario", u);
                return ViewUtil.render(request, model, Path.Template.ESTADISTICA);
         };
}

