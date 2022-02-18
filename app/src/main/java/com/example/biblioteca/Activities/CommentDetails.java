package com.example.biblioteca.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.biblioteca.Model.Singleton;
import com.example.biblioteca.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CommentDetails extends AppCompatActivity {

    //definição de variáveis
    private String id, author, text, idpost;
    private TextView tv_author, tv_text, tv_idpost;
    private TextView tv_delete_comment;
    private final String url = "http://10.0.2.2/biblioteca/frontend/web/comments/delete/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_details);

        //passagem de valores
        id = getIntent().getExtras().getString("id");
        author = getIntent().getExtras().getString("author");
        text = getIntent().getExtras().getString("text");
        idpost = getIntent().getExtras().getString("idpost");

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.comment_details_collapsing_toolbar);
        toolbarLayout.setTitleEnabled(true);

        //referenciar variáveis
        tv_author = findViewById(R.id.tv_comment_details_author);
        tv_text = findViewById(R.id.tv_comment_details_text);
        tv_idpost = findViewById(R.id.tv_comment_details_idpost);
        tv_delete_comment = findViewById(R.id.tv_comment_details_delete);

        //atrubuir valores
        tv_author.setText(author);
        tv_text.setText(text);
        tv_idpost.setText(idpost);

        //toolbarLayout.setTitle(id);

        tv_delete_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CommentDetails.this);
                builder.setTitle("Apagar comentário?").setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteComment();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                }).show();
            }
        });
    }

    //apagar comentário selecionado
    public void deleteComment() {
        StringRequest request = new StringRequest(Request.Method.DELETE, url + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.length() == 0) {
                    Toast.makeText(CommentDetails.this, "Comentário apagado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CommentDetails.this, "Não foi possível apagar o comentário selecionado",
                    Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CommentDetails.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //fazer o pedido através da classe Singleton
        Singleton.getInstance(getApplicationContext()).getRequestQueue();
        Singleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}