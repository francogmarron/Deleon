/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparkProductoXcarrito;

import com.unsl.SparkProductoSQL2o.producto.Producto;
import org.sql2o.Connection;
import util.Sql2oDAO;
import com.unsl.SparkProductoSQL2o.producto.Producto;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.Connection;
import static sparkProductoXcarrito.ProductoXcarritoControlador.registraLog;
import util.Sql2oDAO;
 

/**
 *
 * @author Agustin
 */
public class ProductoXcarritoDAO {
    
    public void insert(ProductosxCarrito pxc) {
        if(devuelveCant(pxc.getIdProducto(),pxc.getIdCarrito())>=1){
            int var1 = devuelveCant(pxc.getIdProducto(),pxc.getIdCarrito());
            pxc.setCantProducto(var1+1);
            update(pxc);  
        }
        else{
        String insertSQL = "INSERT INTO PRODUCTOXCARRITO (idCarrito, idProducto, cantProducto) VALUES (:idCarrito, :idProducto, :cantProducto)";
        System.out.println(insertSQL);
        //try (Connection con = Conexion.getConexion()) {
        try (Connection con = Sql2oDAO.getSql2o().open()) {
             con.createQuery(insertSQL).bind(pxc).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error al Insertar con {}", insertSQL, e);
        }
        registraLog.info("FINALIZO LA INSERCION {}", insertSQL);
       }
    } 
    public List<Integer> selectProductosxCarritos(int idCarrito){
       String sql = "SELECT idProducto FROM productoxcarrito WHERE idCarrito like :idCarrito";
       registraLog.info("En ProductoDAO {} ", idCarrito);
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Integer> res = con
                    .createQuery(sql)
                    .addParameter("idCarrito", idCarrito)
                    .executeAndFetch(Integer.class);
                return res;
            }
    }
     public List<Integer> selectProductosxCarritosCantidad(int idCarrito){
       String sql = "SELECT cantProducto FROM productoxcarrito WHERE idCarrito like :idCarrito";
       registraLog.info("En ProductoDAO {} ", idCarrito);
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Integer> res = con
                    .createQuery(sql)
                    .addParameter("idCarrito", idCarrito)
                    .executeAndFetch(Integer.class);
                return res;
            }
    }
     
    public void delete(ProductosxCarrito pxc){
        int cantidad = devuelveCant(pxc.getIdProducto(),pxc.getIdCarrito());
        if (cantidad==1) {
            String deleteSQL = "DELETE FROM PRODUCTOXCARRITO WHERE idProducto = :idProducto and idCarrito = :idCarrito";
            System.out.println(deleteSQL);
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                con.createQuery(deleteSQL).bind(pxc).executeUpdate();
            } catch(Exception e){
            registraLog.error("Error Delete con {}", deleteSQL, e);
            }
        
        }else{
            cantidad = cantidad-1;
            pxc.setCantProducto(cantidad);
            update(pxc);
        }
        
    }
    
    public void deleteAll(ProductosxCarrito pxc){
        String deleteSQL = "DELETE FROM PRODUCTOXCARRITO WHERE idProducto = :idProducto and idCarrito = :idCarrito";
            System.out.println(deleteSQL);
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                con.createQuery(deleteSQL).bind(pxc).executeUpdate();
            } catch(Exception e){
            registraLog.error("Error Delete con {}", deleteSQL, e);
            }
    }
    
    
    
   
    
    
    
    public void insertPrueba(ProductosxCarrito pxc){
        
        
        
        /*
        List<Integer> idList = new ArrayList<>();
        idList = selectProductosxCarritos(pxc.getIdCarrito());
        boolean a = false;
        for (int i = 0; i <idList.size() && !a ; i++) {
            if(idList.get(i) == pxc.getIdProducto() ){
                a = true;
            }
        }
        if(a){
            int var1 = devuelveCant(pxc.getIdProducto());
            pxc.setCantProducto(var1+1);
            update(pxc);
        }
        else{
            insert(pxc);
        }
        */
    }
    
    public void update(ProductosxCarrito p) {
        String updateSQL = "UPDATE PRODUCTOXCARRITO SET idCarrito = :idCarrito, idProducto = :idProducto, cantProducto = :cantProducto WHERE idProducto = :idProducto and idCarrito = :idCarrito";
        System.out.println(updateSQL);
        try (Connection con = Sql2oDAO.getSql2o().open()) {
          con.createQuery(updateSQL).bind(p).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error Update con {}", updateSQL, e);
        }
    }
    
    public Integer devuelveCant(int idProducto, int idCarrito){
       String sql = "SELECT cantProducto FROM productoxcarrito WHERE idProducto like :idProducto and idCarrito = :idCarrito";
       registraLog.info("En ProductoDAO {} ", idProducto);
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Integer> res = con
                    .createQuery(sql)
                    .addParameter("idProducto", idProducto)
                    .addParameter("idCarrito", idCarrito)
                    .executeAndFetch(Integer.class);
                if(res.isEmpty())
                    return 0;
                return res.get(0) ;
            }
    }   
    
}
