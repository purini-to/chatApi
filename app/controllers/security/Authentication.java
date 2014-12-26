package controllers.security;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.security.action.AuthenticationApiKey;
import models.services.security.AuthenticationService;
import models.utils.ResponseUtils;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

public class Authentication extends Controller {

    /**
     * ユーザー認証API
     *
     * @return
     */
    @With(AuthenticationApiKey.class)
    public static Result authentication() throws Throwable {
        Form<models.requests.security.Authentication> f = Form.form(models.requests.security.Authentication.class).bindFromRequest();
        if (f.hasErrors()) {
            JsonNode result = ResponseUtils.convertResult2Json(ResponseUtils.createErrorResult(f, ResponseUtils.CONF_KEY_RESULT_CODE_INVALID_USER));

            return badRequest(result);
        }

        models.requests.security.Authentication data = f.get();
        models.responses.security.Authentication auth = AuthenticationService.use().createResponseAuthentication(data.userToken);

        return ok(ResponseUtils.convertResult2Json(ResponseUtils.createSuccessResult(auth)));
    }
}