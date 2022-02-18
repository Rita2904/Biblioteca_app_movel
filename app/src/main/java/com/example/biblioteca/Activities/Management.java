package com.example.biblioteca.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.biblioteca.Fragments.CommentManagementFragment;
import com.example.biblioteca.Fragments.HomeManagementFragment;
import com.example.biblioteca.Fragments.OrdersManagementFragment;
import com.example.biblioteca.DialogBox.DialogBox;
import com.example.biblioteca.R;
import com.example.biblioteca.databinding.ActivityManagementBinding;
import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Management extends AppCompatActivity implements DialogBox.DialogListener {

    ActivityManagementBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //evento para credenciais de acesso
        adminLogin();

        //ligação dos fragmentos
        binding = ActivityManagementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //apresentar a página inicial
        switchFragment(new HomeManagementFragment());

        //evento para mudar de fragmento
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.management_home:
                        switchFragment(new HomeManagementFragment());
                        break;
                    case R.id.management_comments:
                        switchFragment(new CommentManagementFragment());
                        break;
                    case R.id.management_orders:
                        switchFragment(new OrdersManagementFragment());
                        break;
                    case R.id.management_logout:
                        finish();
                }
                return true;
            }
        });
    }

    //apresentar a dialogBox
    private void adminLogin() {
        DialogBox dialogBox = new DialogBox();
        dialogBox.show(getSupportFragmentManager(), "dialog box");
    }

    //método para credenciais de acesso
    @Override
    public void login(String username, String password) {
        if(username.equals("Ana Fernandes") && password.equals("ana12345")) {
            Toast.makeText(Management.this, "Login com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
    }

    //apresentar o fragmento selecionado
    private void switchFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

}