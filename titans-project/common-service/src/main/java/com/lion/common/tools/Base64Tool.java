package com.lion.common.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64Tool {
    /**
     * 编码
     *
     * @param content
     * @return
     */
    public static String encode(String content) {
        return new sun.misc.BASE64Encoder().encode(content.getBytes());
    }

    /**
     * 解码
     *
     * @param source
     * @return
     */
    public static String decode(String source) {
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            return new String(decoder.decodeBuffer(source));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * html 加密 header :  Authorization Basic cGlnOnBpZw==
     * @param args
     */
    public static void main(String[] args) {

//        String code = "cGlnOnBpZw==";
        String code = "bGlvbjoxMjM0NTY==";
        String bytyArr  = Base64Tool.decode(code);
        System.out.println(bytyArr);
        String lionStr = Base64Tool.encode("lion:lion");
        System.out.println(lionStr);
    }
}
