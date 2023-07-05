/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparkIndex;

/**
 *
 * @author Agustin
 */

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;
import spark.Request;
import spark.Response;
import spark.Route;
import util.Path;
import util.RequestUtil;
import util.ViewUtil;
import sparkCarrito.Carrito;
import sparkCarrito.CarritoDAO;


public class IndexControlador {
    
    static Carrito carrito;
    
    public static Route 
        getIndex = (Request request, Response res) -> {
        

        
        HashMap model = new HashMap();
        model.put("idCarrito", getCarrito().getIdCarrito());
        model.put("idUsuario", getCarrito().getIdUsuario());
        
        return ViewUtil.render(request, model, Path.Template.INDEX);
        };
    
    public static Carrito getCarrito() {
        if (carrito == null) {
           CarritoDAO carritoDAO = new CarritoDAO();
           carrito = carritoDAO.verEstadoCarrito(1);
            
        }
        return carrito;
    }
}
