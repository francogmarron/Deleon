/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unsl.SparkProductoSQL2o.producto;

import static com.unsl.SparkProductoSQL2o.producto.ProductoControlador.registraLog;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.sql2o.Connection;
import java.util.List;
import lombok.Data; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Sql2oDAO;

@Data
public class ProductoDAO {
        
    
    
    public List<Producto> buscarProductosxCantidad(int cantidad) {
            String sql = "SELECT * FROM producto WHERE stock >= :cantidad";
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Producto> res = con
                    .createQuery(sql)
                    .addParameter("stock", cantidad)
                    .executeAndFetch(Producto.class);
                return res;
            }
    } 
    
    public List<Integer> getIdProductos(){
        String sql = "SELECT id FROM producto";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeScalarList(Integer.class);
        }
    }   

    /* Puede reemplazarse con selectWhere */
    public List<Producto> selectProductoxNombre(String nombre){
       String sql = "SELECT * FROM producto WHERE nombre LIKE  '%"+nombre+"%'"; //%''%
       registraLog.info("En ProductoDAO {} ", nombre);
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Producto> res = con
                    .createQuery(sql)
                    .executeAndFetch(Producto.class);
                return res;
            }
    }   
    
    public List<Producto> selectAll() {
        String selectALLSQL = "SELECT * FROM PRODUCTO;";
        List<Producto> res = null;
        try (Connection con = Sql2oDAO.getSql2o().open()) {
           res = con.createQuery(selectALLSQL).executeAndFetch(Producto.class);
        } catch(Exception e){
            registraLog.error("Error selectALL con {}", selectALLSQL, e);
        }
        return res;
    }   
    
    // Retorna el objeto cuyo PK en t es seleccionado en la BD
    public Producto selectId(Integer id) {
            String sql = "SELECT * FROM PRODUCTO WHERE id = :id ";
            
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Producto> res = 
                        con.createQuery(sql)
                                .addParameter("id", id)
                                .executeAndFetch(Producto.class);
                              
                if (res.size() > 1) 
                    registraLog.error("ERROR BUSCAR ELEMENTO {} en selectId", id); //manejar el ERROR producto VACIO o GENERAR UN ERROR
                
                registraLog.info("SELECT ID con {}", id);
                return res.get(0);
                
            } catch(Exception e){
                registraLog.error("Error selectId con {} ", sql, e);
            }
            registraLog.error("ERROR AL NO ENCONTRAR EL ELEMENTO en SelectId con {}", id);
            return null;
    } 
    
     
    public void delete(Integer id) {
        String deleteSQL = "DELETE FROM PRODUCTO WHERE id = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
           con.createQuery(deleteSQL)
                    .addParameter("id", id).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error Delete con {}", deleteSQL, e);
        }
    }
    
     public void insert(Producto p) {
        String insertSQL = "INSERT INTO PRODUCTO (id, nombre, precio,stock, img) VALUES (:id, :nombre, :precio, :stock, :img)";
        System.out.println(insertSQL);
        //try (Connection con = Conexion.getConexion()) {
        try (Connection con = Sql2oDAO.getSql2o().open()) {
             con.createQuery(insertSQL).bind(p).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error al Insertar con {}", insertSQL, e);
        }
        registraLog.info("FINALIZO LA INSERCION {}", insertSQL);
    } 
    
     public void update(Producto p) {
        String updateSQL = "UPDATE PRODUCTO SET id = :id, nombre = :nombre, precio = :precio, stock = :stock, fecha = :fecha WHERE id = :id";
        System.out.println(updateSQL);
        try (Connection con = Sql2oDAO.getSql2o().open()) {
          con.createQuery(updateSQL).bind(p).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error Update con {}", updateSQL, e);
        }
    }
     
     // productos vendidos
      public List<String> productoxCArrito(String ini, String fin) {
            List<String> cart= new ArrayList();
           
            String sql = "SELECT  nombre FROM CARRITO, "
                    + "PRODUCTOXCARRITO , PRODUCTO WHERE CARRITO.IDCARRITO=PRODUCTOXCARRITO.IDCARRITO "
                    + "and PRODUCTOXCARRITO.IDPRODUCTO=PRODUCTO.ID and fecha BETWEEN :ini AND :fin "
                    + "AND estado like 'cerrado' GROUP BY producto.id";
            
            try (Connection con = Sql2oDAO.getSql2o().open()){
                       
                cart= 
                        con.createQuery(sql)
                                .addParameter("ini", ini)
                                .addParameter("fin", fin)
                                .executeAndFetch(String.class);
                                
                        
                }
            catch(Exception e){
                    registraLog.error("error en  consultar");
                    System.out.println(e);
            }
            return cart;  
    } // la cantidad de productos vendidos
     public List<String> cantidadVendidas(String ini, String fin) {
            
            List<Integer> cant= new ArrayList();
            List<String> cart= new ArrayList();
            String sql = "SELECT   cantProducto*COUNT(*) AS total FROM CARRITO, "
                    + "PRODUCTOXCARRITO , PRODUCTO WHERE CARRITO.IDCARRITO=PRODUCTOXCARRITO.IDCARRITO "
                    + "and PRODUCTOXCARRITO.IDPRODUCTO=PRODUCTO.ID and fecha BETWEEN :ini AND :fin "
                    + "AND estado like 'cerrado' GROUP BY producto.id";
            try (Connection con = Sql2oDAO.getSql2o().open()){
                       
                cant= 
                        con.createQuery(sql)
                                .addParameter("ini", ini)
                                .addParameter("fin", fin)
                                .executeAndFetch(Integer.class);
                                
                        
            }
               
            catch(Exception e){
                    registraLog.error("error en  consultar");
                    System.out.println(e);
            }
            for(int i=0;i<cant.size();i++)
                cart.add(cant.get(i)+" ");
            
            
            return cart;  
    }
}

