package com.example.mobilization.model.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.mobilization.Utils;
import com.example.mobilization.di.App;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ResponseCacheInterceptor implements Interceptor {

    @Inject
    Context mContext;

    @Inject
    Utils mUtils;

    public ResponseCacheInterceptor() {
        App.getComponent().inject(ResponseCacheInterceptor.this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder request = originalRequest.newBuilder();
        if (mUtils.isInternetConnected()) {
            // если есть подключение к интернету, обновляем данные.
            request.cacheControl(CacheControl.FORCE_NETWORK);
        }
        Response response = chain.proceed(request.build());
        return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
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
