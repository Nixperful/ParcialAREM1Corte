/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arem.proyecto;

import static co.edu.escuelaing.arem.proyecto.MyWebServer.getPort;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 2110432
 */
class SocketConnection {

    public SocketConnection() {
    }
    
    public ServerSocket getServerConnection(){
        
        ServerSocket sSocket = null;
        try {
            sSocket = new ServerSocket(getPort());
        } catch (IOException e) {

            System.err.println("Could not listen on port");
            System.exit(1);
        }
        return sSocket;
    }
    
    public Socket getClientConnection(ServerSocket serverSocket) {

        Socket clientSocket = null;
        
        try {

            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();

        } catch (IOException e) {
            System.err.println("Accept failed.");

            System.exit(1);
        }
        
        
        return clientSocket;
        
    }
    
    
    
    
                

    
    
}
