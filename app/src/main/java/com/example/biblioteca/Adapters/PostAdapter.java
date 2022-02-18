package com.example.biblioteca.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.biblioteca.Activities.BookDetails;
import com.example.biblioteca.Activities.PostDetails;
import com.example.biblioteca.Model.Post;
import com.example.biblioteca.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder>{

    //definir variáveis
    private final Context context;
    private final List<Post> postList;
    LayoutInflater inflater;

    //construtor da classe
    public PostAdapter(Context context, List<Post> postList) {

        this.context = context;
        this.postList = postList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        PostHolder holder = new PostHolder(view);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(context, PostDetails.class);
                details.putExtra("id", postList.get(holder.getAdapterPosition()).getId());
                details.putExtra("title", postList.get(holder.getAdapterPosition()).getTitle());
                details.putExtra("author", postList.get(holder.getAdapterPosition()).getAuthor());
                details.putExtra("text", postList.get(holder.getAdapterPosition()).getText());
                details.putExtra("created_at", postList.get(holder.getAdapterPosition()).getDate());

                context.startActivity(details);
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.author.setText(post.getAuthor());
        holder.date.setText(post.getDate());
        //Glide.with(context).load(post.getImage()).into(holder.post_image);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostHolder extends RecyclerView.ViewHolder {

        //definir variáveis
        ImageView post_image;
        TextView title, author, date;
        LinearLayout layout;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            //referenciar variáveis
            post_image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.tv_post_title);
            author = itemView.findViewById(R.id.tv_post_author);
            date = itemView.findViewById(R.id.tv_post_date);
            layout = itemView.findViewById(R.id.post_details_layout);
        }
    }
}
