
package co.edu.escuelaing.arem.proyecto;

/**
 *
 * @author Nicolás
 */
import java.net.*;

import java.io.*;

public class MyWebServer {

    public static void main(String[] args) throws IOException {
        
                
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {

            System.err.println("Could not listen on port");
            System.exit(1);
        }
        
        boolean isCompleted=false;
        try{           
        
            while(!isCompleted){ 

                Socket clientSocket = null;
                try {

                    System.out.println("Listo para recibir ...");
                    clientSocket = serverSocket.accept();

                } catch (IOException e) {
                    System.err.println("Accept failed.");

                    System.exit(1);
                }



                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));



                    String inputLine, outputLine = "";
                    String finalResource="";
                    while ((inputLine = in.readLine()) != null) {

                        System.out.println("Received: " + inputLine);


                        if (!in.ready()) {
                            break;
                        }

                        if(inputLine.toLowerCase().contains("GET".toLowerCase())){

                            int i=5;
                            String resource = "";
                            while ((inputLine.charAt(i))!=' '){
                                resource=resource+inputLine.charAt(i);
                                i++;
                            }

                            System.out.println("ME PIDEN: "+resource);

                            DataRecollector dR= new DataRecollector();
                            finalResource=dR.readResource(resource);

                        }

                    }

                    outputLine = "HTTP/1.1 200 OK\r\n"
                     + "Content-Type: text/html\r\n"
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

                    out.println(outputLine);



                in.close(); 
                out.close();   
                clientSocket.close();
            }
            
             
        }finally{
            serverSocket.close();
        }
        
    }
    
    static int getPort() {
            if (System.getenv("PORT") != null) {
                return new Integer(System.getenv("PORT"));
            }
            return 35000; //returns default port if heroku-port isn't set (i.e. on localhost)
    } 

}
