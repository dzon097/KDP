package net.klijent_server;

public class TextMessage implements Message<String> {

	private static final long serialVersionUID = 1L;
	
	static long id=0;
	long messageID;
	String text;
	
	public TextMessage(String s) {
		messageID = createID();
		text=s;
	}
	
	private static synchronized long createID() {
		return id++;
	}

	public TextMessage(TextMessage m) {
		this(m.getData());
	}

	@Override
	public void setData(String data) {
		text=(String) data;
	}

	@Override
	public String getData() {
		return text;
	}

	@Override
	public void setMessageID(long messageID) {
		this.messageID = messageID;
	}

	@Override
	public long getMessadeID() {
		return messageID;
	}

}
