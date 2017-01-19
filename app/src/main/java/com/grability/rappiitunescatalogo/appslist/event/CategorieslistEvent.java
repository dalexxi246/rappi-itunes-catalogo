package com.grability.rappiitunescatalogo.appslist.event;

import com.grability.rappiitunescatalogo.model.db.tables.Category;

import java.util.List;

/**
 * Created by wilmer on 15/01/17.
 */
public class CategorieslistEvent {

    public static final int READ_EVENT = 641;

    int type;
    List<Category> categories;
    String errorMsg;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
