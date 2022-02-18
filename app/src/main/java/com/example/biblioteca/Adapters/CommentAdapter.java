package com.example.biblioteca.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.biblioteca.Activities.CommentActivity;
import com.example.biblioteca.Model.Comment;
import com.example.biblioteca.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {

    //declaração de variáveis
    private final Context context;
    private final List<Comment> comment_list;
    //LayoutInflater inflater;

    //construtor da classe
    public CommentAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.comment_list = list;
        //this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.comment_item, parent, false);

        CommentHolder holder = new CommentHolder(view);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(context, CommentActivity.class);
                details.putExtra("author", comment_list.get(holder.getAdapterPosition()).getAuthor());
                details.putExtra("text", comment_list.get(holder.getAdapterPosition()).getText());
                details.putExtra("idpost", comment_list.get(holder.getAdapterPosition()).getIdpost());

                context.startActivity(details);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        Comment comment = comment_list.get(position);
        holder.author.setText(comment_list.get(position).getAuthor());
        holder.text.setText(comment_list.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return comment_list.size();
    }

    public static class CommentHolder extends RecyclerView.ViewHolder {

        TextView author, text;
        LinearLayout layout;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.tv_comment_author);
            text = itemView.findViewById(R.id.tv_comment_text);
            layout = itemView.findViewById(R.id.comment_details_layout);
        }
    }

}
