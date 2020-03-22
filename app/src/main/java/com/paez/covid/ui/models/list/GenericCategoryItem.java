package com.paez.covid.ui.models.list;

public interface GenericCategoryItem<T> extends GenericItem<T> {

    String getCategoryName();

    int compareTo(GenericCategoryItem var1);
}
