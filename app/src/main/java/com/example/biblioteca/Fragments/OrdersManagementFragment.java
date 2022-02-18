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
import com.example.biblioteca.Adapters.OrderManagementAdapter;
import com.example.biblioteca.Model.Order;
import com.example.biblioteca.Model.Singleton;
import com.example.biblioteca.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class OrdersManagementFragment extends Fragment {

    //definir variáveis
    private final String url = "http://10.0.2.2/biblioteca/frontend/web/orders/list";
    private RecyclerView rv_orders_management;
    private List<Order> list;
    SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orders_management, container, false);
        rv_orders_management = view.findViewById(R.id.rv_orders_management);
        refreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        list = new ArrayList<>();


        //evento para carregar pedidos
        showOrders();
        return view;


    }

    //método para carregar requisições
    private void showOrders() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        Order order = new Order();
                        order.setId(object.getString("id"));
                        order.setDescription(object.getString("descricao"));
                        order.setDate(object.getString("data"));
                        order.setName(object.getString("nome"));
                        order.setEmail(object.getString("email"));
                        order.setDeadline(object.getString("prazo"));
                        list.add(order);
                        setRecyclerView(list);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //fazer o pedido através da classe Singleton
        Singleton.getInstance(this.getContext()).getRequestQueue();
        Singleton.getInstance(getContext()).addToRequestQueue(request);

    }

    //preparar a recyclerview com os dados
    private void setRecyclerView(List<Order> list) {
        OrderManagementAdapter adapter = new OrderManagementAdapter(getContext(), list);
        rv_orders_management.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_orders_management.setAdapter(adapter);
    }

}