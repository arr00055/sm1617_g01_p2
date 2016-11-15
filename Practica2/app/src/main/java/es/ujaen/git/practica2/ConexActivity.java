package es.ujaen.git.practica2;
/**Esta es la clase de la actividad a la que pasamos una vez utilizamos el intent desde el fragmento tras pular el botón de enviar.
 * Cada uno de los parámetros siguientes son extraidos del Intent uno a uno.
 * @param usuario     el nick o nombre que introduce el usuario en la interfaz.
 * @param password    la contraseña que introduce el usuario en la interfaz.
 * @param direccionIp la dirección Ip que introduce el usuario en la interfaz.
 * @param puerto      el puerto que introduce el usuario en la interfaz.
 */

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity; //Importamos este paquete para la compatibilidad con API inferiores.
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.net.InetSocketAddress;
import java.net.Socket;

//La clase ConexActivity hereda de AppCompatActivity.
public class ConexActivity extends AppCompatActivity {
//Ciclo de vida onCreate cuando se crea la Actividad. Aquí es donde se inicializa la actividad.
//En Bundle savedInstanceState es donde se reciben los datos almacenados tras un recreado de la actividad.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       //Se llama a la superclase primero.
        setContentView(R.layout.activity_conex);  //Pinto el layout correspondiente a la actividad ConexActivity.

        Bundle bundle = getIntent().getExtras();
        //Tomamos el Intent que va dirigido a esta actividad y sacamos los Extras donde están los datos que nos envía el fragmento.
        if (bundle != null){ //Si bundle es diferente de null se procede a extraer los String y los Int y se guardan en variables.
            String user = bundle.getString("usuario");     //Extraigo del bundle la cadena de String con id usuario a través del getString.
            String pass = bundle.getString("password");    //Extraigo del bundle la cadena de String con id password a través del getString.
            String ip   = bundle.getString("direccionIp"); //Extraigo del bundle la cadena de String con id direccionIp a través del getString.
            int port    = bundle.getInt("puerto");         //Extraigo del bundle la cadena del Int con id puerto a través del getInt.
            //Toast para ver que llegan los datos y se guardan en sus variables correspondientes.
            //Toast.makeText(this, "Nombre: "+user, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Contraseña: "+pass, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Dirección IP: "+ip, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Puerto: "+port, Toast.LENGTH_SHORT).show();
        }//Fin del if.
    }//Fin del onCreate.

    //Creo una clase conectar que con los datos que el usuario ha introducido en el fragmento y enviado durante el login, y a través de una
    //tarea asíncrona y con el uso de Sockets TCP me permitirá conectar con un servidor y recibir una respuesta de este.
    //TODO seguir con ejemplo 9.
    public class conectar extends AsyncTask<InetSocketAddress, Void, String> {
        @Override
        protected String doInBackground(InetSocketAddress... arg0){
            Socket cliente = null;//Creo e inicializo una variable cliente de tipo Socket.
            String respuesta=null;//Creo e inicializo una variable respuesta que será la que obtenga del servidor de tipo Socket.

            return respuesta;
        }
    }


}//Fin del ConexActivity.
