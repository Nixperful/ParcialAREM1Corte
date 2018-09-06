package co.edu.escuelaing.arem.proyecto;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolás Osorio Arias
 */
public class DataManager {
    
    /**
    *
    * @param direction Name of the file, without path.
    * @return file, html file
    */    
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
             +resource;
        
        return file;
        
        
    }

    /**
    *
    * @param direction name of the image that you need to read.
    * @return finalData, image in byte array.
    */ 
    public byte[] readImage(String direction){
            byte[] finalData = new byte[]{};
                       
            try { 
                File graphicResource= new File(direction);
                System.out.println(graphicResource.getPath());
                FileInputStream inputImage = new FileInputStream((graphicResource.getPath()));
                finalData =new byte[(int) graphicResource.length()];
                inputImage.read(finalData);
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch(IOException ex){
                System.err.println("Error en la lectura de el archivo");
            }
            
            return finalData;
  
    }

    /**
    *
     * @param direction name of the resource that server needs to send.
     * @param client Client Socket to know where to send the resource
    */     
    public void sendResource(String direction, Socket client){
        
     
        if (direction.toLowerCase().contains(".html".toLowerCase())) {

            String serverAns = readResource(direction);
            PrintWriter out;
            try {
                out = new PrintWriter(client.getOutputStream(), true);
                out.println(serverAns);

            } catch (IOException ex) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (direction.toLowerCase().contains(".png".toLowerCase()) || direction.toLowerCase().contains(".jpg".toLowerCase())) {
            
        
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
        else{
            String serverAns = readResource("404error.html");
            PrintWriter out;
            try {
                out = new PrintWriter(client.getOutputStream(), true);
                out.println(serverAns);

            } catch (IOException ex) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
     
    }
}