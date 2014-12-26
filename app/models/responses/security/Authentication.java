package models.responses.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.entities.UserToken;

/**
 * Created by N12005 on 2014/12/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Authentication {
    public String name;
    public String mail;
    public String token;

    public Authentication(UserToken userToken) {
        this.name = userToken.user.name;
        this.mail = userToken.user.mail;
        this.token = userToken.token;
    }
}
