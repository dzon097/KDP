package net.klijent_server;

import java.io.Serializable;

public interface Message<T> extends Serializable {
	public void setData(T data);
	public T getData();
	public void setMessageID(long messageID);
	public long getMessadeID();

}
