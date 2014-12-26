package models.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.responses.ResultApi;
import play.Logger;
import play.data.Form;
import play.libs.Json;

/**
 * Created by N12005 on 2014/12/15.
 */
public class ResponseUtils {
    public static final String CONF_KEY_RESULT_CODE_SUCCESS = "result.code.success";
    public static final String CONF_KEY_RESULT_CODE_INVALID_PARAMS = "result.code.errors.invalid.params";
    public static final String CONF_KEY_RESULT_CODE_INVALID_APIKEY = "result.code.errors.invalid.apiKey";
    public static final String CONF_KEY_RESULT_CODE_INVALID_USER = "result.code.errors.invalid.user";

    /**
     * API結果クラスをレスポンス用のJsonに変換します
     *
     * @param resultApi
     * @return
     */
    public static JsonNode convertResult2Json(ResultApi resultApi) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String a = mapper.writeValueAsString(resultApi);

        return Json.toJson(a);
    }

    /**
     * 成功時のAPI結果クラスを生成します
     *
     * @param resultCode
     * @param obj
     * @return
     */
    public static ResultApi createSuccessResult(String resultCode, Object obj) {
        ResultApi result = new ResultApi(resultCode);
        result.result = obj;

        return result;
    }

    /**
     * 成功時のAPI結果クラスを生成します
     *
     * @param obj
     * @return
     */
    public static ResultApi createSuccessResult(Object obj) {
        Logger.debug(ConfigUtils.getString(CONF_KEY_RESULT_CODE_SUCCESS));
        return createSuccessResult(ConfigUtils.getString(CONF_KEY_RESULT_CODE_SUCCESS), obj);
    }

    /**
     * エラー時のAPI結果クラスを生成します
     *
     * @param resultCode
     * @param errorNode
     * @return
     */
    public static ResultApi createErrorResult(String resultCode, JsonNode errorNode) {
        ResultApi result = new ResultApi(resultCode);
        result.errors = errorNode;

        return result;
    }

    /**
     * フォームのエラーからAPI結果クラスを生成します
     *
     * @param form
     * @param resultCode
     * @return
     */
    public static ResultApi createErrorResult(Form form, String resultCode) {
        JsonNode errorJson = form.errorsAsJson();
        Logger.debug(form.errorsAsJson().toString());

        String code = ConfigUtils.getString(resultCode);
        return createErrorResult(code, errorJson);
    }
}
