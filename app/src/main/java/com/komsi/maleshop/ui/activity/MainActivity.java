package com.komsi.maleshop.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.komsi.maleshop.helper.NotificationHelper;
import com.komsi.maleshop.helper.ToolbarHelper;
import com.komsi.maleshop.persistence.Prefs;
import com.komsi.maleshop.ui.fragment.CartFragment;
import com.komsi.maleshop.ui.fragment.CategoryFragment;
import com.komsi.maleshop.ui.fragment.HomeFragment;
import com.komsi.maleshop.ui.fragment.ProfileFragment;
import com.komsi.maleshop.R;
import com.komsi.maleshop.ui.fragment.WishlistFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    Fragment selectedFragment = new HomeFragment();

    public static void hideBottomNavigationView(BottomNavigationView view) {
        view.clearAnimation();
        view.animate().translationY(view.getHeight()).setDuration(500);
        view.setVisibility(View.GONE);
    }

    public static void showBottomNavigationView(BottomNavigationView view) {
        view.clearAnimation();
        view.animate().translationY(0).setDuration(500);
        view.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        ToolbarHelper.INSTANCE.setToolbar(toolbar);
        setupDailyReminder();

        if (actionBar != null) {
            ToolbarHelper.INSTANCE.setActionBar(actionBar);
        }

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
        }
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        toolbar.setTitle(R.string.home);
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.navigation_category:
//                        toolbar.setTitle("Category");
                        ToolbarHelper.INSTANCE.setTitle(getString(R.string.category));
                        selectedFragment = new CategoryFragment();
                        break;
                    case R.id.navigation_cart:
//                        toolbar.setTitle("Cart");
                        ToolbarHelper.INSTANCE.setTitle(getString(R.string.cart));
                        selectedFragment = new CartFragment();

                        break;
                    case R.id.navigation_wishlist:
//                        toolbar.setTitle("Wishlist");
                        ToolbarHelper.INSTANCE.setTitle(getString(R.string.wishlist));
                        selectedFragment = new WishlistFragment();
                        break;
                    case R.id.navigation_profile:
//                        toolbar.setTitle("Profile");
                        ToolbarHelper.INSTANCE.setTitle(getString(R.string.profile));
                        selectedFragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,selectedFragment).commit();
                return true;
            }
        });
    }

    private void setupDailyReminder() {
        boolean daily = Prefs.INSTANCE.getDailyReminderSetting(this);
        if (daily){
            NotificationHelper.INSTANCE.setupDailyReminder(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            showBottomNavigationView(bottomNavigationView);
            ToolbarHelper.INSTANCE.displayBackArrow(false);
        }
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
