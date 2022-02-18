package com.example.biblioteca.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.biblioteca.Model.Singleton;
import com.example.biblioteca.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class OrderDetails extends AppCompatActivity {

    private final String url = "http://10.0.2.2/biblioteca/frontend/web/orders/update/";
    private String id, description, deadline, time;
    private TextView tv_update_order;
    private EditText tv_deadline;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        //declaração de variáveis
        String name, email, date;
        TextView tv_id, tv_name, tv_description, tv_email, tv_date;

        //passagem de valores
        id = getIntent().getExtras().getString("id");
        description = getIntent().getExtras().getString("descricao");
        date = getIntent().getExtras().getString("data");
        deadline = getIntent().getExtras().getString("prazo");
        name = getIntent().getExtras().getString("nome");
        email = getIntent().getExtras().getString("email");

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.order_details_collapsing_toolbar);
        toolbarLayout.setTitleEnabled(true);

        //referenciar variáveis
        tv_id = findViewById(R.id.tv_order_details_id);
        tv_name = findViewById(R.id.tv_order_details_name);
        tv_description = findViewById(R.id.tv_order_details_description);
        tv_email = findViewById(R.id.tv_order_details_email);
        tv_date = findViewById(R.id.tv_order_details_date);
        tv_deadline = findViewById(R.id.tv_order_details_deadline);
        tv_update_order = findViewById(R.id.tv_order_details_update);
        refreshLayout = findViewById(R.id.swipe_refresh_layout);

        //atribuir valores
        tv_id.setText(id);
        tv_name.setText(name);
        tv_description.setText(description);
        tv_email.setText(email);
        tv_date.setText(date);
        tv_deadline.setText("");

        //evento para editar pedido
        tv_update_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = tv_deadline.getText().toString();
                //Toast.makeText(OrderDetails.this, time, Toast.LENGTH_SHORT).show();
                updateOrder();
            }
        });

    }

    //método para editar pedido
    private void updateOrder() {

        StringRequest request = new StringRequest(Request.Method.PUT, url + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.length() != 0) {
                    Toast.makeText(OrderDetails.this, "Alterações efetuadas com sucesso", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(OrderDetails.this, "Não foi possível guardar as alterações efetuadas",
                    Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OrderDetails.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("prazo", time);
                return params;
            }
        };

        //fazer o pedido através da classe Singleton
        Singleton.getInstance(this.getApplicationContext()).getRequestQueue();
        Singleton.getInstance(this.getApplicationContext()).addToRequestQueue(request);
    }
}