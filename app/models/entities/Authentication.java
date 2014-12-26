package models.entities;

import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by N12005 on 2014/12/11.
 */
@Entity
public class Authentication extends BaseModel {
    @Id
    public Long id;
    @NotNull
    public String loginId;
    @NotNull
    public String password;

    @OneToOne(cascade = CascadeType.ALL)
    public User user;

    @Override
    public String toString() {
        return "Authentication{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                "} " + super.toString();
    }
}
