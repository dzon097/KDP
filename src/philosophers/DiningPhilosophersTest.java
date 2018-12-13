package philosophers;

public class DiningPhilosophersTest {

	public static final int N = 5;
	
	public static void main(String[] args) {
		Philosopher3 philosophers[] = new Philosopher3[N];
		Forks forks = new Forks();
		for(int i=0; i<N; i++) {
			philosophers[i] = new Philosopher3(i, forks);
			philosophers[i].start();
		}
	//	for(int i=0; i<N; i++) philosophers[i].start();
	}

}
