package models.requests.security;

import models.entities.UserToken;
import models.services.security.AuthenticationService;
import models.services.entity.UserTokenService;
import models.utils.ValidationAware;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N12005 on 2014/12/11.
 */
public class Authentication implements ValidationAware {
    @Constraints.Required
    public String loginId;
    @Constraints.Required
    public String password;

    public UserToken userToken;

    @Override
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();

        models.entities.Authentication auth = AuthenticationService.use().findAuthUser(loginId);
        if (!AuthenticationService.use().authentication(auth, password)) {
            // ログイン認証を行い不正なユーザー
            errors.add(new ValidationError("loginId", "error.auth.invalid.user"));
            errors.add(new ValidationError("password", "error.auth.invalid.user"));

            return errors;
        }

        userToken = UserTokenService.use().createUserToken(auth.user);

        return null;
    }

    @Override
    public String toString() {

        return "Authentication{" +
                "loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                "}";
    }
}