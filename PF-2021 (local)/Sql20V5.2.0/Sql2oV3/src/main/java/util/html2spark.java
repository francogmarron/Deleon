/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;

 public class html2spark {
     Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
     
     public Object getParams2POJOsimple (Object obj, Request request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        String atributo;
        String valorAtributo;
        String nombreMetodo;
        Method setNombreMetodo;
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        
        for (Field field:fields) {
              atributo=field.getName(); // leer el nombre del atributo
              valorAtributo = request.queryParams(atributo); // buscar el valor en request (html) del atributo
              if (valorAtributo != null) {
                 nombreMetodo = "set" + StringUtils.capitalize(atributo); // PASA LA PRIMERA A MAYUSCULAS
                 switch (field.getGenericType().getTypeName()) {
                    case "java.lang.String":
                        setNombreMetodo = obj.getClass().getMethod(nombreMetodo, String.class);
                        setNombreMetodo.invoke(obj, valorAtributo); // pass arg con y SIN TRANSFORMACION
                        break;
                    case "java.lang.Double":
                        setNombreMetodo = obj.getClass().getMethod(nombreMetodo, Double.class);
                        setNombreMetodo.invoke(obj, Double.valueOf(valorAtributo)); 
                        break;
                    case "java.lang.Integer":
                        setNombreMetodo = obj.getClass().getMethod(nombreMetodo, Integer.class);
                        setNombreMetodo.invoke(obj, Integer.valueOf(valorAtributo)); 
                        break;
                    case "java.util.Date":
                        System.out.println("Date " + nombreMetodo);
                        setNombreMetodo = obj.getClass().getMethod(nombreMetodo, Date.class);
                        setNombreMetodo.invoke(obj, Date.valueOf(valorAtributo));
                        break;
                    default:
                        registraLog.error(" getParams2POJOsimple: VALOR ILEGAL DEL TIPO DE PARAMNAME {}", field.getGenericType().getTypeName());
                        throw new IllegalArgumentException("VALOR ILEGAL DEL TIPO DE PARAMNAME en request: " + field.getGenericType().getTypeName());
                    }   
              }
            }
        return obj;
     }
     
     /* Si se quiere leer una lista objetos personas con atributos nombre y edad
     en el request los parametros recibidos deben ser nombre0 y edad0, nombre1 y edad1
     por lo que retorna una lista con dos objetos personas con los valores recibidos en request
     nbreClase es el nombre de la clase, por ejemplo "Persona"
     request es recibido de html
     cantidadElementosLista es la cantidad de elementos sobre los que tiene que iterar. 
          Podría suceder que tuvieran nombre0 y edad0, nombre2 y edad2 (el 1 fue borrado) entonces el valor es 3.
          Los nombres de los atributos deben comenzar en 0
     */
    public <T> List<T> getParams2ListPOJO(T obj, Request request, int cantidadElementosLista) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
            
        
        Class cls = obj.getClass(); 
        String nbreClase = cls.getName();
        Class  c = Class.forName(nbreClase);
        List<T> objs = new ArrayList<T>();
        
        String indice;
        for (int i = 0; i <= cantidadElementosLista-1; i++) {
            Object o = c.newInstance();
            indice = String.valueOf(i);
            o = getParams2POJO1(o, request, indice);
            try{
                objs.add((T)o);
            } catch (Exception e){                
                registraLog.error("\nError lectura setParams2ListPOJO con clase: {} e indice {} ",nbreClase, indice, e);
            }
        registraLog.info("setParams2ListPOJO con clase: {} e indice: {} ", nbreClase, indice);
        
        }
        return objs;
    }
    
    public Object getParams2POJO (Object obj, Request request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return getParams2POJO1(obj, request, "");
    }

    
     public Object getParams2POJO1 (Object obj, Request request, String Indice) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        String atributo;
        String valorAtributo;
        String nombreMetodo;
        Method setNombreMetodo;
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        
        for (Field field:fields) {
              atributo=field.getName(); // leer el nombre del atributo
              valorAtributo = request.queryParams(atributo+Indice); // buscar el valor en request (html) del atributo
              if (valorAtributo != null) {
                 nombreMetodo = "set" + StringUtils.capitalize(atributo); // PASA LA PRIMERA A MAYUSCULAS
                 switch (field.getGenericType().getTypeName()) {
                    case "java.lang.String":
                        setNombreMetodo = obj.getClass().getMethod(nombreMetodo, String.class);
                        setNombreMetodo.invoke(obj, valorAtributo); // pass arg con y SIN TRANSFORMACION
                        break;
                    case "java.lang.Double":
                        setNombreMetodo = obj.getClass().getMethod(nombreMetodo, Double.class);
                        setNombreMetodo.invoke(obj, Double.valueOf(valorAtributo)); 
                        break;
                    case "java.lang.Integer":
                        setNombreMetodo = obj.getClass().getMethod(nombreMetodo, Integer.class);
                        setNombreMetodo.invoke(obj, Integer.valueOf(valorAtributo)); 
                        break;
                    case "java.util.Date":
                        System.out.println("Date " + nombreMetodo);
                        setNombreMetodo = obj.getClass().getMethod(nombreMetodo, Date.class);
                        setNombreMetodo.invoke(obj, Date.valueOf(valorAtributo));
                        break;
                    default:
                        registraLog.error(" getParams2POJO1: VALOR ILEGAL DEL TIPO DE PARAMNAME {}", field.getGenericType().getTypeName());
                        throw new IllegalArgumentException("VALOR ILEGAL DEL TIPO DE PARAMNAME en request: " + field.getGenericType().getTypeName());
                    }   
              }
            }
  return obj;   
 }
 }