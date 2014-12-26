package models.entities.repositories;

import models.entities.User;
import models.entities.UserToken;
import play.db.ebean.Model;

/**
 * Created by N12005 on 2014/12/11.
 */
public class UserTokenRepository implements Repository<UserToken, Long> {
    public static UserTokenRepository use() {
        return new UserTokenRepository();
    }

    /**
     * メールアドレスからユーザー情報を取得します
     *
     * @param token
     * @return
     */
    public UserToken findByToken(String token) {
        return find().where().eq("token", token).findUnique();
    }

    @Override
    public Model.Finder<Long, UserToken> find() {
        return new Model.Finder<Long, UserToken>(Long.class, UserToken.class);
    }

    @Override
    public UserToken findById(Long id) {
        return find().where().eq("id", id).findUnique();
    }
}
