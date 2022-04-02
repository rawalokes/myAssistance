package com.info.myassistant.utility;

import java.util.Random;

/**
 * @author rawalokes
 * Date:3/27/22
 * Time:3:51 AM
 */
public class PasswordGenerator {
    /**
     * generate a random password of 8 character
     * @return
     */
    public static String password() {
        String uppercase="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase="abcdefghijklmnopqrstuvwxyz";
        String num="0987654321";
        String specialChar="<>.,?{}[]~@#$%^&*)_(_+|/";

        String combo=uppercase+lowercase+num+specialChar;
        char [] password=new char[7];
        Random r=new Random();
        for (int i=0;i<7;i++){
            password[i]=combo.charAt(r.nextInt(combo.length()));
        }
        return new String(password);
    }
}
