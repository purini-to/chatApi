package models.entities.repositories;

import models.entities.User;
import play.db.ebean.Model;

/**
 * Created by N12005 on 2014/12/11.
 */
public class UserRepository implements Repository<User, Long> {
    public static UserRepository use() {
        return new UserRepository();
    }

    /**
     * メールアドレスからユーザー情報を取得します
     *
     * @param email
     * @return
     */
    public User findByMail(String email) {
        return find().where().eq("mail", email).findUnique();
    }

    @Override
    public Model.Finder<Long, User> find() {
        return new Model.Finder<Long, User>(Long.class, User.class);
    }

    @Override
    public User findById(Long id) {
        return find().where().eq("id", id).findUnique();
    }
}
