package com.grability.rappiitunescatalogo.appslist.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.grability.rappiitunescatalogo.R;
import com.grability.rappiitunescatalogo.libs.base.ImageLoader;
import com.grability.rappiitunescatalogo.model.db.tables.App;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wilmer on 16/01/17.
 */

public class AppsListAdapter extends RecyclerView.Adapter<AppsListAdapter.Viewholder> {

    List<App> list;
    ImageLoader imageLoader;
    OnAppListItemClickListener clickListener;

    public AppsListAdapter(List<App> list, ImageLoader imageLoader, OnAppListItemClickListener clickListener) {
        this.list = list;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    public void setApps(List<App> appList) {
        this.list = appList;
        notifyDataSetChanged();
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_app, parent, false);
        Viewholder vh = new Viewholder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        App currentApp = list.get(position);

        holder.txtAppName.setText(currentApp.getTitle());
        holder.txtAppCategory.setText(currentApp.getCategory().getLabel());
        holder.txtAppResume.setText(currentApp.getSummary());
        // TODO: obtener imagenes y cargar la de la resolucion adecuada

        holder.setClickListener(currentApp, this.clickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_icon_image)
        ImageView imgIconImage;
        @BindView(R.id.txt_app_category)
        TextView txtAppCategory;
        @BindView(R.id.txt_app_name)
        TextView txtAppName;
        @BindView(R.id.txt_app_resume)
        TextView txtAppResume;
        @BindView(R.id.btn_expand_resume)
        ImageButton btnExpandResume;

        private View view;

        public Viewholder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void setClickListener(final App app, final OnAppListItemClickListener clickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(app);
                }
            });

            btnExpandResume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: Animacion de expandir descripcion (txtAppResume)
                }
            });
        }

    }

}

// https://github.com/ykro/android-recetas/blob/master/app/src/main/java/edu/galileo/android/facebookrecipes/recipelist/ui/adapters/RecipesAdapter.java