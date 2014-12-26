package models.entities.repositories;

import models.entities.IssuedServiceUser;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by N12005 on 2014/12/11.
 */
public class IssuedServiceUserRepository implements Repository<IssuedServiceUser, Long> {
    public static IssuedServiceUserRepository use() {
        return new IssuedServiceUserRepository();
    }

    /**
     * API_KEYから実行可能サービス情報を取得します
     *
     * @param apiKey
     * @return
     */
    public IssuedServiceUser findServiceUserByApiKey(String apiKey) {
        return find().where().eq("apiKey", apiKey).findUnique();
    }

    @Override
    public Model.Finder<Long, IssuedServiceUser> find() {
        return new Model.Finder<Long, IssuedServiceUser>(Long.class, IssuedServiceUser.class);
    }

    @Override
    public IssuedServiceUser findById(Long id) {
        return find().where().eq("id", id).findUnique();
    }
}
