package com.komsi.maleshop.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.komsi.maleshop.fragment.CartFragment;
import com.komsi.maleshop.fragment.CategoryFragment;
import com.komsi.maleshop.fragment.HomeFragment;
import com.komsi.maleshop.fragment.ProfileFragment;
import com.komsi.maleshop.R;
import com.komsi.maleshop.fragment.WishlistFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        toolbar.setTitle("Male Shop");

                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.navigation_category:
                        toolbar.setTitle("Kategori");

                        selectedFragment = new CategoryFragment();

                        break;
                    case R.id.navigation_cart:
                        toolbar.setTitle("Cart");

                        selectedFragment = new CartFragment();

                        break;
                    case R.id.navigation_wishlist:
                        toolbar.setTitle("Disimpan");
                        selectedFragment = new WishlistFragment();

                        break;
                    case R.id.navigation_profile:
                        toolbar.setTitle("Profile");
                        selectedFragment = new ProfileFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,selectedFragment).commit();
                return true;
            }
        });

    }
}
