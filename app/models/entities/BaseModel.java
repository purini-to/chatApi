package models.entities;

import com.avaje.ebean.annotation.CreatedTimestamp;
import play.db.ebean.Model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

/**
 * Created by N12005 on 2014/12/16.
 */
@MappedSuperclass
public class BaseModel extends Model {
    public boolean deleteFlag = false;
    @CreatedTimestamp
    public Date createdDate;
    @Version
    public Date updateDate;

    @Override
    public String toString() {
        return "BaseModel{" +
                "deleteFlag=" + deleteFlag +
                ", createdDate=" + createdDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
