package br.com.gmltec.boomslang.core.comm;


public interface IChannel {
	public boolean startConnection(String brokerAddr, int port, String username, String password);
	public boolean endConnection();
	

}
