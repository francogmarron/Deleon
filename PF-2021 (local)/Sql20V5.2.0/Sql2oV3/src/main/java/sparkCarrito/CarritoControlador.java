/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparkCarrito;

import com.unsl.SparkProductoSQL2o.producto.Producto;
import com.unsl.SparkProductoSQL2o.producto.ProductoControlador;
import com.unsl.SparkProductoSQL2o.producto.ProductoDAO;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;
import util.Path;
import util.ViewUtil;
import sparkProductoXcarrito.*;
import java.util.*;
/**
 *
 * @author Agustin
 */
public class CarritoControlador {
     final static Logger registraLog = LoggerFactory.getLogger(CarritoControlador.class);
    public static Route agregarCarrito = (Request request, Response response) -> {
             
      HashMap model = new HashMap();
      Integer idCliente = Integer.valueOf(request.queryParams("idCliente"));
      Integer idProducto = Integer.valueOf(request.queryParams("idProducto"));
      Integer cantProducto = Integer.valueOf(request.queryParams("cantProducto"));
      
      //ponemos selecId(1) porque sup que un cliente tiene un unico carrito y en la base tenemos un carrito
     
      int idCarrito = sparkIndex.IndexControlador.getCarrito().getIdCarrito();
      
      ProductosxCarrito productosxCarrito = new ProductosxCarrito();
      
      Integer pxc = 0;
      Producto p = new Producto();
      List<Producto> productos = new ArrayList<>();
      List<Integer> pxcs = new ArrayList<>();
      List<Integer> var = new ArrayList<>();
      
      Integer v =0 ;
     
      
      
      
      productosxCarrito.setIdCarrito(idCarrito);
      productosxCarrito.setIdProducto(idProducto);
      productosxCarrito.setCantProducto(cantProducto);
      
      
      
      ProductoXcarritoDAO productoXcarritoDAO = new ProductoXcarritoDAO();
      productoXcarritoDAO.insert(productosxCarrito);
      
      List<Integer> idProductoslList = productoXcarritoDAO.selectProductosxCarritos(idCarrito);
      List<Integer> idCantProductoslList = productoXcarritoDAO.selectProductosxCarritosCantidad(idCarrito);
      
       ProductoDAO x = new ProductoDAO();
       
       for (int i = 0; i < idProductoslList.size(); i++) {
             productos.add(x.selectId(idProductoslList.get(i)));
             pxcs.add(idCantProductoslList.get(i));
          
        }
      model.put("p", p);
      model.put("pxcs", pxcs);
      model.put("productos",productos);
      model.put("pxc", pxc);
     
      
      
      
 
      return ViewUtil.render(request, model, Path.Template.CARRITO);
    };
    
    public static Route EliminarProducto = (Request request, Response response) -> {
        HashMap model = new HashMap();
        
        Integer idProducto = Integer.valueOf(request.queryParams("idProducto"));
        Integer cantProducto = Integer.valueOf(request.queryParams("cantProducto"));
      
      //ponemos selecId(1) porque sup que un cliente tiene un unico carrito y en la base tenemos un carrito
        
        int idCarrito = sparkIndex.IndexControlador.getCarrito().getIdCarrito();
        
        ProductosxCarrito productosxCarrito = new ProductosxCarrito();
        
        productosxCarrito.setIdCarrito(idCarrito);
        productosxCarrito.setIdProducto(idProducto);
        productosxCarrito.setCantProducto(cantProducto);
        
        ProductoXcarritoDAO productoXcarritoDAO = new ProductoXcarritoDAO();
        
     
        productoXcarritoDAO.delete(productosxCarrito);
       
        
        Integer pxc = 0;
        Producto p = new Producto();
        List<Producto> productos = new ArrayList<>();
        List<Integer> var = new ArrayList<>();
      
        Integer v =0 ;
        
        List<Integer> idProductoslList = productoXcarritoDAO.selectProductosxCarritos(idCarrito);
        List<Integer> idCantProductoslList = productoXcarritoDAO.selectProductosxCarritosCantidad(idCarrito);
      
        ProductoDAO x = new ProductoDAO();
       
        for (int i = 0; i < idProductoslList.size(); i++) {
             productos.add(x.selectId(idProductoslList.get(i)));
        }
        
        model.put("p", p);
        model.put("pxcs", idCantProductoslList);
        model.put("productos",productos);
        model.put("pxc", pxc);
     
        return ViewUtil.render(request, model, Path.Template.CARRITO);
        
    };
    
    
    public static Route EliminarTodos = (Request request, Response response) -> {
        HashMap model = new HashMap();
        
        Integer idProducto = Integer.valueOf(request.queryParams("idProducto"));
        Integer cantProducto = Integer.valueOf(request.queryParams("cantProducto"));
      
      //ponemos selecId(1) porque sup que un cliente tiene un unico carrito y en la base tenemos un carrito
        
        int idCarrito = sparkIndex.IndexControlador.getCarrito().getIdCarrito();
        
        ProductosxCarrito productosxCarrito = new ProductosxCarrito();
        
        productosxCarrito.setIdCarrito(idCarrito);
        productosxCarrito.setIdProducto(idProducto);
        productosxCarrito.setCantProducto(cantProducto);
        
        ProductoXcarritoDAO productoXcarritoDAO = new ProductoXcarritoDAO();
        
     
        productoXcarritoDAO.deleteAll(productosxCarrito);
       
        
         Integer pxc = 0;
        Producto p = new Producto();
        List<Producto> productos = new ArrayList<>();
        List<Integer> var = new ArrayList<>();
      
        Integer v =0 ;
        
        List<Integer> idProductoslList = productoXcarritoDAO.selectProductosxCarritos(idCarrito);
        List<Integer> idCantProductoslList = productoXcarritoDAO.selectProductosxCarritosCantidad(idCarrito);
      
        ProductoDAO x = new ProductoDAO();
       
        for (int i = 0; i < idProductoslList.size(); i++) {
             productos.add(x.selectId(idProductoslList.get(i)));
        }
        
        model.put("p", p);
        model.put("pxcs", idCantProductoslList);
        model.put("productos",productos);
        model.put("pxc", pxc);
     
        return ViewUtil.render(request, model, Path.Template.CARRITO);
        
    };
    
   
    
