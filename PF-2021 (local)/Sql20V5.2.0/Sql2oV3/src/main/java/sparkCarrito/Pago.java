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
import lombok.Data; 
import java.util.Date;


@Data
public class Pago {
    private int nroPago;
    private int nroCarrito;
    private String fecha_pago;
    private int cuil;
    private String condicionIva;
    private String tipo_pago;
    private String entrega;
    
}
