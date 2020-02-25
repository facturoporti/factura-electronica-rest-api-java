import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class ConsumirApiRest {
    
    private static final String MAIN_PATH = "C:\\Alejandro\\ConsumirApiRest\\Proyecto\\resources\\";
    private static final String URL_API = "http://wcfpruebas.facturoporti.com.mx/Timbrado/Servicios.svc/";
    
    public static void main(String[] args) throws Exception {
             
    	/*
    	 File file = FileUtils.getFile(MAIN_PATH + "Factura.json");
    	
    	 // Se debe de actualizar la Fecha actual en el archivo ya que si no marcara error de timbrado
    	 String data = FileUtils.readFileToString(file, "UTF-8");
    	 
    	 //System.out.println("" + data);    
        
    	 System.out.println("Inicia Timbrado");
    	 System.out.println("");
    	 
    	 Client client = ClientBuilder.newClient();
		 
		 WebTarget target = client.target(URL_API + "ApiTimbrarCFDI");		
		 String response = target.request().post(Entity.entity(data, MediaType.APPLICATION_JSON), String.class);
	      		 
		 // Se debe de leer los valores del servicio de acuerdo a las necesidades de cada caso
		 System.out.println("Respuesta del servicio: " + response);   
		 */
		 
    	 //Codigo para realizar la cancelacion de CFDI
		 CancelarCFDI();
		         
    }
    
    public static void CancelarCFDI() throws Exception {
   	 
   	 File file = FileUtils.getFile(MAIN_PATH + "Cancelar.json");
   	
   	 String data = FileUtils.readFileToString(file, "UTF-8");

   	 //System.out.println("" + data);    
       
   	 System.out.println("");
	 System.out.println("Inicia Cancelacion");
	 
   	 Client client = ClientBuilder.newClient();
		 
	WebTarget target = client.target(URL_API + "ApiCancelarCFDI");		
	String response = target.request().post(Entity.entity(data, MediaType.APPLICATION_JSON), String.class);	      		 
	System.out.println("Respuesta de cancelacion: " + response);    
		         
   }
    
}