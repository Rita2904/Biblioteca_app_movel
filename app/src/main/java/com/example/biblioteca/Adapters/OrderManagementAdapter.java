package com.example.biblioteca.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.biblioteca.Activities.OrderDetails;
import com.example.biblioteca.Model.Order;
import com.example.biblioteca.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderManagementAdapter extends RecyclerView.Adapter<OrderManagementAdapter.ViewHolder> {

    //criação de variáveis
    private final Context context;
    private final List<Order> list;
    private String id;

    public OrderManagementAdapter(Context context, List<Order> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.orders_management_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orders = new Intent(context, OrderDetails.class);
                orders.putExtra("id", list.get(holder.getAdapterPosition()).getId());
                orders.putExtra("descricao", list.get(holder.getAdapterPosition()).getDescription());
                orders.putExtra("data", list.get(holder.getAdapterPosition()).getDate());
                orders.putExtra("quantidade", list.get(holder.getAdapterPosition()).getDeadline());
                orders.putExtra("nome", list.get(holder.getAdapterPosition()).getName());
                orders.putExtra("email", list.get(holder.getAdapterPosition()).getEmail());
                orders.putExtra("prazo", list.get(holder.getAdapterPosition()).getDeadline());
                context.startActivity(orders);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = list.get(position);
        holder.id.setText(order.getId());
        holder.description.setText(order.getDescription());
        holder.date.setText(order.getDate());
        holder.deadline.setText(order.getDeadline());
        holder.name.setText(order.getName());
        holder.email.setText(order.getEmail());
        holder.deadline.setText(order.getDeadline());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, description, email, date, deadline;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_order_management_id);
            name = itemView.findViewById(R.id.tv_order_management_name);
            description = itemView.findViewById(R.id.tv_order_management_description);
            email = itemView.findViewById(R.id.tv_order_management_email);
            date = itemView.findViewById(R.id.tv_order_management_date);
            deadline = itemView.findViewById(R.id.tv_order_management_time);
            layout = itemView.findViewById(R.id.orders_management_layout);
        }
    }

}
