package com.grability.rappiitunescatalogo.appdetails.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.grability.rappiitunescatalogo.R;

import butterknife.ButterKnife;

import static com.grability.rappiitunescatalogo.appdetails.view.AppDetailsFragment.ARG_APP_ID;

public class AppDetailsActivity extends AppCompatActivity {

    private int appId;
    private FragmentManager fragmentManager;
    private AppDetailsFragment appDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            appId = getIntent().getExtras().getInt(ARG_APP_ID, 0);
        }

        fragmentManager = getSupportFragmentManager();

        appDetailsFragment = AppDetailsFragment.newInstance(appId);
        fragmentManager.beginTransaction().replace(R.id.container_details, appDetailsFragment).commit();

    }
}
