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

import lombok.Data; 
import java.util.Date; 


@Data
public class Comentario {
    
    private Integer idComentario;
    private Integer id;
    private String descripcion;
    private Date fecha;
    /*
   public Integer getidComentario(){return this.idComentario;}
   public Integer getid(){return this.id;}
   public String getdescripcion(){return this.descripcion;}
   public Date getfecha(){return this.fecha;}
 
    public void setidComentario(Integer idComentario){this.idComentario=idComentario;}
    public void setid(Integer id){this.id=id;}
    public void setdescripcion(String descripcion){this.descripcion=descripcion;}
    public Date getfecha(){return this.fecha;}
//private Date fecha;
    //idComentario;
   /// Integer id;
   // descripcion;
   // private Date fecha;
*/
}
