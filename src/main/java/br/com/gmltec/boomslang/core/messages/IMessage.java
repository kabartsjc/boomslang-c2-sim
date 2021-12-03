package br.com.gmltec.boomslang.core.messages;

import java.io.Serializable;

public abstract class IMessage implements Serializable {
	
	public abstract String toString();
	public  abstract IMessage parse(String imsgStr);

}
