package juliano.ufrn.hospital;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;
import java.util.Vector;

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

        new Thread(new Runnable(){
            @Override
            public void run() {
                boolean control = true;

                Vector<Integer> v = new Vector<Integer>();
                v.add(0);
                v.add(0);
                v.add(0);
                int cont = 0;

                while(control){

                    int batimentos_cardiacos = new Random().nextInt((120 - 80) + 1) + 80;
                    int nivel_glicose = new Random().nextInt((120 - 80) + 1) + 80;
                    int pressao = new Random().nextInt((120 - 80) + 1) + 80;

                    v.add(0,batimentos_cardiacos);
                    v.add(1,nivel_glicose);
                    v.add(2,pressao);

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
                        Log.i("PACIENTE_MORRENDO",bat_c);
                        Log.i("PACIENTE_MORRENDO",n_glic);
                        Log.i("PACIENTE_MORRENDO",press);
                        Log.i("PACIENTE_MORRENDO",s);
                    }
                }
            }
        }).start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Sensores Desligados", Toast.LENGTH_LONG).show();
    }
}
