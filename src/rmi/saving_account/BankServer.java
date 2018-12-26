package rmi.saving_account;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class BankServer {
	
	public static String host = "localhost";
	public static int port = 4001;
	
	public static void main(String[] args) {
		try {
			if(System.getSecurityManager() == null ) {
				System.setSecurityManager(new SecurityManager());
			}
			
			Bank bank = new BankImpl();
			System.out.println("Kreiranje banke ... ");
			
			Bank stub = (Bank) UnicastRemoteObject.exportObject(bank, 0);  
			System.out.println("Exportovana je Banka ");
			
			Registry registry = LocateRegistry.createRegistry(port);
			System.out.println("Definisan Registry " + registry);
			
			String name = "/Bank";
			registry.rebind(name, stub);
			System.out.println("Banka je spremna ... ");
			
			
		}catch(RemoteException e) {
			System.out.println("Desila se greska:  "+ e);
			e.printStackTrace();
		}
	}

}
