package rmi.game_of_life;

import java.rmi.*;

public interface MsgBox extends Remote {
	
	public Msg get() throws RemoteException;
	
	public void put(Msg m) throws RemoteException;

}
