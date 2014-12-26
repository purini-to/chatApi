package controllers.usermanage;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.security.action.AuthenticationApiKey;
import models.requests.usermanage.UserCreate;
import models.services.usermanage.UserService;
import models.utils.ResponseUtils;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

public class User extends Controller {

    /**
     * ユーザー登録
     *
     * @return
     */
    @With(AuthenticationApiKey.class)
    public static Result create() throws Throwable {
        Form<UserCreate> f = Form.form(UserCreate.class).bindFromRequest();
        if (f.hasErrors()) {
            JsonNode result = ResponseUtils.convertResult2Json(ResponseUtils.createErrorResult(f, ResponseUtils.CONF_KEY_RESULT_CODE_INVALID_PARAMS));

            return badRequest(result);
        }

        UserCreate data = f.get();
        UserService.use().createUserAuthenication(data);
        models.responses.usermanage.UserCreate response = UserService.use().createResponseUserCreate(data);

        return ok(ResponseUtils.convertResult2Json(ResponseUtils.createSuccessResult(response)));
    }
}