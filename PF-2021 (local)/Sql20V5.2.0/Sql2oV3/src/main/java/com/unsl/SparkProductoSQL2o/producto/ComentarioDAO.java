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

import static com.unsl.SparkProductoSQL2o.producto.ProductoControlador.registraLog;
import java.lang.invoke.MethodHandles;
import org.sql2o.Connection;
import java.util.List;
import lombok.Data; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Sql2oDAO;
public class ComentarioDAO {

    /**
     * 
     */
    
  public void insert(Comentario C) {
        String insertSQL = "INSERT INTO comentario (idComentario,id, descripcion,fecha) VALUES (:idComentario,:id,:descripcion, :fecha)";
        System.out.println(insertSQL);
        //try (Connection con = Conexion.getConexion()) {
        try (Connection con = Sql2oDAO.getSql2o().open()) {
             con.createQuery(insertSQL).bind(C).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error al Insertar con {}", insertSQL, e);
        }
        registraLog.info("FINALIZO LA INSERCION {}", insertSQL);
    }

    public List<Comentario> comentariosProducto(int id) {
        String sql = "SELECT * FROM comentario WHERE id >= :id";
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<Comentario> res = con
                    .createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Comentario.class);
                
        return res;
    }
    }
}
