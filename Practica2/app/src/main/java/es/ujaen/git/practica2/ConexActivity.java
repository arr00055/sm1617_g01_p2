package es.ujaen.git.practica2;
/**Esta es la clase de la actividad a la que pasamos una vez utilizamos el intent desde el fragmento tras pular el botón de enviar.
 * Cada uno de los parámetros siguientes son extraidos del Intent uno a uno.
 * @param usuario     el nick o nombre que introduce el usuario en la interfaz.
 * @param password    la contraseña que introduce el usuario en la interfaz.
 * @param direccionIp la dirección Ip que introduce el usuario en la interfaz.
 * @param puerto      el puerto que introduce el usuario en la interfaz.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

            /**
             * Creo un objeto autentica de la clase Autenticacion al cual le paso los datos que he recibido del intent, luego se crea
             * un objeto conecta de la clase Conexion, para finalmente pasarle al objeto conecta el objeto autentica que contiene los
             * datos introducidos por el usuario y con execute(); envio datos directamente a doInBackground().
             */
            Autenticacion autentica = new Autenticacion(user,pass,ip,port);
            Conexion conecta = new Conexion();
            conecta.execute(autentica);
        }//Fin del if.
    }//Fin del onCreate.

    /**
     * Creo una clase Conexion que con los datos que el usuario ha introducido en el fragmento y enviado durante el login, y a través de una
     * tarea asíncrona y con el uso de Sockets TCP me permitirá conectar con un servidor y recibir una respuesta de este.
     * AsynTask<Autenticacion...>-> Se le pasa este primer valor.
     */
    public class Conexion extends AsyncTask<Autenticacion, Integer, String> {
        ProgressDialog pdia = null;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pdia = new ProgressDialog(ConexActivity.this);
            pdia.setIndeterminate(true);
            pdia.setMessage("Autenticando, espere por favor.");
            pdia.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pdia.setCancelable(false);
            pdia.show();
        }//Fin onPreExecute.

        @Override
        protected String doInBackground(Autenticacion... arg0){
            Socket cliente   = null;//Creo e inicializo una variable cliente de tipo Socket.
            String respuesta = null;//Creo e inicializo una variable respuesta que sera la que obtenga del servidor de tipo Socket.
            String id        = null;
            try {

                String User = arg0[0].getUser();  //Saco de mi array arg0 el valor que se corresponde con el usuario.
                String Pass = arg0[0].getPass();  //Saco de mi array arg0 el valor que se corresponde con la password.
                String IP   = arg0[0].getIP();    //Saco de mi array arg0 el valor que se corresponde con la direccion IP.
                int    Port = arg0[0].getPort();  //Saco de mi array arg0 el valor que se corresponde con el Puerto.
                InetSocketAddress direccion = new InetSocketAddress(IP,Port); //Creo el objeto direccion de tipo InetSocketAddress que contiene la direccon IP y el Puerto.

                //Se crea el socket TCP y me conecto al servidor con la direccion TCP y el puerto.
                cliente = new Socket();
                cliente.connect(direccion);

                //Se leen los datos del buffer de entrada
                BufferedReader bis = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                OutputStream os = cliente.getOutputStream();

                //Recibimos el mensaje de saludo del servidor.
                respuesta = bis.readLine();
                Log.d("Saludo", respuesta);

                //Le metemos "USER USER" para ver la respuesta correspondiente del servidor.
                os.write(new String("USER "+User+"\r\n").getBytes());
                os.flush();
                respuesta = bis.readLine();
                Log.d("Usuario", respuesta);

                //Le metemos "PASS 12345" para ver la respuesta correspondiente del servidor-TCP.
                os.write(new String("PASS "+Pass+"\r\n").getBytes());
                os.flush();
                id = bis.readLine();
                Log.d("Pass", id);
                if (id.startsWith("ERROR")) {
                    os.write(new String("QUIT\r\n").getBytes());
                    os.flush();
                    respuesta = bis.readLine();
                    Log.d("Quit", respuesta);
                    bis.close();
                    os.close();
                    cliente.close();
                    respuesta = "ERROR";
                    return respuesta;
                }//Fin del if result.startsWith.

                //SESION-ID=SIDUSERMTIzNDU=&EXPIRES=2016-12-01-12-38-22
                String [] sesionid = null;//Creo e inicializo una variable sesionid que sera donde se guarde la respuesta con la sesion.
                String [] expira   = null;//Creo e inicializo una variable expira que sera donde se guarde el tiempo de sesion.
                String [] linea    = null;//Creo e inicializo la variable linea para realizar la separacion con split que me envia el
                                          //servidor y he guardado en la variable id.

                linea = id.split("&");         //Separo por &
                sesionid = linea[0].split("=");//Separo por = y guardo la primera linea de la separacion que contiene la sesionid.
                Log.d("Sesionid", sesionid[1]);
                expira  = linea[1].split("="); //Separo por = y guardo la segunda linea de la separacion que contiene la expira.
                Log.d("Expira", expira[1]);

                //Le metemos "QUIT".
                os.write(new String("QUIT\r\n").getBytes());
                os.flush();
                respuesta = bis.readLine();
                Log.d("Quit", respuesta);
                bis.close();
                os.close();
                cliente.close();

                //Preferencias donde guardo los datos, deberé trocear la sesionid para sacar el Id y la fecha como un entero
                //para la comprobación de sesión.
                SharedPreferences prefs = getSharedPreferences("DatosSesion", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Sesion",sesionid[1]);
                editor.putString("Tiempoexpira",expira[1]);
                editor.commit();

            } catch (IOException err){ //Fin del try y captura de la excepción.
             err.printStackTrace();
             respuesta = "IOException: " + err.toString(); //Saco como respuesta el error que se ha producido.
            }//Fin del catch.
            return respuesta; //Devuelvo lo almacenado en la variable respuesta.
        }//Fin doInBackground.

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String respuesta) {
            super.onPostExecute(respuesta);
            pdia.dismiss();
            if (respuesta != null) {
                if (respuesta.startsWith("OK")) {
                    Intent a = new Intent(ConexActivity.this, Servicio.class);
                    startActivity(a);//Realizar la transición intent con identificador a.
                }//Fin del if respuesta.startsWith.
                if(respuesta.startsWith("ERROR")){
                    Intent b = new Intent(ConexActivity.this, MainActivity.class);
                    startActivity(b);//Realizar la transición intent con identificador a.
                }//Fin del if respuesta.startsWith.
            }//Fin del if que comprueba que el result no es nulo.
        }//Fin onPostExecute.
    }//Fin AsynTask.
}//Fin del ConexActivity.
