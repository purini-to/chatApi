package models.services.entity;

import models.entities.User;
import models.entities.UserToken;
import models.entities.repositories.UserTokenRepository;
import models.utils.ConfigUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

/**
 * Created by N12005 on 2014/12/15.
 */
public class UserTokenService {

    private static final int TOKEN_LENGTH = 16;//16*2=32バイト
    private static final int DEFAULT_VALIDITY_PERIOD = 3;
    private static final String CONF_KEY_VALIDITY_PERIOD = "token.validity.periodo";

    public static UserTokenService use() {

        return new UserTokenService();
    }

    /**
     * ユーザートークン情報を生成します
     *
     * @param user
     * @return
     */
    public UserToken createUserToken(User user) {
        UserToken token = new UserToken();
        token.user = user;
        token.token = createToken();
        token.validityPeriod = getValidityPeriod();

        return UserTokenRepository.use().save(token);
    }

    /**
     * セキュリティトークンを取得します
     *
     * @return
     */
    private String createToken() {
        byte token[] = new byte[TOKEN_LENGTH];
        StringBuffer buf = new StringBuffer();
        SecureRandom random = null;

        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            random.nextBytes(token);

            for (int i = 0; i < token.length; i++) {
                buf.append(String.format("%02x", token[i]));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return buf.toString();
    }

    /**
     * 有効期間の日付を取得します
     *
     * @return
     */
    private Date getValidityPeriod() {
        String validityPeriodStr = ConfigUtils.getString(CONF_KEY_VALIDITY_PERIOD);
        int validityPeriod = DEFAULT_VALIDITY_PERIOD;
        if (!StringUtils.isEmpty(validityPeriodStr) && StringUtils.isNumeric(validityPeriodStr)) {
            // 設定ファイルに正常に値が設定されていたら数値に変換する
            validityPeriod = Integer.valueOf(validityPeriodStr);
        }

        // 現在日時に設定値分の日付を加算
        Date validityDate = new Date();
        validityDate = DateUtils.addDays(validityDate, validityPeriod);

        return validityDate;
    }
}
