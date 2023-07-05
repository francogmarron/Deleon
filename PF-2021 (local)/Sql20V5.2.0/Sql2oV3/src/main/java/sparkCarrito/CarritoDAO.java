/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparkCarrito;

import com.unsl.SparkProductoSQL2o.producto.Producto;
import java.util.Date;
import java.util.List;
import org.sql2o.Connection;
import static sparkCarrito.CarritoControlador.registraLog;
import util.Sql2oDAO;

/**
 *sadadadas
 * @author Agustin
 */
public class CarritoDAO {
  
   
      public void insert(Carrito c) {
        String insertSQL = "INSERT INTO CARRITO (idCarrito,idUsuario,fecha,estado) VALUES (:idCarrito,:idUsuario,:fecha,:estado)";
        System.out.println(insertSQL);
        //try (Connection con = Conexion.getConexion()) {
        try (Connection con = Sql2oDAO.getSql2o().open()) {
             con.createQuery(insertSQL).bind(c).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error al Insertar con {}", insertSQL, c);
        }
        registraLog.info("FINALIZO LA INSERCION {}", insertSQL);
    }
      
         public List<Carrito> selectIdCarritos(int idUsuario) {
            String sql = "SELECT * FROM CARRITO WHERE  idUsuario = :idUsuario and estado like 'cerrado' ";
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Carrito> cart = 
                        con.createQuery(sql)
                                .addParameter("idUsuario", idUsuario)
                                .executeAndFetch(Carrito.class);
                return cart;
                
            }
    }
         
         public Carrito selectId(int idUsuario) {
            String sql = "SELECT * FROM CARRITO WHERE idUsuario = :idUsuario ";
            
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Carrito> cart = 
                        con.createQuery(sql)
                                .addParameter("idUsuario", idUsuario)
                                .executeAndFetch(Carrito.class);
                              
                if (cart.size() > 1) 
                    registraLog.error("ERROR BUSCAR ELEMENTO {} en selectId", idUsuario); //manejar el ERROR producto VACIO o GENERAR UN ERROR
                
                registraLog.info("SELECT ID con {}", idUsuario);
                return cart.get(0);
                
            } catch(Exception e){
                registraLog.error("Error selectId con {} ", sql, e);
            }
            registraLog.error("ERROR AL NO ENCONTRAR EL ELEMENTO en SelectId con {}", idUsuario);
            return null;
    }
         
        public Integer devolverUltimo() {
        String sql = "SELECT MAX(idCarrito) AS idCarrito FROM CARRITO";
        System.out.println(sql);
        //try (Connection con = Conexion.getConexion()) {
        try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Integer> cart = 
                        con.createQuery(sql)
                                .executeAndFetch(Integer.class);
                if(cart.size()==0)
                    return 0;
                return cart.get(0);
            } 
            
            catch(Exception e){
                registraLog.error("Error selectId con {} ", sql, e);
            }
            registraLog.error("ERROR AL NO ENCONTRAR EL ELEMENTO en SelectId con {}");
            return null;
    }
         public Carrito selectIdCarrito(int idCarrito) {
            String sql = "SELECT * FROM CARRITO WHERE idCarrito = :idCarrito ";
            
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Carrito> cart = 
                        con.createQuery(sql)
                                .addParameter("idCarrito", idCarrito)
                                .executeAndFetch(Carrito.class);
                              
                if (cart.size() > 1) 
                    registraLog.error("ERROR BUSCAR ELEMENTO {} en selectId", idCarrito); //manejar el ERROR producto VACIO o GENERAR UN ERROR
                
                registraLog.info("SELECT ID con {}", idCarrito);
                return cart.get(0);
                
            } catch(Exception e){
                registraLog.error("Error selectId con {} ", sql, e);
            }
            registraLog.error("ERROR AL NO ENCONTRAR EL ELEMENTO en SelectId con {}", idCarrito);
            return null;
    }
        public void cerrarCarro(Carrito c){
        String updateSQL = "UPDATE CARRITO SET estado = :estado WHERE idcarrito = :idcarrito";
        System.out.println(updateSQL);
        try (Connection con = Sql2oDAO.getSql2o().open()) {
          con.createQuery(updateSQL).bind(c).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error Update con {}", updateSQL, e);
        }
    }
        public Carrito verEstadoCarrito (int idUsuario){
         String sql = "SELECT * FROM CARRITO WHERE estado like 'abierto' and idUsuario = :idUsuario";
         try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Carrito> cart = 
                        con.createQuery(sql)
                                .addParameter("idUsuario", idUsuario)
                                .executeAndFetch(Carrito.class);
                              
                if (cart.size() >= 1) 
                    registraLog.error("ERROR  en ver estado carrito"); //manejar el ERROR producto VACIO o GENERAR UN ERROR
                if(cart.size() == 0){
                    Carrito carrito = new Carrito();
                    carrito.setIdUsuario(idUsuario);
                    java.util.Date fecha = new Date();
                    carrito.setFecha(fecha);
                    carrito.setEstado("abierto");
                    insert(carrito);
                    carrito.setIdCarrito(devolverUltimo());
                    return(carrito);
                }
              
                return cart.get(0);
                
            } catch(Exception e){
                registraLog.error("Error selectId con {} ", sql, e);
            }
            registraLog.error("ERROR AL NO ENCONTRAR EL ELEMENTO en SelectId con {}", idUsuario);
            return null;
    }
}