    public static Route verHistorial = (Request request, Response response) -> {
        HashMap model = new HashMap();
        int idCliente = 1;
        CarritoDAO carritoDAO = new CarritoDAO();
        List<Carrito> carritos = new ArrayList<>();
        carritos =carritoDAO.selectIdCarritos(idCliente);
        
        Carrito carrito = new Carrito();
        model.put("c", carrito);
        model.put("carritos", carritos);
        
        return ViewUtil.render(request, model, Path.Template.HISTORIAL);
    };
    public static Route verDetalle = (Request request, Response response) -> {

        HashMap model = new HashMap();
        Integer idCarrito = Integer.valueOf(request.queryParams("idCarrito"));
        Producto p = new Producto();
        List<Producto> productos = new ArrayList<>();
        List<Integer> var = new ArrayList<>();
      
        Integer v =0;
        ProductoXcarritoDAO productoXcarritoDAO = new ProductoXcarritoDAO();
        List<Integer> idProductoslList = productoXcarritoDAO.selectProductosxCarritos(idCarrito);
        List<Integer> idCantProductoslList = productoXcarritoDAO.selectProductosxCarritosCantidad(idCarrito);
      
        ProductoDAO x = new ProductoDAO();
       
        for (int i = 0; i < idProductoslList.size(); i++) {
             productos.add(x.selectId(idProductoslList.get(i)));
        }
        Integer pxc = 0;
        model.put("p", p);
        model.put("pxcs", idCantProductoslList);
        model.put("productos",productos);
        model.put("pxc", pxc);
        return ViewUtil.render(request, model, Path.Template.VER_DETALLE);
    };
    public static Route verProductosVendidos =(Request request, Response response) -> {
        
        HashMap model = new HashMap();
        System.out.println("-------------------------------------------------");
        System.out.println(request.queryParams("ini"));
        System.out.println(request.queryParams("fin"));
        String s1=request.queryParams("ini");
        String s2=request.queryParams("fin");
        System.out.println("-------------------------------------------------");
        System.out.println(s1);
        System.out.println(s2);
        String p= new String();
        String c= new String();
        int idCliente=1;
        
        ProductoDAO pDAO = new ProductoDAO();
        
        List<String> productos = new ArrayList();
        List<String> cants = new ArrayList();
        productos=pDAO.productoxCArrito(s1, s2);
        cants =pDAO.cantidadVendidas( s1, s2);
       //////////////////////////////////////////
       

      

      
       /////////////////////////////////////////
       
         model.put("c",c );
        model.put("p",p );
        model.put("productos",productos);
        model.put("cants",cants);
        return ViewUtil.render(request, model,Path.Template.ESTADISTICA);
    };
    
  
}
