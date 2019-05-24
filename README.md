<div align="center">

![banner](GitHub.png)

# Código en Java del servicio Rest API para Timbrar y Cancelar

![Java badge](subtitulo-badge.png)

</div>

Este es el ejemplo de uso para consumir el servicio **Rest API en Java  para generar Facturas, Notas, Recibos, Nómina, Carta Porte, Complemento de Pagos, etc)**, te devuelve la **versión impresa en PDF** adicionalmente podrás **enviar por correo el CFDI**, todo esto usando  como datos de entrada una cadena en formato JSON.

Este servicio es ideal para usarlo en aplicaciones móviles y/o software que implemente llamadas tipo Rest, el usuario no necesita saber ninguna regla del SAT ni programar la generación del XML de acuerdo al anexo 20, nosotros realizamos todo esto facilitando el proceso de integración de tu sistema y/o aplicación para cumplir con lo que solicita el SAT.

Además el servicio Rest Api permitirá **cancelar uno o varios CFDI**

## Requerimientos

Java SE-1.8 en adelante, se recomienda usar la última versión de Java.

## Instalación

Obten la última versión de FacturoPorTi Java en:

    git clone https://github.com/facturoporti/factura-electronica-Java

El projecto tiene agregados los archivos de ejemplo para diferentes tipos de CFDI,  puedes descargar las últimas versiones desde las siguientes ligas para integrar los a tu proyecto, contienen todo lo necesario para generar un CFDI solamente deberá de actualizarse la **fecha de creación** que esta en el JSON:

    http:/software.facturoporti.com.mx/TaaS/Json/Factura.json
    http:/software.facturoporti.com.mx/TaaS/Json/ComplementoPagos.json
    http:/software.facturoporti.com.mx/TaaS/Json/CartaPorte.json  
    http:/software.facturoporti.com.mx/TaaS/Json/NotaCargo.json
    http:/software.facturoporti.com.mx/TaaS/Json/NotaCredito.json
    http:/software.facturoporti.com.mx/TaaS/Json/ReciboHonorarios.json
    http:/software.facturoporti.com.mx/TaaS/Json/ReciboArrendamiento.json
    http:/software.facturoporti.com.mx/TaaS/Json/ReciboDonativo.json
    http:/software.facturoporti.com.mx/TaaS/Json/Cancelar.json

## Timbrar

Descarga el repositorio que contiene los archivos necesarios para realizar el proceso de timbrado, en caso de ser necesario podrás actualizar los archivos de entrada en formato Json que se en listan en la parte de arriba. 

Para el caso del timbrado será necesario que el archivo que selecciones le **actualices la fecha** al atributo Fecha que esta dentro del Json el valor debe de ser la **fecha actual o menor a 72 horas** si no lo haces marcara error de validación y no se timbrá el archivo.

```Java

public class ConsumirApiRest {
    
    private static final String MAIN_PATH = "C:\\Alejandro\\ConsumirApiRest\\resources\\";
    private static final String URL_API = "http://wcfpruebas.facturoporti.com.mx/Timbrado/Servicios.svc/";
    
    public static void main(String[] args) throws Exception {
              	
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
		 
		 
    	 //Codigo para realizar la cancelacion de CFDI
		 //CancelarCFDI();
		         
    }
}

```

## Probar Timbrado CFDI

**Ejecuta o depura la aplicación ** automáticamente se generará el CFDI utilizando el servicio Rest API, al término obtendrás un objeto Json de respuesta: 

