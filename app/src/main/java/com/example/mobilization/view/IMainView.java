package com.example.mobilization.view;

import com.example.mobilization.model.data.Artist;

import java.util.List;

public interface IMainView extends IView {
    void showList(List<Artist> artistListList);
    void showError(String error);
    void showEmptyList();
}
