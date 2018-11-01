package com.wsoteam.diet.appmetrica;

import android.app.Application;

import com.wsoteam.diet.Config;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class Diet extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        // Инициализация AppMetrica SDK
        YandexMetricaConfig.Builder configBuilder = YandexMetricaConfig.newConfigBuilder(Config.YANDEX_API_KEY);
        YandexMetrica.activate(getApplicationContext(), configBuilder.build());
        // Отслеживание активности пользователей
        YandexMetrica.enableActivityAutoTracking(this);
    }

}
