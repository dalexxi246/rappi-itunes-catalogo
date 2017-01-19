package com.grability.rappiitunescatalogo.appdetails.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grability.rappiitunescatalogo.R;
import com.grability.rappiitunescatalogo.RappiItunesApp;
import com.grability.rappiitunescatalogo.appdetails.AppDetailsPresenter;
import com.grability.rappiitunescatalogo.libs.base.ImageLoader;
import com.grability.rappiitunescatalogo.model.db.tables.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AppDetailsFragment extends Fragment implements AppDetailsView {

    public static final String ARG_APP_ID = "app_id";

    @BindView(R.id.img_header)
    ImageView imgHeader;
    @BindView(R.id.img_icon_image)
    ImageView imgIconImage;
    @BindView(R.id.txt_app_category)
    TextView txtAppCategory;
    @BindView(R.id.txt_app_name)
    TextView txtAppName;
    @BindView(R.id.txt_app_resume)
    TextView txtAppResume;
    @BindView(R.id.txt_app_currency)
    TextView txtAppCurrency;
    @BindView(R.id.txt_app_rights)
    TextView txtAppRights;
    @BindView(R.id.txt_app_web_link)
    TextView txtAppWebLink;
    @BindView(R.id.txt_app_release_date)
    TextView txtAppReleaseDate;

    @Inject
    AppDetailsPresenter presenter;
    @Inject
    ImageLoader imageLoader;

    private int appId;

    private OnFragmentInteractionListener mListener;

    public AppDetailsFragment() {
        // Required empty public constructor
    }

    public static AppDetailsFragment newInstance(int appId) {
        AppDetailsFragment fragment = new AppDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_APP_ID, appId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjection();
        presenter.onCreate();
        if (getArguments() != null) {
            appId = getArguments().getInt(ARG_APP_ID);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void setupInjection() {
        RappiItunesApp app = (RappiItunesApp) getActivity().getApplication();
        app.getAppDetailsComponent(this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_detail, container, false);
        ButterKnife.bind(this, view);
        if (appId > 0) {
            getAppDetails(appId);
        }
        return view;
    }

    @Override
    public void getAppDetails(int appId) {
        presenter.getAppInfo(appId);
    }

    @Override
    public void onAppLoaded(App app) {
        app.getCategory().load();
        app.getArtist().load();
        txtAppCategory.setText(app.getCategory().getLabel());
        txtAppCurrency.setText(app.getCurrency() + " " + app.getCurrencyType());
        txtAppName.setText(app.getName());
        txtAppReleaseDate.setText(app.getReleaseDate());
        txtAppResume.setText(app.getSummary());
        txtAppRights.setText(app.getRights());
        txtAppWebLink.setText(app.getWeb_link());
        imageLoader.load(imgIconImage, app.getMain_image());
    }

    @Override
    public void onError(String errorMsg) {
        if (mListener == null) {
            mListener.onFragmentInteraction(Uri.EMPTY);
        } else {

        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
