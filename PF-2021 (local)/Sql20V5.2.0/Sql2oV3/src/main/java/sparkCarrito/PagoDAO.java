/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparkCarrito;
/**
 *
 * @author franco
 */
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.sql2o.Connection;
import java.util.List;
import lombok.Data; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static sparkCarrito.CarritoControlador.registraLog;
import util.Sql2oDAO;

@Data
public class PagoDAO {
     
    //telefono, email    :telefono,:email
        /*private int nroPago;
    private String nombre;
    private String email;
    private int telefono;
    private int nroCarrito;
    * 
    private Date fecha_pago;
    private int cuil;
    private String condicionIva;
    private String tipo_pago;
    private String entrega;
        */
     
    public void insert(Pago p) {
        
        String insertSQL = "INSERT INTO PAGO "
                + "(tipo_pago,fecha_pago,cuilCliente,nroCarrito,condicionIva,entrega )"
                + " VALUES "
                + "(:tipo_pago,CURRENT_DATE,:cuil,:nroCarrito,:condicionIva,:entrega )";

        System.out.println(insertSQL);
        System.out.println(Sql2oDAO.getSql2o().open());
        System.out.println("----------------------------------------------------------------------");
        System.out.println(p);
        
        //try (Connection con = Conexion.getConexion()) {
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            
             con.createQuery(insertSQL).bind(p).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error al Insertar con {}", insertSQL, e);
        }
        registraLog.info("FINALIZO LA INSERCION {}", insertSQL);
    } 
     public void update(Pago p) {
        String updateSQL = "UPDATE PAGO SET   TIPO_PAGO = :Tipo_pago WHERE NROPAGO = :nroPago";
        System.out.println(updateSQL);
        try (Connection con = Sql2oDAO.getSql2o().open()) {
          con.createQuery(updateSQL).bind(p).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error Update con {}", updateSQL, e);
        }
    }
    
}
