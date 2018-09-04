package co.edu.escuelaing.arem.proyecto;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Nicolás
 */
public class DataRecollector {
    
    public String readResource(String direction){
        
        String file, resource, contentType = "";
        
         
        if(direction.toLowerCase().contains(".png".toLowerCase())||direction.toLowerCase().contains(".jpg".toLowerCase())){
            byte[] bytesSource = null;
            contentType="image/png";
            String imageLength="";
            try {
                bytesSource = Files.readAllBytes(new File("./resources" + direction).toPath());
                imageLength = "" + bytesSource.length;
            } catch (IOException ex) {
                Logger.getLogger(DataRecollector.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
 
            resource= ("<!DOCTYPE html>"
                    +"<html>"
                    +"<head>"
                    +"<title>Page Title</title>"
                    +"</head>"
                    +"<body>"  
                    
                    +"</body>"  
                    +"<html>");
             
        }

        try{
            BufferedReader bf = new BufferedReader(new FileReader("resources/"+direction));
            String temp = "";
            String bfRead;
            while( (bfRead = bf.readLine())!=null){
                temp=temp+bfRead;                
            }
            resource = temp;
            
        }catch(Exception e){
            System.err.println("No se ha encontrado el archivo");
                
        }
        System.out.println(resource);
        return resource;
       
        
        
        file = "HTTP/1.1 200 OK\r\n"
             + "Content-Type: "+ contentType+"\r\n"
             + "\r\n"
             + "<!DOCTYPE html>\n"
             + "<html>\n"
             + "<head>\n"
             + "<meta charset=\"UTF-8\">\n"
             + "<title>Servidor Web</title>\n"
             + "</head>\n"
             + "<body>\n"
             + "<h1>Petición Resuelta:</h1>\n"
             + "</body>\n"
             + "</html>\n"
             +finalResource;
        
        
    }
    
}