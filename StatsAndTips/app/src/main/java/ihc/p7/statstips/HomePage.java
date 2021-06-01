package ihc.p7.statstips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ihc.p7.statstips.fragments.AccountFragment;
import ihc.p7.statstips.fragments.GuideFragment;
import ihc.p7.statstips.fragments.HomeFragment;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        BottomNavigationView bottomNavBar = findViewById(R.id.bottom_navbar);
        bottomNavBar.setOnNavigationItemSelectedListener(navbarListener);

        // To start with HomeFragment()
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_navbar, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navbarListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selected = null;
            switch (item.getItemId()){
                case R.id.homepage:
                    selected = new HomeFragment();
                    break;
                case R.id.guide:
                    selected = new GuideFragment();
                    break;
                case R.id.account:
                    selected = new AccountFragment();
                    break;
            }

            assert selected != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_navbar, selected).commit();
            return true;
        }
    };
}