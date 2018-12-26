package rmi.saving_account;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.Scanner;

public class BankClient {

	public static String host = "localhost";
	public static int port = 4001;
	
	public static void main(String[] args) {
		try {
			
			Bank bank = null;
			UserAccount account =null;
			String name = args[0];
			
			if(System.getSecurityManager()==null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			Registry registry = LocateRegistry.getRegistry(host, port);
			Object remote = registry.lookup("/Bank");
			bank = (Bank) remote;
			System.out.println("Nadjena banka na serveru: " + host + ":"+port);
			
			account = bank.getUserAccount(name);
			System.out.println("Nadjeni podaci za "+ name);
			
			
			try(Scanner sc = new Scanner(System.in);){
				for(int m=0; m<10; m++) {
					System.out.print("Unesite iznos: ");
					float value = sc.nextFloat();
					System.out.println("Status: "+ account.getStatus());
					account.trensaction(value);
					System.out.println("Novi status: "+ account.getStatus());
				}
			}
			
			
		}catch (RemoteException er) {} 
		catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

}
