package com.example.serversocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.client.myapplication.client.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP;
    String SERVER_IP;
    int SERVER_PORT=8080;
    public Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etIP = findViewById(R.id.etIP);
        Button btnConnect = findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SERVER_IP = etIP.getText().toString().trim();
                Thread1 = new Thread(new Thread1());
                Thread1.start();
            }
        });
        Button btnInfo =findViewById(R.id.buttonI);
        btnInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                runInfo();
            }
        });
    }

    static private ObjectOutputStream out;
    static private ObjectInputStream in;

    class Thread1 implements Runnable {
        public void run() {

            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext()," Connected!! ",Toast.LENGTH_SHORT).show();
                        runDialog();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String testo;

    static class Thread2 extends Thread {
        String tab;
        double rag;
        Thread2(String tabella,double raggio){
            tab=tabella;
            rag=raggio;
        }
        @Override
        public void run()  {

            try {
                out.writeObject(3);
                // out.reset();
                out.writeObject(tab);
                //out.reset();
                out.writeObject(rag);
                //out.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String result="";
            try {
                result = (String) in.readObject();
                //in.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (result.equals("OK"))
                try {
                testo = (String) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            else
                try {
                    throw new ServerException(result);
                } catch (ServerException e) {
                    e.printStackTrace();
                }
        }
    }

    static public String learning_from_file(String tabella,double raggio)  {
        Thread2 t = new Thread2(tabella,raggio);
        t.start();
        return testo;
    }
   static class Thread3 extends Thread {
       String tab;
       double rag;
       Thread3(String tabella, double raggio){
           tab=tabella;
           rag=raggio;
       }
       @Override
       public void run() {
           try {
               out.writeObject(0);
               //out.reset();
               out.writeObject(tab);
               //out.reset();
               String result = (String) in.readObject();
               if (!result.equals("OK"))
                   throw new ServerException(result);
               out.writeObject(1);
               // out.reset();
               out.writeObject(rag);
               //out.reset();
               result = (String) in.readObject();
               if (result.equals("OK"))
                       testo= "Number of Clusters:" + in.readObject() +" \n" + in.readObject();
               //in.close();
               out.writeObject(2);
               //out.reset();
               result = (String) in.readObject();
               // in.close();
                if (!result.equals("OK"))
                    throw new ServerException(result);
           }catch (IOException e){
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           } catch (ServerException e) {
               e.printStackTrace();
           }
       }
   }

    static String storeTableFromDb(String tabella,Double raggio) {
        Thread3 t = new Thread3(tabella,raggio);
        t.start();
        return testo;
    }

   public void runInfo(){
        Intent info = new Intent(this,Info.class);
        startActivity(info);
    }

    public void runDialog(){
        Intent dialog = new Intent(this, Dialog.class);
        startActivity(dialog);
    }





}