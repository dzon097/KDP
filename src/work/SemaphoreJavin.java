package work;

import java.util.concurrent.Semaphore;

public class SemaphoreJavin {
	public static void main(String[] arg) {
		Semaphore s = new Semaphore(5, true);	//boolean znaci da li ce se se semafor ponasati posteno ili ne,   vrednost moze biti i <0
		
		try {
			//ovi pozivi reaguju na prekide !!
			s.acquire();		//wait na semaforu, ako je val==0, nit ce se blokirati dok vrednost ne val ne bude bar 1,  ako je val<0 samo a umanji
			
			s.acquire(3);		//ceka dok vrednost semafora ne bude bar permits (3), do tad se blokira
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		//ne reaguju na prekide !!!
		s.acquireUninterruptibly();			//isti pozivi kao gore, samo ne bacaju izuzetke i ne reaguju na prekide
		s.acquireUninterruptibly(2);
		
		//Postoje imetode koje specificira da li je operacija umanjivanja interne promenjive uspela
		boolean b =s.tryAcquire();
		b= s.tryAcquire(2);
		if(b);
		
		//metoda signal na semaforu
		s.release();
		s.release(6);
		
		
	}

}
