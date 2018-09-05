package co.edu.escuelaing.arem.proyecto;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Nicolás
 */
public class DataManager {
    
    public String readResource(String direction){
        
        String file, contentType = "";
        String resource="";
       
        contentType = "text/html";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("resources/" + direction));
            String temp = "";
            String bfRead = "";
            while ((bfRead = bf.readLine()) != null) {
                temp = temp + bfRead;
            }
            resource = temp;

        } catch (IOException e) {
            resource = "";
            System.err.println("No se ha encontrado el archivo");

        }
        
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
             +resource;
        
        return file;
        
            /*
            try {
                finalData = Files.readAllBytes(new File("./resources" + direction).toPath());
                imageLength = "" + finalData.length;
            } catch (IOException ex) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
 
            resource= ("<!DOCTYPE html>"
                    +"<html>"
                    +"<head>"
                    +"<title>Page Title</title>"
                    +"</head>"
                    +"<body>"  
                    
                    +"</body>"  
                    +"<html>");
             */
    }

  
    public byte[] readImage(String direction){
            byte[] finalData = null;
            String contentType="image/png";
            String bytesLength="";
            
            try { 
                File graphicResource= new File("resources/"+direction);
                FileInputStream inputImage = new FileInputStream(graphicResource);
                finalData =new byte[(int) graphicResource.length()];
                inputImage.read(finalData);
                //inFile.close();
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch(IOException ex){
                System.err.println("Error en la lectura de el archivo");
            }
            
            return finalData;
  
    }

        
    public void sendResource(String direction, Socket client){
        
     
        if (direction.toLowerCase().contains(".html".toLowerCase())) {

            String serverAns = readResource(direction);
            PrintWriter out;
            try {
                out = new PrintWriter(client.getOutputStream(), true);
                out.println(serverAns);
                //out.close();
            } catch (IOException ex) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (direction.toLowerCase().contains(".png".toLowerCase()) || direction.toLowerCase().contains(".jpg".toLowerCase())) {
            
        }
            byte[] serverAns = readImage(direction);
            DataOutputStream binaryOut;
        try {
            binaryOut = new DataOutputStream(client.getOutputStream());
            binaryOut.writeBytes("HTTP/1.1 200 OK \r\n");
            binaryOut.writeBytes("Content-Type: image/png\r\n");
            binaryOut.writeBytes("Content-Length: " + serverAns.length);
            binaryOut.writeBytes("\r\n\r\n");
            binaryOut.write(serverAns);
            binaryOut.close();
        } catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }        
     
    }
}