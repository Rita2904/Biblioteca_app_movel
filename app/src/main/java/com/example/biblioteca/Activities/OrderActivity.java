package com.example.biblioteca.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.biblioteca.Model.Singleton;
import com.example.biblioteca.R;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    TextView tv_title;
    EditText tv_name, tv_email;
    ImageButton btn_order, btn_cancel;
    private String id, name, email, title, titulo;

    private final static String URL = "http://10.0.2.2/biblioteca/frontend/web/orders/order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //passar valores
        titulo = getIntent().getExtras().getString("titulo");
        String autor = getIntent().getExtras().getString("autor");

        //referenciar variáveis
        tv_title = findViewById(R.id.tv_order_title);
        tv_name = findViewById(R.id.tv_order_name);
        tv_email = findViewById(R.id.tv_order_email);

        btn_cancel = findViewById(R.id.btn_order_back);
        btn_order = findViewById(R.id.btn_order);

        //atribuição de valores
        tv_title.setText(titulo);

        //evento para cancelar e voltar atrás
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //evento para requisitar livro
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                name = tv_name.getText().toString();
                email = tv_email.getText().toString();
                title = tv_title.getText().toString();
                if(!name.isEmpty() || !email.isEmpty()) {
                    requestBook();
                } else {
                    tv_name.setError("Insira o seu nome");
                    tv_email.setError("Insira o seu email");
                }
            }
        });
    }

    //método para requisitar livro
    private void requestBook() {
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.length() != 0) {
                    Toast.makeText(OrderActivity.this, "Livro requisitado com sucesso!" +
                    " Receberá um email com os detalhes", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(OrderActivity.this, "Erro " + response, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OrderActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nome", name);
                params.put("descricao", titulo);
                params.put("email", email);
                return params;
            }
        };
        //fazer o pedido através da classe Singleton
        Singleton.getInstance(this.getApplicationContext()).getRequestQueue();
        Singleton.getInstance(this).addToRequestQueue(request);

    }
}