package models.settings;

import com.avaje.ebean.Ebean;
import models.entities.Authentication;
import models.entities.IssuedServiceUser;
import models.entities.User;
import models.entities.repositories.AuthenticationRepository;
import models.entities.repositories.IssuedServiceUserRepository;
import models.entities.repositories.UserRepository;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Yaml;

import java.util.List;
import java.util.Map;

/**
 * 各個人のローカル環境用設定読み込みクラス
 * Created by N12005 on 2014/12/15.
 */
public class LocalGlobal extends GlobalSettings {
    @Override
    public void onStart(Application application) {
        super.onStart(application);

        insertSampleData();
    }

    /**
     * サンプルデータを設定
     */
    public void insertSampleData() {
        if (UserRepository.use().find().all().size() != 0) return;

        Map<String, List<Object>> iniData = (Map<String, List<Object>>) Yaml.load("inidata/sample-data.yml");

        // 発行済みのサービス情報登録
        List<Object> services = iniData.get("issued_service_users");
        Ebean.save(services);

        // ユーザー情報登録
        List<Object> users = iniData.get("users");
        Ebean.save(users);

        // 認証情報登録
        List<Object> login = iniData.get("authentications");
        Ebean.save(login);

        // インサートしたデータをログ出力
        List<IssuedServiceUser> allServices = IssuedServiceUserRepository.use().find().all();
        allServices.stream().forEach(s -> Logger.info(s.toString()));
        List<User> allUsers = UserRepository.use().find().all();
        allUsers.stream().forEach(u -> Logger.info(u.toString()));
        List<Authentication> allAuth = AuthenticationRepository.use().find().all();
        allAuth.stream().forEach(a -> Logger.info(a.toString()));
    }
}
