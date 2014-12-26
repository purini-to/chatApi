package models.requests.security.action;

import models.services.security.AuthenticationService;
import models.utils.ValidationAware;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N12005 on 2014/12/15.
 */
public class ApiRequest implements ValidationAware {
    @Constraints.Required
    public String apiKey;

    @Override
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();

        if (!AuthenticationService.use().issuedServiceUser(apiKey)) {
            // API_KEYが発行されていない不正なユーザー
            errors.add(new ValidationError("apiKey", "error.invalid.api.user"));

            return errors;
        }

        return null;
    }

    @Override
    public String toString() {

        return "ApiRequest{" +
                "apiKey='" + apiKey + '\'' +
                '}';
    }
}
