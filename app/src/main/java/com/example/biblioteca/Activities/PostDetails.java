package com.example.biblioteca.Activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.biblioteca.Adapters.CommentAdapter;
import com.example.biblioteca.Model.Comment;
import com.example.biblioteca.Model.Singleton;
import com.example.biblioteca.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PostDetails extends AppCompatActivity {

    private final String url = "http://10.0.2.2/biblioteca/frontend/web/comments/list/";
    private String id;
    private TextView tv_comments;
    private RecyclerView rv_comments;
    private CommentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Comment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        //definir variáveis
        String title, author, text, date;
        TextView tv_title, tv_author, tv_text, tv_date;

        //passagem de valores
        id = getIntent().getExtras().getString("id");
        title = getIntent().getExtras().getString("title");
        author = getIntent().getExtras().getString("author");
        text = getIntent().getExtras().getString("text");
        date = getIntent().getExtras().getString("created_at");

        //inicializar variáveis
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.post_collapsing_toolbar);
        toolbarLayout.setTitleEnabled(true);

        tv_title = findViewById(R.id.tv_post_details_title);
        //tv_author = findViewById(R.id.tv_post_details_author);
        tv_date = findViewById(R.id.tv_post_detais_date);
        tv_text = findViewById(R.id.tv_post_details_text);

        rv_comments = findViewById(R.id.rv_comments);
        layoutManager = new LinearLayoutManager(this);
        rv_comments.setLayoutManager(layoutManager);
        //rv_comments.setHasFixedSize(true);
        list = new ArrayList<>();

        //atribuição de valores
        tv_title.setText(title);
        // tv_author.setText(author);
        tv_date.setText(date);
        tv_text.setText(text);

        //evento para ver comentários
        showComments();
    }

    //método para mostrar comentários
    private void showComments() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url + id, null, new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                    //tv_comments.setText(response);
                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        Comment comment = new Comment();
                        comment.setAuthor(object.getString("Autor"));
                        comment.setText(object.getString("text"));
                        comment.setIdpost(id);
                        list.add(comment);
                        setRecyclerView(list);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PostDetails.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        //fazer o pedido através da classe Singleton
        Singleton.getInstance(this.getApplicationContext()).getRequestQueue();
        Singleton.getInstance(this).addToRequestQueue(request);
    }
    //preparar a recyclerview com os dados
    private void setRecyclerView(List<Comment> list) {
        CommentAdapter adapter = new CommentAdapter(this, list);
        rv_comments.setLayoutManager(new LinearLayoutManager(this));
        rv_comments.setAdapter(adapter);
    }



}
