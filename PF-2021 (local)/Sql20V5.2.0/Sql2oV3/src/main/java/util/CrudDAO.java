/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.unsl.SparkProductoSQL2o.producto.ProductoControlador;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import org.sql2o.Connection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @param <T>
 * Esta clase parametrizada contiene todas los métodos genericos de acceso a la BD
 * Cada clase DAO debe heredar con la Clase T específicade esta clase abstracta parametrizada
 */
public abstract class CrudDAO<T> {
     
    protected Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
     
    /* Esta clase es necesaria para poder implementar el acceso al tipo especifico T.class */
    abstract public Class<T> getTClass();
    // Retorna los campos de la clase que son declarados privados y publicos, no los heredados
    //abstract public Field[] getDeclaredFields();
    // Retorna la expresión que accede a la fila por clave primaria
    abstract public String getTablePK();
    // Retorna el nombre de la clase
    abstract public String getTableName();  
     
    public void insert(T t) {
        //String insertSQL = "INSERT INTO " + getTableName() + " " + valuesInsertSQL;
        // valuesInsertSQL = "VALUES(:<attr1>, :<attr2>, :<attr3> ...)";
        // Field[] fields = getDeclaredFields();
        Class cls = t.getClass();
        Field[] fields = cls.getDeclaredFields();
        String valuesInsertSQL = " (";
        String columnsInsertSQL = " (";
        String name;
        for (Field field:fields)
            {
            name=field.getName();
            columnsInsertSQL = columnsInsertSQL + " " + name + " ,";
            valuesInsertSQL = valuesInsertSQL + " :" + name + " ,";
            }
        valuesInsertSQL = valuesInsertSQL.substring(0, valuesInsertSQL.length()-1) + ")";
        columnsInsertSQL = columnsInsertSQL.substring(0, columnsInsertSQL.length()-1) + ")";
        
        String insertSQL = "INSERT INTO " + getTableName() + " " + columnsInsertSQL + " VALUES " + valuesInsertSQL;
        System.out.println(insertSQL);
        //try (Connection con = Conexion.getConexion()) {
        try (Connection con = Sql2oDAO.getSql2o().open()) {
             con.createQuery(insertSQL).bind(t).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error al Insertar con {}", insertSQL, e);
        }
        registraLog.info("FINALIZO LA INSERCION {}", insertSQL);
    } 
    public void update(T t) {
        
        // String setUpdateSQL = "SET <col> = :<attr>, <col> = :<attr> ... WHERE <pkIdCol> = :<pkIdAttr>";
        // Arma la sentencia update para actualizar todas las columnas con los nombres de todos los atributos
        //Field[] fields = getDeclaredFields();
        Class cls = t.getClass();
        Field[] fields = cls.getDeclaredFields();
        String valuesUpdateSQL = "SET ";
        for (Field field:fields)
            {
            String name=field.getName();
            valuesUpdateSQL = valuesUpdateSQL + name + " = :" + name + " ,";
            }
        valuesUpdateSQL = valuesUpdateSQL.substring(0, valuesUpdateSQL.length()-1) + "  ";
        valuesUpdateSQL = valuesUpdateSQL + " WHERE " + getTablePK() + "= :" + getTablePK();
                
        String updateSQL = "UPDATE " + getTableName() + " " + valuesUpdateSQL;
        System.out.println(updateSQL);
        try (Connection con = Sql2oDAO.getSql2o().open()) {
          con.createQuery(updateSQL).bind(t).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error Update con {}", updateSQL, e);
        }
    }
    
    public void delete(T t) {
        String deleteSQL = "DELETE FROM " + getTableName() + " " + " WHERE " + getTablePK() + "= :" + getTablePK();
        try (Connection con = Sql2oDAO.getSql2o().open()) {
          con.createQuery(deleteSQL).bind(t).executeUpdate();
        } catch(Exception e){
            registraLog.error("Error Delete con {}", deleteSQL, e);
        }
    }
    
    public List<T> selectAll() {
        String selectALLSQL = "SELECT * FROM " + getTableName() + ";";
        List<T> res = null;
        try (Connection con = Sql2oDAO.getSql2o().open()) {
           res = con.createQuery(selectALLSQL).executeAndFetch(getTClass());
        } catch(Exception e){
            registraLog.error("Error selectALL con {}", selectALLSQL, e);
        }
        return res;
    }   
    
    public List<T> selectWhere(String where) {
        String selectWhereSQL = "SELECT * FROM " + getTableName() + " WHERE " + where;
        List<T> res=null;
        try (Connection con = Sql2oDAO.getSql2o().open()) {
           res = con.createQuery(selectWhereSQL).executeAndFetch(getTClass());

        } catch(Exception e){
            registraLog.error("Error selectWhere con {} ", selectWhereSQL, e);
        }
        return res;
    }   
    
    public Integer countALL(){
        String countSQL = "SELECT count(*) FROM " + getTableName() + ";";
        Integer res = null;
        try (Connection con = Sql2oDAO.getSql2o().open()) {
              res = con.createQuery(countSQL).executeScalar(Integer.class);
        } catch(Exception e){
            registraLog.error("Error countAll con {} ", countSQL, e);
        }
        return res;
    }
    
    // Retorna el objeto cuyo PK en t es seleccionado en la BD
    public T selectId(Integer id) {
            String sql = "SELECT * FROM " + getTableName() + " WHERE " + getTablePK() + " = :id ";
            
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                List<T> res = con.createQuery(sql).addParameter(getTablePK(), id).executeAndFetch(getTClass());
                              
                if (res.size() > 1) 
                    registraLog.error("ERROR BUSCAR ELEMENTO {} en selectId de CrudDAO", id); //manejar el ERROR producto VACIO o GENERAR UN ERROR
                
                registraLog.info("SELECT ID con {}", id);
                return res.get(0);
                
            } catch(Exception e){
                registraLog.error("Error selectId con {} ", sql, e);
            }
            registraLog.error("ERROR AL NO ENCONTRAR EL ELEMENTO en SelectId con {}", id);
            return null;
    } 
}
