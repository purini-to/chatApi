package models.entities.repositories;

import play.Logger;
import play.db.ebean.Model;

/**
 * リポジトリー共通インターフェース
 * Created by N12005 on 2014/12/11.
 */
public interface Repository<T extends Model, E> {

    public Model.Finder<E, T> find();

    public T findById(E id);

    public default T save(T entity) {
        Logger.debug("Save[ " + entity.toString() + " ]");
        entity.save();

        return entity;
    }

    public default T update(T entity) {
        Logger.debug("Update[ " + entity.toString() + " ]");
        entity.update();

        return entity;
    }
}
