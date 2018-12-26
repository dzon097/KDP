package rmi.saving_account;

import java.rmi.*;

public interface Bank extends Remote {
	
	public UserAccount getUserAccount(String name)throws RemoteException;

}
