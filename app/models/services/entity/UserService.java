package models.services.entity;

import models.entities.User;
import models.entities.UserToken;
import models.entities.repositories.UserRepository;
import models.entities.repositories.UserTokenRepository;
import models.requests.usermanage.UserCreate;
import models.utils.ConfigUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

/**
 * Created by N12005 on 2014/12/15.
 */
public class UserService {

    public static UserService use() {

        return new UserService();
    }

    /**
     * ユーザー登録リクエストからユーザー情報を生成します
     *
     * @param userCreate
     * @return
     */
    public User createUser(UserCreate userCreate) {
        User user = new User();
        user.name = userCreate.name;
        user.mail = userCreate.mail;

        return UserRepository.use().save(user);
    }

}
