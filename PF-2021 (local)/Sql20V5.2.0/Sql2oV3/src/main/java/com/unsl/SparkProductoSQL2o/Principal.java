/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unsl.SparkProductoSQL2o ;


import static spark.Spark.*;
import sparkIndex.IndexControlador;
import com.unsl.SparkProductoSQL2o.producto.ProductoControlador;
import com.unsl.SparkProductoSQL2o.producto.UserController;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.debug.DebugScreen.enableDebugScreen;
import sparkCarrito.PagoControlador;
import util.Path;


public class Principal {
   
   public static void main(String[] args) { 
    /*Para Registrar los logs*/
    Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    enableDebugScreen();
    staticFiles.location("/public");
    get(Path.Web.INICIO,IndexControlador.getIndex);
    get(Path.Web.VER_PRODUCTO, ProductoControlador.getProducto);  
    get(Path.Web.PRODUCTO_ID, ProductoControlador.getProductoId );  
    get(Path.Web.PRODUCTO_FILTRAR, ProductoControlador.getProductoNombre );  
    post(Path.Web.PRODUCTO_INSERT, ProductoControlador.insertProducto );   
    post(Path.Web.PRODUCTO_UPDATE, ProductoControlador.updateProducto );   // Put fue eliminado de HMTL5
    post(Path.Web.PRODUCTO_DELETE, ProductoControlador.deleteProducto );  // Delete fue eliminado de HMTL5
    get(Path.Web.PRODUCTO_CANTIDAD, ProductoControlador.getProductoCantidad );  // Delete fue eliminado de HMTL5
    get(Path.Web.AGREGAR_CARRITO, sparkCarrito.CarritoControlador.agregarCarrito);
    get(Path.Web.ELIMINAR_CARRITO, sparkCarrito.CarritoControlador.EliminarProducto);
    get(Path.Web.VERTODOS, ProductoControlador.getTodos );  
    get(Path.Web.MOSTRAR_HISTORIAL, sparkCarrito.CarritoControlador.verHistorial );  
    get(Path.Web.VER_DETALLE, sparkCarrito.CarritoControlador.verDetalle );  
    post(Path.Web.PRODUCTOSMASVENDIDOS, sparkCarrito.CarritoControlador.verProductosVendidos );
    get(Path.Web.ELIMINAR_TODO, sparkCarrito.CarritoControlador.EliminarTodos );   
    post(Path.Web.COMENTARIO_INSERT, ProductoControlador.insertComentario );
    get(Path.Web.VER_COMENTARIO, ProductoControlador.verComentario );
    get(Path.Web.ADMIN, UserController.adminVision );
    post(Path.Web.COMPRAR, PagoControlador.pagarCompra);
    get(Path.Web.COMPRAR_C, PagoControlador.CompraCarrito);
    get(Path.Web.MERCADOPAGO, PagoControlador.pagarMercadoPago);
    post(Path.Web.MERCADOPAGOFORM, PagoControlador.rellenarFormulario);
   // post(Path.Web.NEXT, PagoControlador.updateCompra );
    // juanma puto get("/login", UserController.getLogin); 
    //post("/login", UserController.getLogin);
    //get("/logout", UserController.Logout); 
    //get("/index", IndexController.getIndex); 
   }
}
   


 
