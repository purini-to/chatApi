package models.utils;

import play.Play;

/**
 * Created by N12005 on 2014/12/15.
 */
public class ConfigUtils {

    /**
     * 設定ファイルから指定されたキーの値を取得します
     *
     * @param key
     * @return
     */
    public static String getString(String key) {

        return Play.application().configuration().getString(key);
    }

    /**
     * 設定ファイルから指定されたキーの値を取得します
     * 値が存在しない場合はデフォルト値を返却します
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(String key, String defaultValue) {

        return Play.application().configuration().getString(key, defaultValue);
    }
}
