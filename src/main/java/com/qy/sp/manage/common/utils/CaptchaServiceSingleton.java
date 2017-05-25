package com.qy.sp.manage.common.utils;

/**
 * Created by xtGodLike on 2017/5/25.
 */
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

public class CaptchaServiceSingleton {

    private static ImageCaptchaService instance =
            new DefaultManageableImageCaptchaService( new FastHashMapCaptchaStore(), new ImageCaptchaEngineExtend(),
                    180, 100000, 75000);

    public static ImageCaptchaService getInstance(){
        return instance;
    }
}
