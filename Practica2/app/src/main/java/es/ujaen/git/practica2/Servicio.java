package es.ujaen.git.practica2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**Clase servicio a la que ira el usuario directamente si se ha autenticado correctamente, o si sale de la aplicacion y vuelve a ella
 * tras autenticarse y aun no ha expirado el tiempo de sesion.
 */
public class Servicio extends AppCompatActivity {
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio);
        bt1 = (Button) findViewById(R.id.botonstock);

        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View View){
                Toast.makeText(getApplicationContext(), "Accediendo al control de stock.", Toast.LENGTH_SHORT).show();
                Intent c = new Intent(Servicio.this, Stock.class);
                startActivity(c);//Realizar la transición intent con identificador c.
            }
        });
    }
}
