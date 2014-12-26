package models.entities.repositories;

import models.entities.Authentication;
import play.db.ebean.Model;

/**
 * Created by N12005 on 2014/12/11.
 */
public class AuthenticationRepository implements Repository<Authentication, Long> {
    public static AuthenticationRepository use() {
        return new AuthenticationRepository();
    }

    /**
     * ユーザーIDから認証情報を取得します
     *
     * @param userId
     * @return
     */
    public Authentication findByLoginId(String userId) {
        return find().fetch("user").where().eq("loginId", userId).findUnique();
    }

    @Override
    public Model.Finder<Long, Authentication> find() {
        return new Model.Finder<Long, Authentication>(Long.class, Authentication.class);
    }

    @Override
    public Authentication findById(Long id) {
        return find().where().eq("id", id).findUnique();
    }
}
