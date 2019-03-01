package cn.ldbz.mail;

import java.security.GeneralSecurityException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.sun.mail.util.MailSSLSocketFactory;

@Configuration
public class MailConfig {

    private static final Logger logger = LoggerFactory.getLogger(MailConfig.class);

	@Value("${mail.is.ssl}")
	private boolean EMAIL_SSL;

	@Value("${mail.host}")
	private String EMAIL_HOST;
	
	@Value("${mail.smtp.port}")
	private int EMAIL_PORT;
	@Value("${mail.smtp.auth}")
	private String EMAIL_AUTH;
	@Value("${mail.smtp.timeout}")
	private String EMAIL_TIMEOUT;
	
	@Value("${mail.transport.protocol}")
	private String EMAIL_PROTOCOL;

	@Value("${mail.auth.name}")
	private String EMAIL_AUTHNAME;
	@Value("${mail.auth.password}")
	private String EMAIL_PASSWORD;

    
    /**
     * 监听配置项是否有修改
     */
    @ApolloConfigChangeListener
	public void onChange(ConfigChangeEvent changeEvent) {
		for (String key : changeEvent.changedKeys()) {
			ConfigChange change = changeEvent.getChange(key);
			logger.debug("Found change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
					change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType());
			switch(key) {
				case "mail.is.ssl" : 
					EMAIL_SSL = Boolean.valueOf(change.getNewValue());
				case "mail.host" : 
					EMAIL_HOST = change.getNewValue() ;
				case "mail.smtp.port" : 
					EMAIL_PORT = Integer.valueOf(change.getNewValue()) ;
				case "mail.smtp.auth" : 
					EMAIL_AUTH = change.getNewValue() ;
				case "mail.smtp.timeout" : 
					EMAIL_TIMEOUT = change.getNewValue() ;
				case "mail.transport.protocol" : 
					EMAIL_PROTOCOL = change.getNewValue() ;
				case "mail.auth.name" : 
					EMAIL_AUTHNAME = change.getNewValue() ;
				case "mail.auth.password" : 
					EMAIL_PASSWORD = change.getNewValue() ;
			}
		}
	}

	@Bean(name = "mailSender")
	public JavaMailSender getSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(EMAIL_HOST);
        javaMailSender.setPort(EMAIL_PORT);
        javaMailSender.setUsername(EMAIL_AUTHNAME);
        javaMailSender.setPassword(EMAIL_PASSWORD);
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setProtocol(EMAIL_PROTOCOL);

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", EMAIL_AUTH);
        properties.setProperty("mail.smtp.timeout", EMAIL_TIMEOUT);
        if(EMAIL_SSL){
            MailSSLSocketFactory sf = null;
            try {
                sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
                properties.put("mail.smtp.ssl.enable", "true");
                properties.put("mail.smtp.ssl.socketFactory", sf);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        javaMailSender.setJavaMailProperties(properties);
		return javaMailSender;
	}

}