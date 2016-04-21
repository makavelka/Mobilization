package com.example.mobilization.view;

/**
 * Базовый интерфейс для слоя View, имеющий 2 метода:
 * getContext() - получить контекст View
 * showToast(String message) - показать информационное сообщение
 */
public interface IView {
    void showToast(String message);
}
