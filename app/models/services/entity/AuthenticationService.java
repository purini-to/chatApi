package models.services.entity;

import models.entities.Authentication;
import models.entities.User;
import models.entities.UserToken;
import models.entities.repositories.AuthenticationRepository;
import models.entities.repositories.UserTokenRepository;
import models.requests.usermanage.UserCreate;
import models.utils.ConfigUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

/**
 * Created by N12005 on 2014/12/15.
 */
public class AuthenticationService {

    public static AuthenticationService use() {

        return new AuthenticationService();
    }

    /**
     * 認証情報エンティティを生成します
     *
     * @param userCreate
     * @param user
     * @return
     */
    public Authentication createAuthentication(UserCreate userCreate, User user) {
        Authentication auth = new Authentication();
        auth.loginId = userCreate.loginId;
        auth.password = getSaltPassword(userCreate.password);
        auth.user = user;

        return AuthenticationRepository.use().save(auth);
    }

    /**
     * ハッシュ化したパスワードを取得します
     *
     * @param password
     * @return
     */
    private String getSaltPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
