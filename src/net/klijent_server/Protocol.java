package net.klijent_server;

public interface Protocol {

	void addComunicator(Communicator communicator);
	void conversation();
	boolean endConversation();
	boolean startConversation();
	boolean removeCommunicator(Communicator comunicator);
	Communicator getComunicator(int id);

}
