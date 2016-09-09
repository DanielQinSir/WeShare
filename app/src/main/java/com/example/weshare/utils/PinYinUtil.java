package com.example.weshare.utils;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-08
 * Time: 11:42
 * For:
 */

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil
{

    /**
     * 获取汉字串拼音首字母，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String getAllFirstSpell(String chinese)
    {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[ i ] > 128)
            {
                try
                {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[ i ], defaultFormat);
                    if (temp != null)
                    {
                        pybf.append(temp[ 0 ].charAt(0));
                    }
                }
                catch (BadHanyuPinyinOutputFormatCombination e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                pybf.append(arr[ i ]);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }

    /**
     * 获取汉字串首个汉字的拼音首字母，英文返回#
     *
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String getFirstSpell(String chinese)
    {
        String result = null;
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
                try
                {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[0], defaultFormat);
                    if (temp == null)
                    {
                        result =  "#";
                    }
                    else
                    {
                        result = temp[ 0 ].charAt(0) + "";
                    }
                }
                catch (BadHanyuPinyinOutputFormatCombination e)
                {
                    e.printStackTrace();
                }
        return result;
    }

    /**
     * 获取汉字串拼音，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音
     */
    public static String getFullSpell(String chinese)
    {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[ i ] > 128)
            {
                try
                {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[ i ], defaultFormat)[ 0 ]);
                }
                catch (BadHanyuPinyinOutputFormatCombination e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                pybf.append(arr[ i ]);
            }
        }
        return pybf.toString();
    }
}