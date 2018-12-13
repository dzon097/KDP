package sleeping_barber_109;

public class Test {

	final static int NUMSEATS=5;
	final static int NUMCUST=12;
	
	public static void main(String[] args) {
		BarberShop barbershop = new BarberShop(NUMSEATS);
		Barber barber = new Barber(barbershop);
		barber.start();
		
		Customer customers[] = new Customer[NUMCUST];
		for(int i=0;i<NUMCUST;i++) {
			customers[i]= new Customer(barbershop, i);
			customers[i].start();
		}
			
	}

}
