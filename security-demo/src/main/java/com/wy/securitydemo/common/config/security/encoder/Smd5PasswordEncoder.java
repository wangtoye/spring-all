package com.wy.securitydemo.common.config.security.encoder;

import com.wy.securitydemo.utils.Smd5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : wangtoye
 * @date : 2019-10-08
 * Description:
 */
@Component
public class Smd5PasswordEncoder implements PasswordEncoder {

    private static final Logger logger = LoggerFactory.getLogger(Smd5PasswordEncoder.class);

    @Override
    public String encode(CharSequence rawPassword) {
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        try {
            return Smd5Utils.verifyPwd(encodedPassword, rawPassword.toString());
        } catch (Exception e) {
            logger.error("密码验证失败-" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
