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
public class App extends BaseModel {

    @Column
    @PrimaryKey
    private int id;

    @Column
    private String name;

    @Column
    private String summary;

    @Column
    private String currency;

    @Column
    private String currencyType;

    @Column
    private String Rights;

    @Column
    private String title;

    @Column
    private String web_link;

    @Column
    private String id_package;

    @Column
    private String releaseDate;

    @Column
    private String releaseTimestamp;

    @Column
    private String main_image;

    @Column
    @ForeignKey(stubbedRelationship = true)
    private Artist artist;

    @Column
    @ForeignKey(stubbedRelationship = true)
    private Category category;

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getRights() {
        return Rights;
    }

    public void setRights(String rights) {
        Rights = rights;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeb_link() {
        return web_link;
    }

    public void setWeb_link(String web_link) {
        this.web_link = web_link;
    }

    public String getId_package() {
        return id_package;
    }

    public void setId_package(String id_package) {
        this.id_package = id_package;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseTimestamp() {
        return releaseTimestamp;
    }

    public void setReleaseTimestamp(String releaseTimestamp) {
        this.releaseTimestamp = releaseTimestamp;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", currency='" + currency + '\'' +
                ", currencyType='" + currencyType + '\'' +
                ", Rights='" + Rights + '\'' +
                ", title='" + title + '\'' +
                ", web_link='" + web_link + '\'' +
                ", id_package='" + id_package + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", releaseTimestamp='" + releaseTimestamp + '\'' +
                ", artist=" + artist +
                ", category=" + category +
                ", main_image='" + main_image + '\'' +
                '}';
    }
}
