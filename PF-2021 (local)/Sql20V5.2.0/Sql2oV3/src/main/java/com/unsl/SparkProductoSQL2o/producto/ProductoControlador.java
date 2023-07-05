/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unsl.SparkProductoSQL2o.producto;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;
import util.Path;
import util.RequestUtil;
import util.ViewUtil;

public class ProductoControlador {
     final static Logger registraLog = LoggerFactory.getLogger(ProductoControlador.class);

     public static Route getProductoCantidad = (Request request, Response response) -> {
             
      HashMap model = new HashMap();
      List<Producto> res;
      Producto p;      
      p = new Producto();
      Integer cantidad = Integer.valueOf(request.queryParams("cantidad"));
      ProductoDAO pDAO = new ProductoDAO();
      res = pDAO.buscarProductosxCantidad(cantidad);
      registraLog.info("getProductoNombre RES {}", res); 
          
      model.put("productos", res);
      model.put("producto", p);
      return ViewUtil.render(request, model, Path.Template.PRODUCTO);
    };

public static Route getProducto = (Request request, Response response) -> {
      // Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
      HashMap model = new HashMap();
      Integer idProducto = Integer.valueOf(request.queryParams("idProducto"));
      
      
      ProductoDAO pDAO = new ProductoDAO();
      Producto p = new Producto();
      p = pDAO.selectId(idProducto);
      
      
      //model.put("productos", productos);
      model.put("p", p);
      return ViewUtil.render(request, model, Path.Template.PRODUCTO_V);
    };

public static Route getProductoId = (Request request, Response response) -> {
   
      HashMap model = new HashMap();
      Integer id = Integer.valueOf(request.queryParams("id"));
      
      ProductoDAO pDAO = new ProductoDAO();
      Producto p;
      p = pDAO.selectId(id);
      registraLog.info("Producto {} ", p);
      List<Producto> res = pDAO.selectAll();
      
      model.put("productos", res);
      model.put("producto", p);
      return ViewUtil.render(request, model, Path.Template.PRODUCTO);
    };


public static Route getProductoNombre = (Request request, Response response) -> {
   
      HashMap model = new HashMap();
      List<Producto> res;
      Producto p;      
      p = new Producto();
      String nombre = request.queryParams("nombre");
      ProductoDAO pDAO = new ProductoDAO();
        
      if (nombre.equals(""))
       {
           res = pDAO.selectAll();
       }
      else
       {
          res = pDAO.selectProductoxNombre(nombre);
       }
      
      registraLog.info("getProductoNombre RES {}", res); 
          
      model.put("productos", res);
      model.put("producto", p);
      return ViewUtil.render(request, model, Path.Template.RESULT);
    };
    

   
   public static Route deleteProducto = (Request request, Response response) -> {
   
      HashMap model = new HashMap();
      List<Producto> res;
      ProductoDAO pDAO = new ProductoDAO();
      
      Integer id =  Integer.valueOf(request.queryParams("id"));
      pDAO.delete(id);
      
      res = pDAO.selectAll();
      
      model.put("productos", res);
      return ViewUtil.render(request, model, Path.Template.PRODUCTO);
    };
  
   public static Route insertProducto = (Request request, Response response) -> {
      HashMap model = new HashMap();
      double stock=4;
      String imagen ="........";
      Producto p = new Producto();
  
      p.setNombre(request.queryParams("nombre")); 
      p.setStock(stock);
      p.setPrecio(Double.parseDouble(request.queryParams("precio")));
      p.setImg("peron");

        
      ProductoDAO pDAO = new ProductoDAO();
      pDAO.insert(p);
      
      // Inicializar formulario
      p = new Producto();
      List<Producto> res;
      res = pDAO.selectAll();
      model.put("productos", res);
      model.put("producto", p);
      return ViewUtil.render(request, model, Path.Template.PRODUCTO);
      };
  
    public static Route updateProducto = (Request request, Response response) -> {
      HashMap model = new HashMap();
      
      Producto p = new Producto();
      p.setId(Integer.valueOf(request.queryParams("id")));
      p.setNombre(request.queryParams("nombre"));
      p.setStock(Double.parseDouble(request.queryParams("stock")));
      p.setPrecio(Double.parseDouble(request.queryParams("precio")));
      p.setImg(request.queryParams("img"));

      
      ProductoDAO pDAO = new ProductoDAO();
      pDAO.update(p);
                          
      // Inicializar formulario
      p = new Producto();
      List<Producto> res;
      res = pDAO.selectAll();
      model.put("productos", res);
      model.put("producto", p);
      return ViewUtil.render(request, model, Path.Template.PRODUCTO);
    };

    public static Route getTodos = (Request request, Response response) -> {
      //Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
      ProductoDAO pDAO = new ProductoDAO();
      List<Producto> res = pDAO.selectAll();
      Producto p = new Producto();
      HashMap model = new HashMap();
      model.put("productos", res);
      model.put("producto", p);
      return ViewUtil.render(request, model, Path.Template.RESULT);
    };
    
      public static Route insertComentario = (Request request, Response response) -> {
      HashMap model = new HashMap();
      System.out.println("-------------------------------------------------------");
      System.out.println(request.queryParams("id")+ " id puto");
      Comentario c= new Comentario();
      
      c.setId(Integer.parseInt(request.queryParams("id")));
      c.setDescripcion(request.queryParams("descripcion"));
      c.setFecha(Date.from(Instant.now()));
      ComentarioDAO cDAO = new ComentarioDAO();
      cDAO.insert(c);
      
      // Inicializar formulario
      List<Comentario> comentarios;
      //res = cDAO.selectAll();
      //model.put("comentarios", res);
      model.put("comentario", c);
      
      return ViewUtil.render(request, model, Path.Template.VER_DETALLE);
      };
      public static Route verComentario = (Request request, Response response) -> {
      Comentario c= new Comentario();
      Producto p = new Producto();
      System.out.println("-------------------------------------------------------");
      Integer idProducto = Integer.valueOf(request.queryParams("idProducto"));
      ComentarioDAO cDAO = new ComentarioDAO();
      ProductoDAO pDAO = new ProductoDAO();
      HashMap model = new HashMap();
      // Inicializar formulario
      List<Comentario> comentarios;
      comentarios= cDAO.comentariosProducto(idProducto);
      p=pDAO.selectId(idProducto);
      System.out.println("-------------------------------------------------------");
      System.out.println(p);
      System.out.println("-------------------------------------------------------");
      System.out.println(comentarios);
      model.put("comentarios", comentarios);
      model.put("comentario", c);
      model.put("p", p);
      return ViewUtil.render(request, model, Path.Template.COMENTARIO_V);
      };

}

