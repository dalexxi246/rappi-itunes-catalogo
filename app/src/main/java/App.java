import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by wilmer on 11/01/17.
 */

public class App extends Application {

    private SharedPreferences prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
        initModules();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    public Context getContext(){
        return getApplicationContext();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initModules() {
        // TODO: DI (inicializar los modulos)
//        libsModule = new LibsModule();
//        domainModule = new DomainModule();
//        weNotesAppModule = new WENotesAppModule(this);
    }
}

// TODO: http://www.jsonschema2pojo.org/ (Crear Clases a partir de JSon)
// TODO: http://www.jsoneditoronline.org/ (Consultar Endpoints de APIs y Navegar por JSon's)