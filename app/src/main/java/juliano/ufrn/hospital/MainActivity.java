package juliano.ufrn.hospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

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
                 //startService(new Intent(getApplicationContext(),Sensores.class));

                 //Toast.makeText(this,"Sensores Ligados", Toast.LENGTH_LONG).show();

                 JSONObject metadatasElements = new JSONObject();
                 final JSONObject objMain = new JSONObject();
                 try {
                     metadatasElements.put("name","location");
                     metadatasElements.put("type","string");
                     metadatasElements.put("value","WGS84");

                     JSONArray aMeta = new JSONArray();
                     aMeta.put(metadatasElements);

                     //JSONObject mainMeta = new JSONObject();
                     //mainMeta.put("metadatas",aMeta);

                     JSONArray aAttr = new JSONArray();

                     JSONObject nomeElements = new JSONObject();
                     nomeElements.put("name","nome");
                     nomeElements.put("type","text");
                     nomeElements.put("value","paciente720");

                     JSONObject positionElements = new JSONObject();
                     positionElements.put("name","position");
                     positionElements.put("type","geo:point");
                     positionElements.put("value","-5.79448,-35.211");
                     positionElements.put("metadatas",aMeta);

                     JSONObject pressaoElements = new JSONObject();
                     pressaoElements.put("name","pressao");
                     pressaoElements.put("type","integer");
                     pressaoElements.put("value","paciente720");

                     JSONObject batimento_cardiacoElements = new JSONObject();
                     batimento_cardiacoElements.put("name","batimento_cardiaco");
                     batimento_cardiacoElements.put("type","integer");
                     batimento_cardiacoElements.put("value","30");

                     JSONObject nivel_glicoseElements = new JSONObject();
                     nivel_glicoseElements.put("name","nivel_glicose");
                     nivel_glicoseElements.put("type","integer");
                     nivel_glicoseElements.put("value","30");


                     aAttr.put(nomeElements);
                     aAttr.put(positionElements);
                     aAttr.put(pressaoElements);
                     aAttr.put(batimento_cardiacoElements);
                     aAttr.put(nivel_glicoseElements);

                     JSONObject cElements = new JSONObject();
                     cElements.put("id","paciente720");
                     cElements.put("type","Paciente");
                     cElements.put("isPattern","false");
                     cElements.put("attributes",aAttr);


                     objMain.put("contextElements",cElements);
                     objMain.put("updateAction","UPDATE");




                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

                 String url = "http:177.20.147.208/v1/updateContext";



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
