package com.example.biblioteca.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.biblioteca.Activities.CommentDetails;
import com.example.biblioteca.Model.Comment;
import com.example.biblioteca.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentManagementAdapter extends RecyclerView.Adapter<CommentManagementAdapter.ViewHolder> {


    //criação de variáveis
    private final Context context;
    private final List<Comment> list;
    private String id;

    public CommentManagementAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.comments_management_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(context, CommentDetails.class);
                details.putExtra("id", list.get(holder.getAdapterPosition()).getId());
                details.putExtra("author", list.get(holder.getAdapterPosition()).getAuthor());
                details.putExtra("text", list.get(holder.getAdapterPosition()).getText());
                details.putExtra("idpost", list.get(holder.getAdapterPosition()).getIdpost());
                context.startActivity(details);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = list.get(position);
        id = comment.getId();
        holder.id.setText(comment.getId());
        holder.author.setText(comment.getAuthor());
        holder.text.setText(comment.getText());
        holder.idpost.setText(comment.getIdpost());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, author, text, idpost;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_comment_management_id);
            author = itemView.findViewById(R.id.tv_comment_management_author);
            text = itemView.findViewById(R.id.tv_comment_management_text);
            idpost = itemView.findViewById(R.id.tv_comment_management_idpost);
            layout = itemView.findViewById(R.id.comments_management_layout);
        }
    }

}