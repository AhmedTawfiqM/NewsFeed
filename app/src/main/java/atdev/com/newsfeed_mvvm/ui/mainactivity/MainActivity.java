package atdev.com.newsfeed_mvvm.ui.mainactivity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import atdev.com.newsfeed_mvvm.R;
import atdev.com.newsfeed_mvvm.adapters.ViewPagerAdapter;
import atdev.com.newsfeed_mvvm.databinding.ActivityMainBinding;
import atdev.com.newsfeed_mvvm.ui.koktilfragment.koktilFragment;
import atdev.com.newsfeed_mvvm.ui.newsfragment.NewsFragment;
import atdev.com.newsfeed_mvvm.ui.profilefragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        setupViewPager();
    }

    private void setupViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment("Feeds", new NewsFragment());
        adapter.AddFragment("Koktil", new koktilFragment());
        adapter.AddFragment("Profile", new ProfileFragment());

        binding.viewpager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewpager);

        binding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
    }
}
