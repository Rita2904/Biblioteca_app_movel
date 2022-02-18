package com.example.biblioteca.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.example.biblioteca.Adapters.ImageAdapter;
import com.example.biblioteca.Model.SliderImage;
import com.example.biblioteca.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //definir variáveis
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView nav_view;

    private ViewPager2 viewPager;
    private final Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //atribuir valores às variáveis
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.navigation_view);

        viewPager = findViewById(R.id.vpImageSlider);


        //lista de imagens //
        List<SliderImage> sliderImages = new ArrayList<>();
        sliderImages.add(new SliderImage(R.drawable.livro_1));
        sliderImages.add(new SliderImage(R.drawable.livro_2));
        sliderImages.add(new SliderImage(R.drawable.livro_3));
        sliderImages.add(new SliderImage(R.drawable.livro_4));
        sliderImages.add(new SliderImage(R.drawable.livro_5));
        sliderImages.add(new SliderImage(R.drawable.livro_6));
        sliderImages.add(new SliderImage(R.drawable.livro_7));
        sliderImages.add(new SliderImage(R.drawable.livro_8));
        sliderImages.add(new SliderImage(R.drawable.livro_9));
        sliderImages.add(new SliderImage(R.drawable.livro_10));
        sliderImages.add(new SliderImage(R.drawable.livro_11));
        sliderImages.add(new SliderImage(R.drawable.livro_12));
        sliderImages.add(new SliderImage(R.drawable.livro_13));

        viewPager.setAdapter(new ImageAdapter(sliderImages, viewPager));
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float f = 1 - Math.abs(position);
                page.setScaleY(0.85f + f * 0.15f);

            }
        });

        viewPager.setPageTransformer(compositePageTransformer);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(sliderRunnable);
                handler.postDelayed(sliderRunnable, 2000);
            }
        });

        //barra de menu
        setSupportActionBar(toolbar);


        //chamar o menu de navegação
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
        R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(this);

//        //eventos para os botões de conta de utilizador
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //verificar início de sessão
//                if(Singleton.getInstance(getApplicationContext()).getUser() == null) {
//                    Intent login = new Intent(MainActivity.this, LoginActivity.class);
//                    startActivity(login);
//                } else {
//                    btnSignup.setVisibility(View.INVISIBLE);
//                    Intent intent = new Intent(MainActivity.this, AccountActivity.class);
//                    startActivity(intent);
//                }
//
//            }
//        });
//
//        if(Singleton.getInstance(getApplicationContext()).getUser() != null) {
//            btnSignup.setVisibility(View.INVISIBLE);
//        } else {
//            btnSignup.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent signup = new Intent(MainActivity.this, SignUpActivity.class);
//                    startActivity(signup);
//                }
//            });
//        }
    }

    //implementar slider automático
    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(sliderRunnable, 2000);
    }


    //fechar o menu de navegação
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //itens do menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //início
            case R.id.home:
                onBackPressed();
                break;
            //consulta de livros
            case R.id.books:
                Intent book = new Intent(this, BooksActivity.class);
                startActivity(book);
                break;
            //blog
            case R.id.blog:
                Intent blog = new Intent(this, BlogActivity.class);
                startActivity(blog);
                break;
            //gestão
            case R.id.management:
                Intent management = new Intent(this, Management.class);
                startActivity(management);
                break;
        }
        return true;
    }
}