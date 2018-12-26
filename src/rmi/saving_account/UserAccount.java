package rmi.saving_account;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Interfejsi na serverskoj strani treba da prosiruju interfejs Remote kako bi OS znao kako da se postupa sa njima pri pozivima udaljenih metoda
//svaka metoda interfejsa se deklarise da baca izuzetak RmoteException koji moze nastsati u razim situacijama problema sa konekcijom i drugih ...

public interface UserAccount extends Remote {

	public float getStatus() throws RemoteException;

	public void trensaction(float value) throws RemoteException;

}
