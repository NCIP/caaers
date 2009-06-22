package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;

import java.util.Properties;

import javax.activation.FileTypeMap;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailerStub extends CaaersJavaMailSender {
    @Override
    public void send(MimeMessage mimeMessage) throws MailException {
        // TODO Auto-generated method stub
        // super.send(mimeMessage);
    }

    public void send(MailMessage msg) throws MailException {

    }

    @Override
    public void send(MimeMessage[] mimeMessages) throws MailException {
        // TODO Auto-generated method stub
        // super.send(mimeMessages);
    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
        // TODO Auto-generated method stub
        // super.send(mimeMessagePreparator);
    }

    @Override
    public void send(MimeMessagePreparator[] arg0) throws MailException {
        // TODO Auto-generated method stub
        // super.send(arg0);
    }

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        // TODO Auto-generated method stub
        // super.send(simpleMessage);
    }

    @Override
    public void send(SimpleMailMessage[] arg0) throws MailException {
        // TODO Auto-generated method stub
        // super.send(arg0);
    }

    @Override
    public void setHost(String host) {
        // TODO Auto-generated method stub
        // super.setHost(host);
    }

    @Override
    public void setDefaultEncoding(String defaultEncoding) {
        // TODO Auto-generated method stub
        // super.setDefaultEncoding(defaultEncoding);
    }

    @Override
    public void setDefaultFileTypeMap(FileTypeMap defaultFileTypeMap) {
        // TODO Auto-generated method stub
        // super.setDefaultFileTypeMap(defaultFileTypeMap);
    }

    @Override
    public void setJavaMailProperties(Properties javaMailProperties) {
        // TODO Auto-generated method stub
        // super.setJavaMailProperties(javaMailProperties);
    }

    @Override
    public void setPassword(String password) {
        // TODO Auto-generated method stub
        // super.setPassword(password);
    }

    @Override
    public void setPort(int port) {
        // TODO Auto-generated method stub
        // super.setPort(port);
    }

    @Override
    public void setProtocol(String protocol) {
        // TODO Auto-generated method stub
        // super.setProtocol(protocol);
    }

    @Override
    public void setSession(Session session) {
        // TODO Auto-generated method stub
        // super.setSession(session);
    }

    @Override
    public void setUsername(String username) {
        // TODO Auto-generated method stub
        // super.setUsername(username);
    }
    
    @Override
    public void sendMail(String[] to, String subject, String content,
    		String[] attachmentFilePaths) {
    	super.sendMail(to, subject, content, attachmentFilePaths);
    }

}
