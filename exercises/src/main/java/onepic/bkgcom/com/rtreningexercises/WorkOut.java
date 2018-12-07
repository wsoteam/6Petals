package onepic.bkgcom.com.rtreningexercises;

import android.app.Application;

import com.orm.SugarContext;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class WorkOut extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);

        YandexMetricaConfig.Builder configBuilder = YandexMetricaConfig.newConfigBuilder(getResources()
                .getString(R.string.yandex_id));
        YandexMetrica.activate(getApplicationContext(), configBuilder.build());
        // Отслеживание активности пользователей
        YandexMetrica.enableActivityAutoTracking(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
