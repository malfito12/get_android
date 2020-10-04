package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;

public class Registro_usuario extends AppCompatActivity {
    EditText txt_nombre, txt_pass, txt_email, txt_edad;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        txt_nombre = (EditText) findViewById(R.id.txt_nombreU);
        txt_pass = (EditText) findViewById(R.id.txt_passU);
        txt_email = (EditText) findViewById(R.id.txt_emailU);
        txt_edad = (EditText) findViewById(R.id.txt_edadU);
    }

    public void Registrar( View view) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("username", txt_nombre.getText().toString());
        rp.put("password", txt_pass.getText().toString());
        rp.put("email", txt_email.getText().toString());
        rp.put("edad", txt_edad.getText().toString());
        client.post("http://192.168.1.17:8000/service/user", rp, new JsonHttpResponseHandler() {
            public void onSuccess(int statuscode, Header[] headers, JSONObject response) {
                Intent n = new Intent(Registro_usuario.this, MainActivity.class);
                Registro_usuario.this.startActivity(n);
                //super.onSuccess(statuscode, headers, response);
                /*try {
                    String resp = response.getString("mensaje");
                    if (resp.equals("regustro exitoso")) {
                        Intent in = new Intent(Register.this, MainActivity.class);
                        startActivity(in);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //super.onSuccess(statusCode, headers, response);
            }

        });
    }
}
