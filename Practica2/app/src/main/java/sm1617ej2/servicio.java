package sm1617ej2;

import java.io.*;
import java.net.*;

/**Created on 13/11/2016.
 * @author Alejandro Romo Rivero.
 * La clase servicio será la clase abstracta donde se implementan los métodos básicos para conseguir dar el servicio de control de
 * stock. Esta clase sería la que emplearía el servidor y cuenta con métodos para la Autenticación del usuario, para recibir su
 * petición y para cerrar el la conexión cuando expire el tiempo de conexión o cuando el usuario decida cerrar la conexión.
 */
public abstract class servicio {
    static ServerSocket mServidor = null; //Se inicializa la variable del mServidor del tipo ServerSocket.
    public static int mConexiones = 0;    //Se inicializa la variable Conexiones de tipo entero, para el número de conexiones.
    final int PUERTO=6000;                //Puerto en el que estará escuchando nuestro servidor TCP por conexiones.
    Socket mSocket;                       //Variable mSocket tipo Socket.
    String inputData = null;              //Cadena para los datos de entrada.
    String outputData = "";               //Cadena para los datos de salida.
    String parametro = "";                //Cadena para los parámetros.
    String tempUser = "";                 //Cadena para la temporización del usuario.

    abstract void main(String[] args);                           //Método principal.

    abstract public Boolean Login(String user,String pass);      //Método para la autenticación del usuario. Me devuelve un Boolean
                                                                 //para comprobar si el usuario ha podido autentincarse correctamente.
    abstract public String close();                              //Método para cerrar la conexión con el cliente, me devolverá una
                                                                 //cadena para avisar al cliente de que se ha desconectado.
    abstract public String peticion();                           //Método para recibir la petición del usuario, se sacará la información
                                                                 //del mensaje del protocolo.
}//Fin clase abstracta servicio.
