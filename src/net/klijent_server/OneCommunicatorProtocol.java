package net.klijent_server;

public abstract class OneCommunicatorProtocol implements Protocol {

	protected Communicator c;
	
	public OneCommunicatorProtocol(Communicator c) {
		this.c=c;
	}
	
	public OneCommunicatorProtocol() {
		c=null;
	}
	
	@Override
	public void addComunicator(Communicator communicator) {
		c=communicator;
	}

	@Override
	public abstract  void conversation();
	
	@Override
	public boolean endConversation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean startConversation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCommunicator(Communicator comunicator) {
		if(this.c.equals(c)) {
			c=null;
			return true;
		}
		return false;
	}

	@Override
	public Communicator getComunicator(int id) {
		return c;
	}

}
