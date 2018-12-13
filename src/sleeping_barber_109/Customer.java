package sleeping_barber_109;

public class Customer extends Thread {
	private BarberShop barbershop;

	private int id;

	public Customer(BarberShop barbershop, int id) {
		this.barbershop = barbershop;
		this.id = id;
	}
	
	public void run() {
		starting();
		boolean result = barbershop.getHaircut(id);
		System.out.println("Customer " + id + " was "+ result);
	}

	private void starting() {
		for(int i=0; i<50000; i++);
		
	}

}
