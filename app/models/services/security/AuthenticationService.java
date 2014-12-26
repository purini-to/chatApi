package models.services.security;

import models.entities.Authentication;
import models.entities.IssuedServiceUser;
import models.entities.UserToken;
import models.entities.repositories.AuthenticationRepository;
import models.entities.repositories.IssuedServiceUserRepository;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by N12005 on 2014/12/11.
 */
public class AuthenticationService {
    public static AuthenticationService use() {

        return new AuthenticationService();
    }

    /**
     * ユーザートークンエンティティを元にレスポンス用クラスに生成します
     *
     * @param userToken
     * @return
     */
    public models.responses.security.Authentication createResponseAuthentication(UserToken userToken) {

        return new models.responses.security.Authentication(userToken);
    }

    /**
     * API_KEYが正しいかチェックします
     *
     * @param apiKey
     * @return
     */
    public boolean issuedServiceUser(String apiKey) {
        IssuedServiceUser serviceUser = IssuedServiceUserRepository.use().findServiceUserByApiKey(apiKey);

        return (serviceUser != null);
    }

    /**
     * IDから認証情報を取得します
     *
     * @param loginId
     * @return
     */
    public Authentication findAuthUser(String loginId) {
        return AuthenticationRepository.use().findByLoginId(loginId);
    }

    /**
     * ログイン認証チェックを行います
     *
     * @param auth
     * @param password
     * @return
     */
    public boolean authentication(Authentication auth, String password) {
        if (auth == null) {

            return false;
        }

        return equalsPassword(auth, password);
    }

    /**
     * パスワードのソルトから等しいかチェックします
     *
     * @param auth
     * @param password
     * @return
     */
    public boolean equalsPassword(Authentication auth, String password) {

        return BCrypt.checkpw(password, auth.password);
    }
}
