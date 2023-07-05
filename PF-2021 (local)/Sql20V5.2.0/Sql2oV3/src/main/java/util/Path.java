/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.java.Log;

public class Path {

    // Los metodos @Getter son necesarios para acceder desde las variables de Templates de Velocity
    // NO USAR ACCESOS DIRECTOS, SINO SIEMPRE A TRAVÃ‰S DE ESTA CLASE
    public static class Web {
        @Getter public static final String VER_DETALLE = "/api/verDetalle/";
        @Getter public static final String PRODUCTOSMASVENDIDOS = "/api/verProductosVendidos";
        @Getter public static final String COMENTARIO_INSERT = "/api/producto/insertComentario/";
        @Getter public static final String VER_PRODUCTO = "/api/producto/getProducto/";
        @Getter public static final String PRODUCTO_ID = "/api/producto/id/";
        @Getter public static final String PRODUCTO_FILTRAR = "/api/producto/filtrar/";
        @Getter public static final String PRODUCTO_INSERT = "/api/producto/insert/";
        @Getter public static final String PRODUCTO_UPDATE = "/api/update/";
        @Getter public static final String PRODUCTO_DELETE = "/api/producto/delete/";
        @Getter public static final String PRODUCTO_CANTIDAD = "/api/producto/cantidad/";
        @Getter public static final String INICIO = "/api/index/";
        @Getter public static final String AGREGAR_CARRITO = "/api/getCarrito";
        @Getter public static final String ELIMINAR_CARRITO = "/api/EliminarProducto";
        @Getter public static final String MOSTRAR_CARRITO = "/api/verCarrito/";
        @Getter public static final String VERTODOS = "/api/todos/";
        @Getter public static final String MOSTRAR_HISTORIAL = "/api/verHistorial/";
        @Getter public static final String ELIMINAR_TODO = "/api/EliminarTodos";
        @Getter public static final String VER_COMENTARIO = "/api/verComentario";
        @Getter public static final String ADMIN = "/api/adminVision/" ;
        @Getter public static final String COMPRAR = "/api/pagarCompra/" ;
        @Getter public static final String NEXT = "/api/updateCompra" ;
        @Getter public static final String COMPRAR_C = "/api/CompraCarrito" ;
        @Getter public static final String MERCADOPAGO = "/api/pagarMercadoPago" ;
        @Getter public static final String MERCADOPAGOFORM  = "/api/rellenarFormulario" ;
    }
    
    public static class Template {
        public final static String LOGIN = "templates/login.vsl";
        public final static String LAYOUT = "templates/layout.vsl";
        public final static String PRODUCTO = "templates/producto.vsl";
        public final static String PRODUCTO_V = "templates/product-details.vsl";
        public final static String INDEX =  "templates/index.vsl";
        public final static String RESULT =  "templates/resultado-busqueda.vsl";
        public final static String CARRITO =  "templates/carrito.vsl";
        public final static String HISTORIAL =  "templates/historial.vsl";
        public final static String ESTADISTICA =  "templates/estadistica.vsl";
        public final static String VER_DETALLE =  "templates/verDetalle.vsl";
        public final static String PAGOS =  "templates/comprar.vsl";
        public final static String COMENTARIO_V=  "templates/comentario.vsl";
        public final static String MPTARGETA=  "templates/MercadoPago.vsl";
        
      
        
        
       
    }

}
