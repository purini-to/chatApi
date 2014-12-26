package controllers.security.action;

import com.fasterxml.jackson.databind.JsonNode;
import models.requests.security.action.ApiRequest;
import models.utils.ResponseUtils;
import play.data.Form;
import play.libs.F;
import play.mvc.*;
import play.mvc.Result;

/**
 * Created by N12005 on 2014/12/15.
 */
public class AuthenticationApiKey extends Action.Simple {

    @Override
    public F.Promise<Result> call(Http.Context context) throws Throwable {
        Form<ApiRequest> f = Form.form(ApiRequest.class).bindFromRequest();
        if (f.hasErrors()) {
            JsonNode result = ResponseUtils.convertResult2Json(ResponseUtils.createErrorResult(f, ResponseUtils.CONF_KEY_RESULT_CODE_INVALID_APIKEY));
            Result errorResult = new Authenticator().badRequest(result);

            return F.Promise.pure(errorResult);
        }

        return delegate.call(context);
    }

    public class Authenticator extends Results {
    }
}
