package com.example.drawer_layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private RelativeLayout firstItem;
    private RelativeLayout secondItem;
    private int currentId = R.id.first_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firstFragment = new FirstFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content, firstFragment).commit();
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.draw_layout);
        findViewById(R.id.menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.menu_button:
                        drawerLayout.openDrawer(Gravity.LEFT);
                        break;
                }
            }
        });

        firstItem = findViewById(R.id.first_item);
        secondItem = findViewById(R.id.second_item);
        firstItem.setOnClickListener(itemClick);
        secondItem.setOnClickListener(itemClick);
        firstItem.setSelected(true);
    }

    private View.OnClickListener itemClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            drawerLayout.closeDrawer(Gravity.LEFT);

            int selectId = view.getId();
            if (selectId == currentId) {
                return;
            }
            currentId = selectId;

            firstItem.setSelected(false);
            secondItem.setSelected(false);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (firstFragment != null) {
                transaction.hide(firstFragment);
            }
            if (secondFragment != null) {
                transaction.hide(secondFragment);
            }

            switch (selectId) {
                case R.id.first_item:
                    firstItem.setSelected(true);
                    transaction.show(firstFragment);
                    break;
                case R.id.second_item:
                    secondItem.setSelected(true);
                    if (secondFragment == null) {
                        secondFragment = new SecondFragment();
                        transaction.add(R.id.content, secondFragment);
                    } else {
                        transaction.show(secondFragment);
                    }
                    break;
            }

            transaction.commit();
        }
    };
}
