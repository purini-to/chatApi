package models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by N12005 on 2014/12/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultApi {
    public String resultCode;
    public Object result;
    public Object errors;

    public ResultApi(String resultCode) {
        this.resultCode = resultCode;
    }
}
