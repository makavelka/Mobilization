package com.example.mobilization.view;

import android.content.Context;

/**
 * Базовый интерфейс для слоя View, имеющий 2 метода:
 * getContext() - получить контекст View
 * showToast(String message) - показать информационное сообщение
 */
public interface IView {
    Context getContext();
    void showToast(String message);
}
