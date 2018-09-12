/*
 * Dian.so Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.zzg.mybatis.generator.util;

import java.util.Random;

/**
 * 随机码生成器,生成验证码,密码盐值
 * 可生成数字、大写、小写字母及三者混合类型的验证码
 *
 * @author ${baizhang}
 * @version $Id: MD5Encryption.java, v 0.1 2017-11-29 下午2:27 Exp $
 */
@SuppressWarnings("unused")
public class RadomCodeUtil {
    /**
     * 类型为仅数字,即0-9
     */
    public static final int TYPE_NUM_ONLY = 0;

    /**
     * 类型为仅字母,即大小写字母混合
     */
    public static final int TYPE_LETTER_ONLY = 1;

    /**
     * 类型为数字和大小写字母混合
     */
    public static final int TYPE_ALL_MIXED = 2;

    /**
     * 类型为数字和大写字母混合
     */
    public static final int TYPE_NUM_UPPER = 3;

    /**
     * 类型为数字和小写字母混合
     */
    public static final int TYPE_NUM_LOWER = 4;

    /**
     * 类型为仅大写字母
     */
    public static final int TYPE_UPPER_ONLY = 5;

    /**
     * 类型为仅小写字母
     */
    public static final int TYPE_LOWER_ONLY = 6;

    private RadomCodeUtil() {}

    /**
     *  * 生成验证码字符串
     * @param type          验证码类型,参见本类的静态属性
     * @param length        验证码长度,要求大于0的整数
     * @param excludeString 需排除的特殊字符（无需排除则为null）
     * @return
     */
    public static String generateTextCode(int type, int length, String excludeString) {
        if (length <= 0) {
            return "";
        }
        StringBuilder verifyCode = new StringBuilder();
        int i = 0;
        Random random = new Random();
        switch (type) {
            case TYPE_NUM_ONLY:
                while (i < length) {
                    int t = random.nextInt(10);
                    //排除特殊字符
                    if (null == excludeString || excludeString.indexOf(t + "") < 0) {
                        verifyCode.append(t);
                        i++;
                    }
                }
                break;
            case TYPE_LETTER_ONLY:
                while (i < length) {
                    int t = random.nextInt(123);
                    boolean r = (t >= 97 || (t >= 65 && t <= 90)) && (null == excludeString || excludeString.indexOf((char) t) < 0);
                    if (r) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_ALL_MIXED:
                while (i < length) {
                    int t = random.nextInt(123);
                    boolean r = (t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57))
                            && (null == excludeString || excludeString.indexOf((char) t) < 0);
                    if (r) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_NUM_UPPER:
                while (i < length) {
                    int t = random.nextInt(91);
                    boolean r = (t >= 65 || (t >= 48 && t <= 57))
                            && (null == excludeString || excludeString.indexOf((char) t) < 0);
                    if (r) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_NUM_LOWER:
                while (i < length) {
                    int t = random.nextInt(123);
                    boolean r = (t >= 97 || (t >= 48 && t <= 57))
                            && (null == excludeString || excludeString.indexOf((char) t) < 0);
                    if (r) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_UPPER_ONLY:
                while (i < length) {
                    int t = random.nextInt(91);
                    boolean r = (t >= 65) && (null == excludeString || excludeString.indexOf((char) t) < 0);
                    if (r) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            case TYPE_LOWER_ONLY:
                while (i < length) {
                    int t = random.nextInt(123);
                    boolean r = (t >= 97) && (null == excludeString || excludeString.indexOf((char) t) < 0);
                    if (r) {
                        verifyCode.append((char) t);
                        i++;
                    }
                }
                break;
            default:
                break;
        }
        return verifyCode.toString();
    }

}
