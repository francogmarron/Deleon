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
public class User {
    private int id;
    private String email;
    private String pass;
}
