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
import com.bumptech.glide.request.RequestOptions;
import com.example.biblioteca.Activities.BookDetails;
import com.example.biblioteca.Model.Book;
import com.example.biblioteca.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BookViewHolder> {

    //definição de variáveis
    private final Context context;
    private final List<Book> bookList;
    LayoutInflater inflater;
    RequestOptions options;

    public RecyclerViewAdapter(Context context, List<Book> bookList) {

        this.inflater = LayoutInflater.from(context);
        this.bookList = bookList;
        this.context = context;

        //Glide request option
        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_item, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(view);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookDetails.class);
                intent.putExtra("titulo", bookList.get(viewHolder.getAdapterPosition()).getTitle());
                intent.putExtra("autor", bookList.get(viewHolder.getAdapterPosition()).getAuthor());
                intent.putExtra("tema", bookList.get(viewHolder.getAdapterPosition()).getTheme());
                intent.putExtra("editora", bookList.get(viewHolder.getAdapterPosition()).getEditor());
                intent.putExtra("isbn", bookList.get(viewHolder.getAdapterPosition()).getIsbn());
                intent.putExtra("numeropaginas", bookList.get(viewHolder.getAdapterPosition()).getPages());
                intent.putExtra("imagem", bookList.get(viewHolder.getAdapterPosition()).getImage());

                context.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        holder.tvTitle.setText(bookList.get(position).getTitle());
        holder.tvAuthor.setText(bookList.get(position).getAuthor());
        holder.tvTheme.setText(bookList.get(position).getTheme());
        holder.tvEditor.setText(bookList.get(position).getEditor());
        holder.tvIsbn.setText(bookList.get(position).getIsbn());
        holder.tvPages.setText(bookList.get(position).getPages());

        //carregar imagem
        Glide.with(context).load("http://10.0.2.2/biblioteca/frontend/" + bookList.get(position).getImage()).apply(options).into(holder.imgThumbnail);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvAuthor, tvTheme, tvEditor, tvIsbn, tvPages;
        ImageView imgThumbnail;
        LinearLayout container;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.bookContainer);
            tvTitle = itemView.findViewById(R.id.book_title);
            tvAuthor = itemView.findViewById(R.id.book_author);
            tvTheme = itemView.findViewById(R.id.book_theme);
            tvEditor = itemView.findViewById(R.id.book_editor);
            tvIsbn = itemView.findViewById(R.id.book_isbn);
            tvPages = itemView.findViewById(R.id.book_pages);
            imgThumbnail = itemView.findViewById(R.id.book_details_image);
        }
    }
}
