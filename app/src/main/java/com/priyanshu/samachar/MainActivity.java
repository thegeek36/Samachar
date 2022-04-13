package com.priyanshu.samachar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button signout;
    FirebaseAuth fAuth;
    TextView txt;
    TabLayout tabLayout;
    TabItem mhome, msports, mtech, mscience, mentertainment, mhealth;
    PagerAdapter pagerAdapter;
    Toolbar mtoolbar, menubar;
    //String api ="1b72f4ef67b54ce1b25b4b51f08fdbd1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // signout = findViewById(R.id.button);
        fAuth = FirebaseAuth.getInstance();

        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        menubar = findViewById(R.id.menu_bar);
        setSupportActionBar(menubar);
        mhome = findViewById(R.id.home);
        msports = findViewById(R.id.sports);
        mentertainment = findViewById(R.id.entertainment);
        mtech = findViewById(R.id.tech);
        mhealth = findViewById(R.id.health);
        mscience = findViewById(R.id.science);


        ViewPager viewPager = findViewById(R.id.fragment);
        tabLayout = findViewById(R.id.include);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 6);
        viewPager.setAdapter(pagerAdapter);

//        signout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fAuth.signOut();
//                Intent intent = new Intent(MainActivity.this, Login.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3 || tab.getPosition() == 4 || tab.getPosition() == 5) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.credits:
                Toast.makeText(this, "Created By Priyanshu Panda", Toast.LENGTH_SHORT).show();
                break;

            case R.id.sign_out:
                fAuth.signOut();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();


        }
        return true;
    }
}