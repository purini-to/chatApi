package models.responses.usermanage;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.entities.User;

/**
 * Created by N12005 on 2014/12/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreate {
    public String name;
    public String mail;

    public UserCreate(User user) {
        this.name = user.name;
        this.mail = user.mail;
    }
}
