package rmi.saving_account;

import java.rmi.RemoteException;
import java.util.HashMap;

public class BankImpl implements Bank {

	private static transient HashMap<String, UserAccount> users;

	public BankImpl() {
		users = new HashMap<String, UserAccount>();
	}

	@Override
	public UserAccount getUserAccount(String name) throws RemoteException {
		synchronized (this) {
			UserAccount user = users.get(name);
			if (user != null) {
				return user;
			}
			user = new UseAccountImpl(name); // Ako nema korisnika napravi ga
			users.put(name, user);
			return user;
		}
	}

}