```Java
{
  "Estatus": {
  "Codigo": "000",
  "Descripcion": "Timbrado del CFDI realizado con éxito",
  "DetieneEjecucionProveedor": false,
  "Fecha": "2019-05-22T16:10:42",
  "InformacionTecnica": "Ok"
  },
  "CFDITimbrado": {
    "Respuesta": {
    "CFDIXML": "XML en base 64",
    "CadenaOriginal": "||1.1|B225EEB3-EF95-4AA8-A3E2-EA79B88DF01E|2019-05-22T16:10:42|DAL050601L35|E73GPv8I7Kdvq+iFLhQF+24NTwm6Rw39zZgBHuMfFrYtiJSYwd322TdqHmrqo26T9kYYHE0V49Xx2g4Y4UIH199InCDIMiNL8xxm6it33jax9EZXDgk/TwPedlzy3sqBBVvcaPrGA3RhIvmkoNHrt56SsEiAAqRlehb3ihNtMmgP9CvDDZICORkxyN8R/+OYF37187ye5alugIRNtZYT/rJ9M9H83Kz44Xc4tOpgVdi8I9t/xKs6MF1mlUNIPoPLVb4CqzK3gRQGX2W2D7dAffTq6I5WRMmHrSNBSRvk/1o8DbMQxUzPBSuuWl7EGEVLKbnjhLSwqkW2iwIqKKFfsQ==|20001000000300022323||",
    "CadenaOriginalCFD": "||3.3|AB|1|2019-05-21T23:59:15|99|30001000000300023708|100.00|MXN|1|100.00|I|PUE|06470|AAA010101AAA|Empresa Patito|601|SSF1103037F1|Scafandra Software Factory SA de CV|P01|84111506|1.00|E48|Servicio|Recibo de donativo de una ambulancia|100.00|100.00|1.1|123456789|2019-05-14|Este comprobante ampara un donativo, el cual será destinado por la donataria a los fines propios de su objeto social. En el caso de que los bienes donados hayan sido deducidos previamente para los efectos del impuesto sobre la renta, este donativo no es deducible. La reproducción no autorizada de este comprobante constituye un delito en los términos de las disposiciones fiscales. Autorización publicada por la Secretaría de Hacienda y Crédito Público Número||",
    "EmailEnviado": "false",
    "Fecha": "2019-05-22T16:10:42",
    "IdVersionTimbrado": "1.1",
    "NoCertificado": "20001000000300022323",
    "PDF": "PDF en base 64",
    "RfcProvCertif": "DAL050601L35",
    "SelloCFD": "E73GPv8I7Kdvq+iFLhQF+24NTwm6Rw39zZgBHuMfFrYtiJSYwd322TdqHmrqo26T9kYYHE0V49Xx2g4Y4UIH199InCDIMiNL8xxm6it33jax9EZXDgk/TwPedlzy3sqBBVvcaPrGA3RhIvmkoNHrt56SsEiAAqRlehb3ihNtMmgP9CvDDZICORkxyN8R/+OYF37187ye5alugIRNtZYT/rJ9M9H83Kz44Xc4tOpgVdi8I9t/xKs6MF1mlUNIPoPLVb4CqzK3gRQGX2W2D7dAffTq6I5WRMmHrSNBSRvk/1o8DbMQxUzPBSuuWl7EGEVLKbnjhLSwqkW2iwIqKKFfsQ==",
    "SelloSAT": "Yc4TszQ/y5L56XY128rnOWZaScqQ4d6iew48goun0P5lToGAKv7ApEm6myKMj4/XNF4vbHZrriebUU+BJPbqjO+b6+K3MuOX1wgKfPGkj67+pz89reME/O17BZP5nk0+9iixGi7PkEIJ37QKEtfg6AM5LHZnigMHZtWnaFJCqz//eSO/OjB1LQFP9lzbhgBJXk6YLrKkIRPjHpW1X1bVDYHpIWbWAjUPYR1kOnxMaERDqejLiaZ9ahqHKSzxX6Ecdmnzo/R1UCghrEzy9mDoAihp1LdQtgiHkN3z+APjEAelRNnjl1ar9xKjn6hX+un3s7WHOrOHJKEIRwRWE4lV9g==",
    "TimbreXML": "Timbre en base 64",
    "UUID": "B225EEB3-EF95-4AA8-A3E2-EA79B88DF01E"
    }
  }
}
```

Los atributos ** CFDIXML, TimbreXML y PDF estan en Base64 ** se deberán de convertir a texto para obtener el XML y/o timbre del CFDI, en el caso del PDF lo podrán guardar o convertir de manera binaria para obtener la representación impresa.

Versión de Java usada

```
Java SE-1.8 
```


## Cancelar

Cuando se va a cancelar uno o varios CFDI se debe de actualizar los valores del Json como RFC, usuario, password, certificado PFX, etc. 

```Java

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

```

## Probar Cancelación de CFDI

Ejecuta el método para cancelar mediante el cúal se llamará el servicio de Rest Api. Antes de ejecutar el código deberás de actualizar los valores con tus datos fiscales; al término de la ejecución obtendrás un objeto Json de respuesta: 

```json
 {
  "Estatus": {
    "Codigo": null,
    "Descripcion": null,
    "DetieneEjecucionProveedor": false,
    "Fecha": null,
    "InformacionTecnica": null
  },
  "FoliosRespuesta": [
    {
      "Estatus": {
        "Codigo": "000",
        "Descripcion": "Se envió con éxito el CFDI para ser cancelado en el SAT recuerde que debe de esperar de 24 a 72 horas para que el SAT refleje los cambios",
        "DetieneEjecucionProveedor": false,
        "Fecha": "2019-05-24T00:16:56",
        "InformacionTecnica": null
      },
      "UUID": "D59C1F53-C70F-40C9-8B03-3C729B4416F6"
    }
  ]
}
```

## Documentación Adicional

Si deseas agregar o eliminar información descarga el **diccionario de datos** que contiene todos los atributos y su descripción de los valores que se permiten http://software.facturoporti.com.mx/TaaS/Diccionario/Rest-Api-V-2.3.7.xlsx



## Contribuir

1. Fork el repositorio 

2. Clona el repositorio

    git clone git@github.com:yourUserName/factura-electronica-Java.git


3. Crea una rama 
```
    git checkout desarrollo
    git pull al original desarrollo
    # Podrás escoger el nombre de tu rama
    git checkout -b <feature/my_branch>
```
4. Haz los cambios necesarios y commit para carga los
```
    git add .
    git commit -m "mis cambios"
```
5. Envía los cambios a GitHub
```
    git push origin <feature/my_branch>
```

***-

## License

Desarrollado en México por [FacturoPorTi](https://www.FacturoPorTi.com). Available with [MIT License](LICENSE).
****
