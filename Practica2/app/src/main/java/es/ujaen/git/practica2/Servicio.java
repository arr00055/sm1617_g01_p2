package es.ujaen.git.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**Clase servicio a la que ira el usuario directamente si se ha autenticado correctamente, o si sale de la aplicacion y vuelve a ella
 * tras autenticarse y aun no ha expirado el tiempo de sesion.
 */
public class Servicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio);
    }
}
