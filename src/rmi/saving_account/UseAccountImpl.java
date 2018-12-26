package rmi.saving_account;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Implementacija racuna i cela problematika zadatka koji resavamo ovde to sredjujemo preko synhronized metoda !!

public class UseAccountImpl extends UnicastRemoteObject implements UserAccount, Serializable {

	private static final long serialVersionUID = 1L;

	private float status = 0;
	private String name;

	protected UseAccountImpl(String name) throws RemoteException {
		super();
		this.name = name;
	}


	private void work() { // neki posaoo!!
		for (int i = 0; i < 30000; i++);
	}

	@Override
	public float getStatus() throws RemoteException {
		synchronized (this) {
			work();
			return status;
		}
	}

	@Override
	public void trensaction(float value) throws RemoteException { // u zavisnosti od velicine value, radice uplatu ili
																	// isplatu i svaki put ako nema dovljo ce se
																	// blokirati i cekati
		synchronized (this) {
			work();
			while (status + value < 0) {
				try {
					wait();
				} catch (InterruptedException ex) {
				}
			}
			status += value;
			notifyAll();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
