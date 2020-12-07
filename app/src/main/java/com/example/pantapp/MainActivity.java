package com.example.pantapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pantapp.Model.UserParcelable;
import com.example.pantapp.ui.avistamientos.avistamientoFragment;
import com.example.pantapp.ui.home.HomeFragment;
import com.example.pantapp.ui.inmercionn.inmercionFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private UserParcelable user;
    private int ident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
        ImageView photo = (ImageView)header.findViewById(R.id.imageView);
        try{
            Bundle bundle = getIntent().getExtras();
            user = bundle.getParcelable("DATA_USER");
            if(bundle!=null){
                ident = user.getId();
                ((TextView) header.findViewById(R.id.tv_nombre_user_nav_header)).setText(user.getNombre());
                ((TextView) header.findViewById(R.id.tv_email_user_nav_header)).setText(user.getEmail());
                if(!user.getImage().equals("sin imagen")){
                    String url_image = "http://192.168.1.73:8086/pnatdb"+user.getImage();
                    url_image = url_image.replace(" ","%20");
                    try {
                        Log.i("RESPUESTA IMAGE: ",""+url_image);
                        Glide.with(this).load(url_image).into(photo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        HomeFragment homeFragment=new HomeFragment();
        FragmentManager frag=getSupportFragmentManager();
        frag.beginTransaction().add(R.id.nav_host_fragment,homeFragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.action_inmercionFragment_to_avistamientoFragment,R.id.action_nav_home_to_inmercionFragment)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*
        inmercionFragment inmercionFragment=new inmercionFragment();
        FragmentManager inm=getSupportFragmentManager();
        inm.beginTransaction().add(R.id.nav_host_fragment,inmercionFragment).commit();

        /*
        avistamientoFragment avistamientoFragment=new avistamientoFragment();
        FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction().add(R.id.nav_host_fragment,avistamientoFragment).commit();*/


    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}