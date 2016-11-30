package juliano.ufrn.hospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    //RequestQueue queue = Volley.newRequestQueue(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLigarSensores = (Button) findViewById(R.id.btnLigaSensor);
        Button btnDesligarSensores = (Button) findViewById(R.id.btnDesligaSensor);

        btnLigarSensores.setOnClickListener( new View.OnClickListener(){
             @Override
             public void onClick(View view) {
                 startService(new Intent(getApplicationContext(),Sensores.class));


             }
         });

        btnDesligarSensores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(getApplicationContext(), Sensores.class));
            }
        });


    }
}
