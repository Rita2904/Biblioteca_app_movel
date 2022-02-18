package com.example.biblioteca.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.biblioteca.Adapters.RecyclerViewAdapter;
import com.example.biblioteca.Model.Book;
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

public class BooksActivity extends AppCompatActivity {

    private final static String JSON_URL = "http://10.0.2.2/biblioteca/frontend/web/books/list";
    private JsonArrayRequest request;
    private List<Book> bookList;
    private RecyclerView recyclerView;
    private TextView tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        bookList = new ArrayList<>();
        recyclerView = findViewById(R.id.rvBooks);
        tv_back = findViewById(R.id.tv_books_back);
        listBooks();

        //evento para voltar atrás
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //listar livros
    private void listBooks() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request = new JsonArrayRequest(Request.Method.POST, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(BooksActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                int pages;
                if(response.length() != 0) {
                    for(int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            //Toast.makeText(BooksActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
                            Book book = new Book();
                            int id = object.getInt("id");
                            book.setTitle(object.getString("Titulo"));
                            book.setAuthor(object.getString("Autor"));
                            book.setTheme(object.getString("Tema"));
                            book.setEditor(object.getString("editora"));
                            book.setPages(object.getString("numeropaginas"));
                            book.setIsbn(object.getString("isbn"));
                            book.setImage(object.getString("imagem"));
                            bookList.add(book);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    };
                } else {
                    Toast.makeText(BooksActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }
                setRecyclerView(bookList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BooksActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        //fazer o pedido através da classe Singleton
        Singleton.getInstance(this.getApplicationContext()).getRequestQueue();
        Singleton.getInstance(this).addToRequestQueue(request);
    }

    //preparar a recyclerview com os dados
    private void setRecyclerView(List<Book> bookList) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, bookList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}