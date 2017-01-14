package com.grability.rappiitunescatalogo.model.db.tables;

import com.grability.rappiitunescatalogo.model.db.AppsCatalogDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by wilmer on 14/01/17.
 */
@Table(database = AppsCatalogDatabase.class)
public class Category extends BaseModel {
    @PrimaryKey
    private int id;

    @Column
    private String term;

    @Column
    private String scheme;

    @Column
    private String label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", term='" + term + '\'' +
                ", scheme='" + scheme + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
