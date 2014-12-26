package models.entities;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by N12005 on 2014/12/11.
 */
@Entity
public class UserToken extends BaseModel {
    @Id
    public Long id;
    @NotNull
    public String token;
    @NotNull
    public Date validityPeriod;

    @OneToOne(cascade = CascadeType.ALL)
    public User user;

    @Override
    public String toString() {
        return "UserToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", validityPeriod=" + validityPeriod +
                ", user=" + user +
                "} " + super.toString();
    }
}
