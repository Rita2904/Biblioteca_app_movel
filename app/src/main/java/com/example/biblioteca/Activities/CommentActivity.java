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

public class CommentActivity extends AppCompatActivity {

    //declaração de variáveis
    private final static String URL = "http://10.0.2.2/biblioteca/frontend/web/comments/comment";
    private EditText tv_author, tv_commentText;
    private String author, comment, id;
    private ImageButton btn_save_comment;
    private TextView tv_save_comment, tv_cancel_comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //passar valores
        id = getIntent().getExtras().getString("idpost");

        //referenciar variáveis
        tv_author = findViewById(R.id.tv_comment_commentAuthor);
        tv_commentText = findViewById(R.id.tv_comment_commentText);
        tv_save_comment = findViewById(R.id.tv_comment_save);
        tv_cancel_comment = findViewById(R.id.tv_comment_cancel);


        //evento para submeter comentário
        tv_save_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                author = tv_author.getText().toString();
                comment = tv_commentText.getText().toString();
                if(!author.isEmpty() && !comment.isEmpty()) {
                    saveComment();
                } else {
                    tv_author.setError("Insira o seu nome");
                    tv_commentText.setError("Insira o seu comentário");
                }
            }
        });

        //evento para cancelar comentário
        tv_cancel_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //método para submeter comentário
    private void saveComment() {
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.length() != 0) {
                    //Toast.makeText(CommentActivity.this, "Comentário submetido com sucesso", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(CommentActivity.this, "Erro " + response, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CommentActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("author", author);
                params.put("text", comment);
                params.put("idpost", id);
                return params;
            }
        };
        //fazer o pedido através da classe Singleton
        Singleton.getInstance(this.getApplicationContext()).getRequestQueue();
        Singleton.getInstance(this).addToRequestQueue(request);
    }
}