package ihc.p7.statstips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import ihc.p7.statstips.fragments.AccountFragment;
import ihc.p7.statstips.fragments.GuideFragment;
import ihc.p7.statstips.fragments.HomeFragment;

public class HomePage extends AppCompatActivity {

    private String[] names = {"Recommended Events", "Standings - League/Cup", "Teams - League/Cup"};
    private CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        BottomNavigationView bottomNavBar = findViewById(R.id.bottom_navbar);
        bottomNavBar.setOnNavigationItemSelectedListener(navbarListener);


        // To start with HomeFragment()
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_navbar, new HomeFragment()).commit();
        circleMenu = findViewById(R.id.circleMenu);//314755

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.add, R.drawable.remove)
                .addSubMenu(Color.parseColor("#CDCDCD"), R.drawable.star)
                .addSubMenu(Color.parseColor("#CDCDCD"), R.drawable.standings)
                .addSubMenu(Color.parseColor("#CDCDCD"), R.drawable.look_up)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        //Toast.makeText(getApplicationContext(), "Options= " + names[index], Toast.LENGTH_SHORT).show();
                        Fragment selected=null;
                        switch (index){
                            case 0: //Recommended Events
                                break;
                            case 1: //Standings
                                //startActivity(new Intent(HomePage.this, Leagues.class));
                                selected = new Leagues();
                                break;
                            case 2: //Teams
                                startActivity(new Intent(HomePage.this, StandingPT.class));
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), "Invalid option", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_navbar, selected).commit();
                    }
                });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navbarListener = item -> {
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
    };


}