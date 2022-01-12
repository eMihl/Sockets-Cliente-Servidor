package client;

import java.io.IOException;

public class Main{
	
	/** Main del cliente 
	 **/

        public static void main(String[] args) throws IOException {
        	
        	//Creamos objeto de Cliente
            Client cli = new Client();
            System.out.println("Iniciando cliente...");
            
            //Iniciamos la conexión
            cli.iniciarCliente();
        }
}