package com.qy.sp.manage.common.utils;

/**
 * Created by xtGodLike on 2017/5/25.
 */
import java.awt.Color;
import java.awt.Font;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.LineTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;


public class ImageCaptchaEngineExtend extends ListImageCaptchaEngine{

    @Override
    protected void buildInitialFactories() {
        int num = 5;//默认为5个

        //设置内容
        WordGenerator wgen =  new RandomWordGenerator("abcdefghijklmnopqrstuvwxyz123456789");
        //设置颜色
        RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(
                new int[] { 100, 255 }, new int[] { 100, 255 },
                new int[] { 100, 255 });

        RandomRangeColorGenerator cgen1 = new RandomRangeColorGenerator(
                new int[] { 0, 100 }, new int[] { 0, 100 },
                new int[] { 0, 100 });


        //设置背景色
        BackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(new Integer(80), new Integer(37),Color.WHITE);
        //设置字体
        Font[] fontsList = new Font[] { new Font("Arial", 0, 12),
                new Font("Tahoma", 0, 12), new Font("Verdana", 0, 12), };
        FontGenerator fontGenerator = new RandomFontGenerator(new Integer(26),
                new Integer(26), fontsList);
//        BaffleTextDecorator baffleTextDecorator = new BaffleTextDecorator(1,cgen1);//气泡干扰
        LineTextDecorator lineTextDecorator = new LineTextDecorator(0,cgen1);//曲线干扰
        TextDecorator[] textDecorators = new TextDecorator[1];
//        textDecorators[0] = baffleTextDecorator;
        textDecorators[0] = lineTextDecorator;
        TextPaster textPaster = new DecoratedRandomTextPaster(new Integer(num),new Integer(num), cgen, true,textDecorators);
//        TextPaster textPaster = new DecoratedRandomTextPaster(new Integer(num),new Integer(num), cgen, true,
//                new TextDecorator[] { new BaffleTextDecorator(new Integer(1), Color.WHITE) });
        WordToImage wordToImage = new ComposedWordToImage(fontGenerator,backgroundGenerator, textPaster);
        this.addFactory(new GimpyFactory(wgen, wordToImage));

    }

}
