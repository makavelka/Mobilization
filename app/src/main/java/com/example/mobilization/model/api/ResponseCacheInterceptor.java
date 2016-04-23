package com.example.mobilization.model.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.mobilization.R;
import com.example.mobilization.Utils;
import com.example.mobilization.di.App;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Класс, используемый для перехвата загружаемых данных и сохранения их в кэше, обращения к
 * которому будут идти при отсутствии подключения к интернету
 */
public class ResponseCacheInterceptor implements Interceptor {

    //Инъекция контекста
    @Inject
    Context mContext;

    //Инъекция контекста
    @Inject
    Utils mUtils;

    //Вызов метода inject в конструкторе класса, чтобы инъектируемые данные были доступны сразу
    public ResponseCacheInterceptor() {
        App.getComponent().inject(ResponseCacheInterceptor.this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //Обработка пришедего запроса
        Request originalRequest = chain.request();
        Request.Builder request = originalRequest.newBuilder();
        if (mUtils.isInternetConnected()) {
            // если есть подключение к интернету, обновляем данные.
            request.cacheControl(CacheControl.FORCE_NETWORK);
        } else {
            //Вывод сообщения, если подключение отсутствует
            Toast.makeText(mContext, mContext.getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }
        Response response = chain.proceed(request.build());
        return response.newBuilder()
                //Удаление заголовков, отвечающих за кэш
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                //Свой заголовок для кэшированных данных
                .header("Cache-Control", cacheControl())
                .build();
    }

    @NonNull
    private String cacheControl() {
        String cacheHeaderValue;
        // выбираем значение http заголовка "Cache-Control" в зависимости от наличия интернета.
        if (mUtils.isInternetConnected()) {
            cacheHeaderValue = "public, max-age=2419200";   // 4 недели
        } else {
            cacheHeaderValue = "public, only-if-cached, max-stale=2419200";   // 4 недели
        }
        return cacheHeaderValue;
    }
}
