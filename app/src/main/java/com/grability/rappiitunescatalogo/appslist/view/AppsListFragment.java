package com.grability.rappiitunescatalogo.appslist.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grability.rappiitunescatalogo.R;
import com.grability.rappiitunescatalogo.RappiItunesApp;
import com.grability.rappiitunescatalogo.appslist.AppsListPresenter;
import com.grability.rappiitunescatalogo.appslist.view.adapter.AppsListAdapter;
import com.grability.rappiitunescatalogo.appslist.view.adapter.OnAppListItemClickListener;
import com.grability.rappiitunescatalogo.model.db.tables.App;
import com.grability.rappiitunescatalogo.model.db.tables.Category;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppsListFragment extends Fragment implements AppsListView, OnAppListItemClickListener {

    public static final int DEFAULT_LIMIT_APP_LIST = 20;
    public static final int FILTER_NO_CATEGORY = 0;
    public static final int SCREEN_SIZE_PHONE = 1;
    public static final int SCREEN_SIZE_TABLET = 2;

    private static final String ARG_SCREEN_SIZE = "screen_size";
    private static final String ARG_LIMIT = "limit";
    private static final String ARG_CATEGORY = "category";
    @BindView(R.id.list_apps)
    RecyclerView listApps;
    @Inject
    AppsListAdapter adapter;
    @Inject
    AppsListPresenter presenter;
    private int pScreenSize;
    private int pLimit;
    private int pCategory;
    private OnFragmentInteractionListener mListener;

    public AppsListFragment() {

    }

    public static AppsListFragment newInstance(int pScreenSize, int pLimit, int pCategory) {
        AppsListFragment fragment = new AppsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCREEN_SIZE, pScreenSize);
        args.putInt(ARG_LIMIT, pLimit);
        args.putInt(ARG_CATEGORY, pCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pScreenSize = SCREEN_SIZE_PHONE;
        pLimit = DEFAULT_LIMIT_APP_LIST;
        pCategory = FILTER_NO_CATEGORY;
        if (getArguments() != null) {
            pScreenSize = getArguments().getInt(ARG_SCREEN_SIZE);
            pLimit = getArguments().getInt(ARG_LIMIT);
            pCategory = getArguments().getInt(ARG_CATEGORY);
        }
        setupInjection();
        presenter.onCreate();
        presenter.getApps(pCategory, "");
    }

    private void setupInjection() {
        RappiItunesApp app = (RappiItunesApp) getActivity().getApplication();
        app.getAppsListComponent(this, this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_list, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView(pScreenSize);
        return view;
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    private void setupRecyclerView(int screenSize) {
        if (screenSize == 1) {
            listApps.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            listApps.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.HORIZONTAL, false));
        }
        listApps.setAdapter(adapter);
        listApps.setHasFixedSize(true);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    // TODO: 18/01/17 Definir como hacer el filtrado desde la pantalla principal, una vez haya datos en el adapter (o en el repo, con el array ya cargado)
    // TODO: 18/01/17 En tablets se invoca el CircleReveal, pero no aparece el boton para realizar la busqueda
// TODO: 18/01/17 Pantalla de detalles
// TODO: 18/01/17 Bloquear orientacion portrait en smartphone.
// TODO: 18/01/17 NavDrawer Categorias
// TODO: 18/01/17 Fragment y Activity Detalles
// TODO: 18/01/17 Animaciones
    @Override
    public void searchCatalog(int category_code, String app_name) {
        presenter.getApps(category_code, app_name);
    }

    @Override
    public void setAppList(List<App> appList) {
        adapter.setApps(appList);
    }

    @Override
    public void getCategories() {

    }

    @Override
    public void onCategoriesReaded(List<Category> categories) {

    }

    @Override
    public void onError(String errorMsg) {
        if (mListener != null) {
            mListener.onFragmentError(errorMsg);
        }
    }

    @Override
    public void onItemClick(App app) {
        if (mListener != null) {
            mListener.onSelectedApp(app);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentError(String errorMsg);
        void onSelectedApp(App app);

        void onCategoriesReaded(List<Category> categories);
    }
}
