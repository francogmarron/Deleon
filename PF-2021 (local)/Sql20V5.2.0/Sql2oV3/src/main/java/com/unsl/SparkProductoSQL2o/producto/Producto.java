/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unsl.SparkProductoSQL2o.producto;

import lombok.Data; 
import java.util.Date;
@Data
public class Producto {
    private Integer id;
    private String nombre;
    private Double precio;
    private Double stock;
    private String img;
 /*
    public Integer getId(){return this.id;}
    public String getNombre(){return this.nombre;}
    public Double getPrecio(){return this.precio;}
    public Double getStock(){return this.stock;}
    public String getImg(){return this.img;}
    
   
    public void setId(Integer id){this.id=id;}
    public void setNombre(String nombre){this.nombre=nombre;}
    public void setPrecio(Double precio){this.precio=precio;}
    public void setStock(Double stock){this.stock=stock;}
    public void setImg(String img){this.img=img;}*/
         
    
    
    
    
    
   /*public static List<Producto> buscarProductos() {
        String sql = "Select * from producto;";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            List<Producto> res = con.createQuery(sql).executeAndFetch(Producto.class);
        }
        return res;
    }
    
    public static List<Producto> buscarProductosxCantidad(int cantidad) {
        String sql = "SELECT * FROM producto WHERE cantidad >= :cantidad";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            List<Producto> res = con
                .createQuery(sql)
                .addParameter("cantidad", cantidad)
                .executeAndFetch(Producto.class);
        }
        return res;
    }
    
    public void insertar() {
        String sql = "INSERT INTO producto " + "VALUES(:id, :nombre, :precio, :cantidad,:fecha)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
                .addParameter("id" , getId())
                .addParameter("nombre" , getNombre())
                .addParameter("precio" , getPrecio())
                .addParameter("cantidad", getCantidad())
                .addParameter("fecha" , getFecha())
                .executeUpdate();
        }
    }*/
}


