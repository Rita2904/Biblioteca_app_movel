package com.example.biblioteca.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.biblioteca.Model.Book;
import com.example.biblioteca.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class BookDetails extends AppCompatActivity {

    private List<Book> list;
    private String title, author, theme, editor, isbn, pages, image;
    private int book_id;
    TextView tv_title, tv_author, tv_theme, tv_editor, tv_isbn, tv_pages;
    TextView tv_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        //passagem de valores
        book_id = getIntent().getExtras().getInt("id");
        title = getIntent().getExtras().getString("titulo");
        author = getIntent().getExtras().getString("autor");
        theme = getIntent().getExtras().getString("tema");
        editor = getIntent().getExtras().getString("editora");
        isbn = getIntent().getExtras().getString("isbn");
        pages = getIntent().getExtras().getString("numeropaginas");
        image = getIntent().getExtras().getString("imagem");


        //inicializar variáveis
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.book_colapsingToolBar);
        toolbarLayout.setTitleEnabled(true);

        tv_title = findViewById(R.id.book_details_title);
        tv_author = findViewById(R.id.book_details_author);
        tv_theme = findViewById(R.id.book_details_theme);
        tv_editor = findViewById(R.id.bookDetailsEditor);
        tv_isbn = findViewById(R.id.bookDetailsIsbn);
        tv_pages = findViewById(R.id.bookDetailsPages);
        ImageView book_image = findViewById(R.id.book_details_image);
        tv_request = findViewById(R.id.tv_book_request);

        //atribuição de valores
        tv_title.setText(title);
        tv_author.setText(author);
        tv_theme.setText(theme);
        tv_editor.setText(editor);
        tv_isbn.setText(isbn);
        tv_pages.setText(pages);

        toolbarLayout.setTitle(title);

        //carregar imagem com Glide
        Glide.with(this).load(image).centerCrop().error(R.drawable.book_icon).into(book_image);

        //voltar atrás
        book_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //requisitar livro
        tv_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent request = new Intent(BookDetails.this, OrderActivity.class);
                request.putExtra("titulo", title);
                request.putExtra("autor", author);
                request.putExtra("id", book_id);
                startActivity(request);
            }
        });
    }
}