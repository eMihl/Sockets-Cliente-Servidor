package server;

import java.io.IOException;

public class Main {
	
	/** Main del servidor 
	 **/
	
    public static void main(String[] args) throws IOException {
        // Objeto server
    	Server serv = new Server();
        System.out.println("Iniciando servidor...");
        
        // Se inicia el servidor
        serv.iniciarServer();
        
        // Finaliza el servidor
        serv.finalizarServer();
    }
}
