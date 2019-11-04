package com.wy.securitydemo.common.config.security.encoder;

import com.wy.securitydemo.utils.SMD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-10-08
 * Description:
 */
@Component
public class SMD5PasswordEncoder implements PasswordEncoder {

    private static final Logger logger = LoggerFactory.getLogger(SMD5PasswordEncoder.class);

    @Override
    public String encode(CharSequence rawPassword) {
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        try {
            return SMD5Utils.verifyPwd(encodedPassword, rawPassword.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("密码验证失败-" + e.getMessage());
        }
        return false;
    }
}
