package models.entities;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by N12005 on 2014/12/11.
 */
@Entity
public class IssuedServiceUser extends BaseModel {
    @Id
    public Long id;
    @NotNull
    public String serviceName;
    @NotNull
    public String apiKey;

    @Override
    public String toString() {
        return "IssuedServiceUser{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", apiKey='" + apiKey + '\'' +
                "} " + super.toString();
    }
}
