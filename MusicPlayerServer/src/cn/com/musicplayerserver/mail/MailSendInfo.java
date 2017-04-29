package cn.com.musicplayerserver.mail;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;




public class MailSendInfo{
	private String host ="smtp.163.com";
	private int port = 25;
	private String fromAddress;
	private String toAddress;
	private String userName = "sivd_musicPlayer@163.com";
	private String password ="admin123";
	private boolean validate = true;
	private String subject;
	private String content;
	private String attchFileName[];
	
	public Properties getProperties(){
		Properties p = new Properties();
		p.put("mail.smtp.host",this.host);
		p.put("mail.smtp.port",port);
		p.put("mail.transport.protocol", "smtp");
		p.put("mail.smtp.auth",validate?"true":"false");
		return p;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttchFileName() {
		return attchFileName;
	}

	public void setAttchFileName(String[] attchFileName) {
		this.attchFileName = attchFileName;
	}
}