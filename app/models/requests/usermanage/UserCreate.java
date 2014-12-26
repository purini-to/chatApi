package models.requests.usermanage;

import models.entities.Authentication;
import models.services.security.AuthenticationService;
import models.utils.ValidationAware;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N12005 on 2014/12/16.
 */
public class UserCreate implements ValidationAware {
    @Constraints.Required
    public String name;
    public String mail;
    @Constraints.Required
    public String loginId;
    @Constraints.Required
    public String password;

    @Override
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();

        Authentication auth = AuthenticationService.use().findAuthUser(loginId);
        if (auth != null) {
            errors.add(new ValidationError("loginId", "error.user.create.exsist.loginId"));
            return errors;
        }

        return null;
    }

    @Override
    public String toString() {
        return "UserCreate{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
