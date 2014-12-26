package models.services.usermanage;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.TxRunnable;
import models.entities.Authentication;
import models.entities.IssuedServiceUser;
import models.entities.User;
import models.entities.repositories.AuthenticationRepository;
import models.entities.repositories.IssuedServiceUserRepository;
import models.requests.usermanage.UserCreate;
import models.services.entity.AuthenticationService;
import org.mindrot.jbcrypt.BCrypt;
import play.Logger;

/**
 * Created by N12005 on 2014/12/11.
 */
public class UserService {
    public static UserService use() {

        return new UserService();
    }

    /**
     * ユーザー登録情報からレスポンス用の情報を生成します
     *
     * @param userCreate
     * @return
     */
    public models.responses.usermanage.UserCreate createResponseUserCreate(UserCreate userCreate) {
        Authentication auth = models.services.security.AuthenticationService.use().findAuthUser(userCreate.loginId);
        models.responses.usermanage.UserCreate result = new models.responses.usermanage.UserCreate(auth.user);

        return result;
    }

    /**
     * ユーザー情報を生成します
     *
     * @param create
     */
    public void createUserAuthenication(UserCreate create) {
        try {
            Ebean.execute(new TxRunnable() {
                @Override
                public void run() {
                    Logger.debug("-- トランザクション処理開始 ユーザー登録 --");

                    User user = models.services.entity.UserService.use().createUser(create);
                    AuthenticationService.use().createAuthentication(create, user);

                    Logger.debug("-- トランザクション処理終了 ユーザー登録 --");
                }
            });
        } catch (Exception e) {
            Logger.error(e.toString());
            Logger.error("-- トランザクション処理失敗 ユーザー登録 --");
        }
    }
}
