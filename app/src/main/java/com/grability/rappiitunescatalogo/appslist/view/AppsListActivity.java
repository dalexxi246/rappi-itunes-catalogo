package com.grability.rappiitunescatalogo.appslist.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.grability.rappiitunescatalogo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppsListActivity extends AppCompatActivity {

    @BindView(R.id.txt_title_term)
    TextView txtTitleTerm;
    @BindView(R.id.appbar)
    Toolbar appbar;
    @BindView(R.id.container_list)
    FrameLayout containerList;
    @BindView(R.id.container_details)
    FrameLayout containerDetails;
    @BindView(R.id.btn_search)
    FloatingActionButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_search)
    public void onClick() {
    }
}
