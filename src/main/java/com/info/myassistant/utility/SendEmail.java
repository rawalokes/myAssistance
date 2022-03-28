package com.info.myassistant.utility;

/**
 * @author rawalokes
 * Date:3/27/22
 * Time:4:05 AM
 */


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

/**
 * @author rawalokes
 * Date:3/4/22
 * Time:2:39 PM
 */
@Component
public class SendEmail {
    public void sendEmail(String emailAddress, String name, String password) {

        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("tryingdemo65@gmail.com", "Jethalal@#123"));
            email.setSSLOnConnect(true);
            email.setFrom("tryingdemo65@gmail.com");
            email.setMsg("Hi " + name + " ," +
                    "\n Your myAssistant password is  " + password + "" +
                    "\n");
            email.addTo(emailAddress);
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");


        }

    }

}

