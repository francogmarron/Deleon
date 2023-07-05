/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparkCarrito;

/**
 *
 * @author Agustin
 */

import lombok.Data; 
import java.util.Date;


@Data
public class Carrito {
    private int idCarrito;
    private int idUsuario;
    private Date fecha;
    private String estado;
}


