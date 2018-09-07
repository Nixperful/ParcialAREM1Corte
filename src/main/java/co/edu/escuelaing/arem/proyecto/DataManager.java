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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Nicolás Osorio Arias
 */
public class DataManager {
    
    
    /**
    *
     * @param direction name of the resource that server needs to send.
     * @param client Client Socket to know where to send the resource
    */     
        public void sendJsonData(String request, Socket clientSocket) {
            
            JSONObject jsonResource = new JSONObject();
            System.out.println(request);
            LinkedList temp = new LinkedList();
            
            
            try{
                
                

                String[] dataNumbers= request.split("&");

                for(int i=0; i<dataNumbers.length;i++){
                    temp.add(Float.parseFloat(dataNumbers[i]));
                }  
                
            }catch (Exception ex){
                System.out.println("Error al recoger los números");
            }
            
            
            
    
            try {
                
                
                JSONArray list = new JSONArray();
                System.out.println(request);
                for (int i=0; i<temp.size();i++){
                    list.add(temp.get(i));
                    System.out.println(temp.get(i));
                }
               
                jsonResource.put("numbers", list);
                
                DataProcessor dP = new DataProcessor(temp);
                
                jsonResource.put("maximum", dP.getMax());
                jsonResource.put("minimum", dP.getMin());
                jsonResource.put("summation", dP.getSum());
                jsonResource.put("multiplication", dP.getMult());


            } catch (Exception ex) {
                
            }
            
            String file = "HTTP/1.1 200 OK\r\n"
             + "Content-Type: application/json\r\n"
             + "\r\n"+
             jsonResource.toString();
            
            PrintWriter out;
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(file);

            } catch (IOException ex) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
        
        
}