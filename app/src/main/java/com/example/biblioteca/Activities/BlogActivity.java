package com.example.biblioteca.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.biblioteca.Adapters.PostAdapter;
import com.example.biblioteca.Model.Post;
import com.example.biblioteca.Model.Singleton;
import com.example.biblioteca.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BlogActivity extends AppCompatActivity {

    //definição de variáveis
    private TextView tv_back;
    private RecyclerView rv_posts;
    private final String URL = "http://10.0.2.2/biblioteca/frontend/web/posts/list";
    private List<Post> post_list;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        //referenciar variáveis
        tv_back = findViewById(R.id.tv_blog_back);
        rv_posts = findViewById(R.id.rv_posts);

        rv_posts.setHasFixedSize(true);
        rv_posts.setLayoutManager(new LinearLayoutManager(this));

        post_list = new ArrayList<>();
        getPosts();

        //método para voltar atrás
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //método para carregar todos os posts
    private void getPosts() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        Post post = new Post();

                        post.setId(object.getString("id"));
                        post.setTitle(object.getString("title"));
                        post.setText(object.getString("text"));
                        post.setAuthor(object.getString("author"));
                        post.setDate(object.getString("created_at"));
                        //post.setComment(object.getString("comment"));
                        post_list.add(post);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rv_posts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new PostAdapter(getApplicationContext(), post_list);
                rv_posts.setAdapter(adapter);

                setRecyclerView(post_list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BlogActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        //fazer o pedido através da classe Singleton
        Singleton.getInstance(this.getApplicationContext()).getRequestQueue();
        Singleton.getInstance(this).addToRequestQueue(request);
    }
    //preparar a recyclerview com os dados
    private void setRecyclerView(List<Post> post_list) {
        PostAdapter adapter = new PostAdapter(this, post_list);
        rv_posts.setLayoutManager(new LinearLayoutManager(this));
        rv_posts.setAdapter(adapter);
    }
}