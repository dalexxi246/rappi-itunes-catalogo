package com.grability.rappiitunescatalogo.model.db.tables;

import com.grability.rappiitunescatalogo.model.db.AppsCatalogDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by wilmer on 14/01/17.
 */
@Table(database = AppsCatalogDatabase.class)
public class AppImage extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String source;

    @Column
    private int height;

    @Column
    @ForeignKey(stubbedRelationship = true)
    private App app;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    @Override
    public String toString() {
        return "AppImage{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", height=" + height +
                ", app=" + app +
                '}';
    }
}
