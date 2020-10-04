package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText email, pass, txt_usu,txt_contra;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.txt_nombre);
        pass = findViewById(R.id.txt_pass);
        btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);

        txt_usu = (EditText)findViewById(R.id.txt_nombre);
        txt_contra = (EditText)findViewById(R.id.txt_pass);
        btn1 = (Button)findViewById(R.id.btn_1);
        btn2 = (Button)findViewById(R.id.btn_2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(MainActivity.this, Registro_usuario.class);
                startActivity(registrar);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_1){
            cargar();
        }

    }
    

    public void cargar(){
        AsyncHttpClient client = new AsyncHttpClient();
        final RequestParams req=new RequestParams();
        req.put("email",email.getText().toString());
        req.put("password",pass.getText().toString());
        
        client.post("http://192.168.1.18:8000/user/login", req, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){

               super.onSuccess(statusCode, headers, response);
               try {
                   String resp=response.getString("mensage");
                   if (resp.equals("autenticacion exitosa")){
                       Intent in = new Intent(MainActivity.this, Usuario.class);
                       utilidades.token=response.getString("token");
                       startActivity(in);
                   }else if (resp.equals("el password es incorrecto")){
                       Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_LONG).show();

                   }else {
                       Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_LONG).show();
                   }
               }catch (JSONException e){
                   e.printStackTrace();
               }
           }
           public void onSuccess(int statusCode, Header[] headers, JSONArray response){
               super.onSuccess(statusCode,headers,response);
           }
        });
    }

}
