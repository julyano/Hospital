package juliano.ufrn.hospital;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.lang.Object;

import static java.lang.Math.round;

/**
 * Created by juliano on 29/11/16.
 */
public class Sensores extends Service {



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Sensores Ligados", Toast.LENGTH_LONG).show();

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
        /*JSONTokener tok = new JSONTokener(objMain);
        JSONObject jsObj = new JSONObject();
        try {
            jsObj = (JSONObject) tok.nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

       /* HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
       */

        /*@Override
        public void onResponse(JSONObject response) {
            Log.i("teste", response.toString());
            //pDialog.hide();
        }*/

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, objMain, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });


        /*JsonArrayRequest jsonObjReq = null;
        jsonObjReq = new JsonArrayRequest(Request.Method.POST, url, new JSONObject(headers),
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("teste", response.toString());
                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("teste", "Error: " + error.getMessage());
                //pDialog.hide();
            }
        });*/

        /*{

            *//**
             * Passing some request headers
             * *//*
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

        };*/

        //AppController.getInstance().addToRequestQueue(request);
        queue.add(jsonObjRequest);


        /*

        new Thread(new Runnable(){
            @Override
            public void run() {
                boolean control = true;
                int cont = 0;

                int a=0,b=0,c=0;



                while(control){

                    int batimentos_cardiacos = new Random().nextInt((120 - 80) + 1) + 80;
                    int nivel_glicose = new Random().nextInt((120 - 80) + 1) + 80;
                    int pressao = new Random().nextInt((120 - 80) + 1) + 80;

                    a = batimentos_cardiacos;
                    b = nivel_glicose;
                    c = pressao;

                    String bat_c = "batimentos_cardiacos = "+batimentos_cardiacos;
                    String n_glic = "nivel_glicose = "+ nivel_glicose;
                    String press = "pressao = "+ pressao;

                    String s = null;
                    if(batimentos_cardiacos < 100 || nivel_glicose < 100 || pressao < 100) {
                        ++cont;
                        s = ""+cont;
                        Log.i("PACIENTE_ESTAVEL",bat_c);
                        Log.i("PACIENTE_ESTAVEL",n_glic);
                        Log.i("PACIENTE_ESTAVEL",press);
                    }

                    if(cont == 50){
                        control = false;
                        cont = 0;
                        Log.i("PACIENTE_MORRENDO",bat_c);
                        Log.i("PACIENTE_MORRENDO",n_glic);
                        Log.i("PACIENTE_MORRENDO",press);
                        Log.i("PACIENTE_MORRENDO",s);
                    }
                }





                String object =
                        "{  \"contextElements\":\n" +
                                "        [\n" +
                                "            {\n" +
                                "                \"id\": \"paciente720\",\n" +
                                "                \"type\": \"Paciente\",\n" +
                                "                \"isPattern\":\"false\",\n" +
                                "                \"attributes\":\n" +
                                "                    [\n" +
                                "                        {\n" +
                                "                            \"name\":\"nome\",\n" +
                                "                            \"type\":\"text\",\n" +
                                "                            \"value\":\"paciente720\"\n" +
                                "                        },\n" +
                                "                       {\n" +
                                "                            \"name\": \"position\",\n" +
                                "                            \"type\": \"geo:point\",\n" +
                                "                            \"value\": \"-5.79448,-35.211\",\n" +
                                "                                \"metadatas\": [\n" +
                                "                                        { \"name\":\"location\", \"type\": \"string\", \"value\": \"WGS84\"}\n" +
                                "                                 ]\n" +
                                "                        },\n" +
                                "                        {\n" +
                                "                            \"name\":\"pressao\",\n" +
                                "                            \"type\":\"integer\",\n" +
                                "                            \"value\":"+a+"\"\n" +
                                "                        },\n" +
                                "                        {\n" +
                                "                            \"name\":\"batimento_cardiaco\",\n" +
                                "                            \"type\":\"integer\",\n" +
                                "                            \"value\":"+b+"\"\n" +
                                "                        },\n" +
                                "                        {\n" +
                                "                            \"name\":\"nivel_glicose\",\n" +
                                "                            \"type\":\"integer\",\n" +
                                "                            \"value\":"+c+"\"\n" +
                                "                        }\n" +
                                "\n" +
                                "\n" +
                                "                    ]\n" +
                                "            }\n" +
                                "        ],     \"updateAction\":\"UPDATE\" }\n";






                URL url = null;
                try {
                    url = new URL(null);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    String body = "";
                    OutputStream output = new BufferedOutputStream(conn.getOutputStream());
                    output.write(body.getBytes());
                    output.flush();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    conn.disconnect();
                }
            }
        }).start();
        */
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Sensores Desligados", Toast.LENGTH_LONG).show();
    }
}
