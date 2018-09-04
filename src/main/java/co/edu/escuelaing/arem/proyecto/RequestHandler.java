/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arem.proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author 2110432
 */
class RequestHandler {
    Socket clientSocket;
    public RequestHandler(Socket socket) {
        clientSocket= socket;
    }
    
    public String getRequest() throws IOException{
        
        
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));

        String inputLine = "";
        String finalResource = "";
        while ((inputLine = in.readLine()) != null) {

            System.out.println("Received: " + inputLine);

            if (!in.ready()) {
                break;
            }

            if (inputLine.toLowerCase().contains("GET".toLowerCase())) {

                int i = 5;
                String resource = "";
                while ((inputLine.charAt(i)) != ' ') {
                    resource = resource + inputLine.charAt(i);
                    i++;
                }

                System.out.println("ME PIDEN: " + resource);
                finalResource=resource;
            }
        }
        
        return finalResource;
    }   
}
