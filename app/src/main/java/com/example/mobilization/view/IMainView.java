package com.example.mobilization.view;

import com.example.mobilization.model.data.Artist;

import java.util.List;

/**
 * Интерфейс главного экрана.
 * showList(List<Artist> artistListList) - вывод списка на экран
 * void showError(String error) - вывод ошибки на экран
 * void showEmptyList() - показ пустого списка
 */
public interface IMainView extends IView {
    void showList(List<Artist> artistListList);
    void showError(String error);
    void showEmptyList();
}
