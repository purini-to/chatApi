package models.utils;

import play.data.validation.ValidationError;

import java.util.List;

/**
 * バリデーション可能なエンティティを示すインターフェース
 * Created by N12005 on 2014/12/15.
 */
public interface ValidationAware {
    /**
     * バリデーション
     *
     * @return バリデーションのエラー
     */
    public default List<ValidationError> validate() {
        return null;
    }
}
