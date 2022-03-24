package com.jiankun.community;

import com.jiankun.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail() {
        mailClient.sendMail("jiankun_qiu@163.com", "Test", "Hello world");
    }

    @Test
    public void testHTMLMail() {
        Context context = new Context();
        context.setVariable("username", "Mark");

        String content = templateEngine.process("/mail/demo", context);
        mailClient.sendMail("jiankun_qiu@163.com", "HTML", content);

    }
}
