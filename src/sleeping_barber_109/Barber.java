package sleeping_barber_109;

public class Barber extends Thread {
	private BarberShop barbershop;
	
	public Barber(BarberShop bs) {
		barbershop=bs;
	}
	
	public void run() {
		int custumer;
		while(true) {
			custumer = barbershop.getNextCustomer();
			shaving(custumer);
			barbershop.finishedCut();
		}
	}

	private void shaving(int custumer) {
		for(int i=0;i<40000;i++);
		
	}
	
}
