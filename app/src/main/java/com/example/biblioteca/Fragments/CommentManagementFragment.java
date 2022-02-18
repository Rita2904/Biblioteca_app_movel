package com.example.biblioteca.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.biblioteca.Adapters.CommentManagementAdapter;
import com.example.biblioteca.Model.Comment;
import com.example.biblioteca.Model.Singleton;
import com.example.biblioteca.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CommentManagementFragment extends Fragment {
    private final String url = "http://10.0.2.2/biblioteca/frontend/web/comments/all";
    private RecyclerView rv_comments_management;
    private List<Comment> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_comment_management, container, false);
       rv_comments_management = view.findViewById(R.id.rv_comments_management);
       list = new ArrayList<>();
       showComments();
       return view;
    }

    //método para carregar todos os comentários
    private void showComments() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        Comment comment = new Comment();
                        comment.setId(object.getString("id"));
                        comment.setAuthor(object.getString("author"));
                        comment.setText(object.getString("text"));
                        comment.setIdpost(object.getString("idpost"));
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
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        //fazer o pedido através da classe Singleton
        Singleton.getInstance(this.getContext()).getRequestQueue();
        Singleton.getInstance(getContext()).addToRequestQueue(request);
    }
    //preparar a recyclerview com os dados
    private void setRecyclerView(List<Comment> list) {
        CommentManagementAdapter adapter = new CommentManagementAdapter(getContext(), list);
        rv_comments_management.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_comments_management.setAdapter(adapter);
    }

}