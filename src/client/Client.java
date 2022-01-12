package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final String HOST = "localhost";
    private final int PUERTO = 9876;
    private Socket socket;

    public Client() throws IOException {
        socket = new Socket(HOST, PUERTO);
    }

    public void iniciarCliente() throws IOException {
    	
    	//Iniciamos la entrada de datos
        DataInputStream entradaServidor = new DataInputStream(socket.getInputStream());
        System.out.println(entradaServidor.readUTF()); //Mostramos el mensaje por pantalla
        
        //Se inicia la salida de datos
        DataOutputStream salidaServidor = new DataOutputStream(socket.getOutputStream());
        
        Scanner sc = new Scanner(System.in);
        
        try {
        	
        	// Se envía el nombre de usuario y el número de tareas
        
        	String nombre = sc.nextLine();        
        
	        salidaServidor.writeUTF(nombre);
	          
	        System.out.println(entradaServidor.readUTF());
	        
	        int numTareas = sc.nextInt();
	        salidaServidor.writeUTF(""+numTareas);
	        sc.nextLine();
	        
	      // Se envía la información de cada tarea
	        
	        for (int a=1; a<=numTareas; a++) {
	        	
	     	   
	        	System.out.println(entradaServidor.readUTF());
	        	System.out.println(entradaServidor.readUTF());
	        	
	        	String descripcion = sc.nextLine();
	        	salidaServidor.writeUTF(descripcion);
	        	
	        	System.out.println(entradaServidor.readUTF());
	        	
	        	String estado = sc.nextLine();
	        	salidaServidor.writeUTF(estado);
	        	
	        }
	        
	        // Se recibe del servidor las tareas solicitadas
	        
	        System.out.println(entradaServidor.readUTF());
	        
	        for (Integer a=0; a<numTareas; a++) {
         	   
	        	System.out.println(entradaServidor.readUTF());
            } 
            
        } catch (EOFException ex) {
        	System.out.println("Error de cierta gravedad.");
        	
        }
        
        // Se cierran los flujos, el zócalo y el escáner

        salidaServidor.close();
        entradaServidor.close();
        socket.close();
        sc.close();
    }
}
