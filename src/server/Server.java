package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final int PUERTO = 9876;
    private ServerSocket serverSocket;
    private Socket socket;
    
    public Server() throws IOException {
        serverSocket = new ServerSocket(PUERTO); //Definimos la conexión
        socket = new Socket(); //Iniciamos el cliente
    }
    
    // Función para iniciar la conexión
    
    public void iniciarServer() throws IOException {
    	
    	ArrayList<Tarea> tareas = new ArrayList<Tarea>();
        
    	// Vamos a aceptar los datos que llegarán del cliente
    	
    	while (true) {
    		
            System.out.println("Esperando la conexion del cliente");
            socket = serverSocket.accept(); // Guardamos la petición que llegue al servidor en socket
            
            // El servidor se queda a la espera de recibir peticiones
            // Al recibir la petición, iniciamos la conexión
            
            DataOutputStream mensajeACliente = new DataOutputStream(socket.getOutputStream());
            
            // Enviamos mensaje al cliente solicitando su nombre
            
            mensajeACliente.writeUTF("Por favor, indica tu nombre: ");
            
            try {
            
	            DataInputStream entrada = new DataInputStream(socket.getInputStream());
	            
	            // Recibimos el nombre a través del cliente
	            
	            String nombre = entrada.readUTF();
	            System.out.println("Bievenido, " + nombre);
	            
	            // Enviamos mensaje al cliente solicitando el número de tareas
	            
	            mensajeACliente.writeUTF("Indica el número de tareas a realizar: ");
	            
	            Integer numTareas = Integer.valueOf(entrada.readUTF());
	          
	            System.out.println("Se realizarán " + numTareas + " tareas.");
	            
	            // Bucle donde se solicita al cliente la descripción y el estado de cada tarea
	           
                for (Integer a=1; a<=numTareas; a++) {
            	   
            	   mensajeACliente.writeUTF("Tarea " + a + ": ");
            	   mensajeACliente.writeUTF("Describa la tarea: ");
            	   String descripcion = entrada.readUTF();
            	   System.out.println(descripcion);
            	   mensajeACliente.writeUTF("Estado de la tarea: ");
            	   String estado = entrada.readUTF();
            	   System.out.println("Estado: " + estado);
            	   
            	// Se crea una nueva tarea con la información recibida
            	   tareas.add(new Tarea(descripcion, estado));
                } 
               
                mensajeACliente.writeUTF("A continuación recibirá todas las tareas: ");
                
               // Se envían las tareas al cliente
               
                for (Integer a=0; a<numTareas; a++) {
            	   
            	   mensajeACliente.writeUTF("Tarea " + (a+1) + ": " + tareas.get(a).toString());
                } 
               
            } catch (EOFException ex){
            	
                System.out.println("Fin de la comunicación");
            }
            
            System.out.println("Fin de la conexión");
            socket.close();
        }
    }

    public void finalizarServer() throws IOException {
        serverSocket.close();
    }

}
