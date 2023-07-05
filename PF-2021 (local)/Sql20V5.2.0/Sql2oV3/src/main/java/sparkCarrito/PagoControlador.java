/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sparkCarrito;

import java.util.HashMap;
import java.util.List;
import spark.Request;
import spark.Response;
import spark.Route;
import sparkCarrito.Pago;
import sparkCarrito.PagoDAO;
import util.Path;
import util.ViewUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  
 * @author franco
 */


public class PagoControlador {
    public static Route pagarCompra = (Request request, Response response) -> {
    HashMap model = new HashMap();
    Pago p = new Pago();   
    PagoDAO pDAO = new PagoDAO();
    Carrito c =new Carrito();
    Carrito c1 =new Carrito();
    CarritoDAO cDAO= new CarritoDAO();
    String element=request.queryParams("nuevaCategoria2");
    String subelement=element.substring(10, 16);
    
    System.out.println("---------------------------0-------------------------------------------");
    //p.setTelefono(Integer.parseInt(request.queryParams("telefono")));
    
    System.out.println("-------------------------------------1---------------------------------");
    System.out.println(request.queryParams("nroCarrito"));
    p.setNroCarrito(Integer.parseInt(request.queryParams("nroCarrito")));
    System.out.println("------------------------------------2----------------------------------");
    
    p.setCuil(Integer.parseInt(request.queryParams("cuil")));
    System.out.println("-----------------------------------3-----------------------------------");
    System.out.println(subelement);
    p.setEntrega(subelement);
    
    subelement=element.substring(41, 49);
    System.out.println(subelement);
    p.setTipo_pago(subelement);
    
    
   
    System.out.println("------------------------------------7----------------------------------");
    p.setCondicionIva(request.queryParams("condicionIva"));
    System.out.println("---------------------------------8-------------------------------------");
 
    System.out.println(p); 
    
    pDAO.insert(p);
    
    //c=cDAO.selectIdCarrito(p.getNroCarrito());
    //c1.setIdCarrito(c.getIdCarrito()+1);
    //cDAO.insert(c1);
    //cDAO.cerrarCarro(c);
    
    model.put("pagos", p);
    return ViewUtil.render(request, model, Path.Template.PAGOS);
      };
 
/*   
  public static Route updateCompra = (Request request, Response response) -> {
      HashMap model = new HashMap();
      
      Pago p = new Pago();
      p.setNroPago(Integer.valueOf(request.queryParams("nropago")));
      p.setTipo_pago(request.queryParams("formaPago"));
      
      PagoDAO pDAO = new PagoDAO();
      pDAO.update(p);
                          
      // Inicializar formulario
      model.put("pago", p);
      return ViewUtil.render(request, model, Path.Template.PAGOS);
    };
  //##<input type="text" name="direccion" id="direccion" placeholder="Direccion">
 */
    public static Route CompraCarrito = (Request request, Response response) -> {
      //Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
      System.out.print("----------------------------------------------------------");
       
        Double monto= Double.parseDouble(request.queryParams("monto"));
        System.out.print(monto);
        Pago p = new Pago();
        HashMap model = new HashMap();
                
        model.put("pago", p);
        model.put("monto", monto);
      return ViewUtil.render(request, model, Path.Template.PAGOS);
    };
    public static Route pagarMercadoPago= (Request request, Response response) -> {
      //Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
      System.out.print("----------------------------------------------------------");
       
        Double monto= Double.parseDouble(request.queryParams("monto"));
        System.out.print(monto);
        
        HashMap model = new HashMap();
        
        
        model.put("monto", monto);
      return ViewUtil.render(request, model, Path.Template.MPTARGETA);
    };
    public static Route rellenarFormulario= (Request request, Response response) -> {
      //Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
      System.out.print("----------------------------------------------------------");
       
        Double monto= Double.parseDouble(request.queryParams("monto"));
        System.out.print(monto);
        
        HashMap model = new HashMap();
        /*
        MercadoPago.SDK.setAccessToken("TEST-2875629348509221-120514-d1ef538dafd2da162df7dcb5db729b4a-1031445391");

            Payment payment = new Payment();
            payment.setTransactionAmount(Float.valueOf(request.getParameter("transactionAmount")))
                   .setToken(request.getParameter("token"))
                   .setDescription(request.getParameter("description"))
                   .setInstallments(Integer.valueOf(request.getParameter("installments")))
                   .setPaymentMethodId(request.getParameter("paymentMethodId"));

            Identification identification = new Identification();
            identification.setType(request.getParameter("docType"))
                          .setNumber(request.getParameter("docNumber")); 

            Payer payer = new Payer();
            payer.setEmail(request.getParameter("email"))
                 .setIdentification(identification);

            payment.setPayer(payer);

            payment.save();

            System.out.println(payment.getStatus());
*/
        
        model.put("monto", monto);
      return ViewUtil.render(request, model, Path.Template.MPTARGETA);
    };
}
