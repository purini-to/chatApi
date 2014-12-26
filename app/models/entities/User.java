package models.entities;

import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by N12005 on 2014/12/11.
 */
@Entity
public class User extends BaseModel {
    @Id
    public Long id;
    @NotNull
    public String name;
    public String mail;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                "} " + super.toString();
    }
}
