package com.rongyi.diamond.baselibiary.utils;

import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/28 下午7:39
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class StringHelper {
    public static boolean isEmpty(String str) {
        return str == null || str.equals("null") || str.trim().equals("");
    }

    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 获取 EditText 的内容
     *
     * @param edt EditText 控件
     * @return String内容
     */
    public static String getEditTextContent(EditText edt) {
        return edt == null ? "" : edt.getText().toString().trim();
    }

    /**
     * 是否 EditText 内容为空
     *
     * @param edt EditText控件
     * @return true 内容为空
     */
    public static boolean isEditTextEmpty(EditText edt) {
        return isEmpty(getEditTextContent(edt));
    }

    /**
     * 获取TextView 的内容
     *
     * @param tv TextView控件
     * @return String内容
     */
    public static String getTextViewContent(TextView tv) {
        return tv == null ? "" : tv.getText().toString().trim();
    }

    /**
     * 判断汉字
     *
     * @param content 内容
     * @return true 是汉字
     */
    public static boolean hasChinese(String content) {
        String regEx = "[\\u4e00-\\u9fa5]+$";   //"^[\\u2E80-\\uFE4F]+$";   //"[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content);
        return m.find();
    }

    /**
     * @param str: 需要验证的字符串
     * @return 含有表情返回true
     */
    public static boolean checkExpression(String str) {
        //匹配非表情符号的正则表达式
        String reg = "^([a-z]|[A-Z]|[0-9]|[\u2E80-\u9FFF]|[\\u4e00-\\u9fa5]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\]._<>/?~！@#￥%……&*（）\\-\\{\\}\\[\\]——+|{}【】‘；：”“’。，、？])+|@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?|[wap.]{4}|[www.]{4}|[blog.]{5}|[bbs.]{4}|[.com]{4}|[.cn]{3}|[.net]{4}|[.org]{4}|[http://]{7}|[ftp://]{6}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return !matcher.matches();
    }

    /**
     * 检查手机号码
     *
     * @param phone 手机号码
     * @return true:正确的手机号码
     */
    public static boolean isPhoneNumber(String phone) {
        return notEmpty(phone) && phone.length() == 11;
    }

    /**
     * 设置 textView 控件的值
     *
     * @param text   需要设置的值
     * @param target textView
     */
    public static void setText(String text, TextView target) {
        if (isEmpty(text)) {
            target.setText("");
        } else {
            target.setText(text);
        }
    }

    /**
     * 设置 textView 控件的值 值为空时隐藏VIEW
     *
     * @param text   需要设置的值
     * @param target textView
     */
    public static void setTextEmptyGone(String text, TextView target) {
        if (isEmpty(text)) {
            Utils.setGone(target, true);
        } else {
            target.setText(text);
            Utils.setGone(target, false);
        }
    }

    /**
     * 设置 textView 控件的值 值为空时隐藏VIEW
     *
     * @param text   需要设置的值
     * @param target textView
     */
    public static void setTextEmptyInvisible(String text, TextView target) {
        if (isEmpty(text)) {
            Utils.setInvisible(target, true);
        } else {
            target.setText(text);
            Utils.setInvisible(target, false);
        }
    }

    /**
     * replace shop name contains "(" ;
     *
     * @param resultStr 返回字符串
     * @return String
     */
    public static String replaceShopName(String resultStr) {
        return resultStr.replace("（", "").replace("(", "")
                .replace("）", "").replace(")", "");
    }

    /**
     * 字符串数组转字符串
     *
     * @param strings 需要转换的字符串数组
     * @return String
     */
    public static StringBuilder formatArrayStrings(String[] strings, int formatSize, String format) {
        StringBuilder buffer = new StringBuilder();
        if (strings != null && strings.length > 0) {
            int size = strings.length > formatSize ? formatSize : strings.length;//只取前两个标签
            for (int i = 0; i < size; i++) {
                buffer.append(strings[i]).append(format);
            }
        }
        return buffer;
    }

    public static String StringArrayToString(String[] strings) {
        StringBuffer sb = new StringBuffer();

        if (strings != null && strings.length > 0) {
            int size = strings.length > 2 ? 2 : strings.length;//只取前两个标签
            for (int i = 0; i < size; i++) {
                sb.append(strings[i]).append(" ");
            }
        }
        return sb.toString();
    }
}
