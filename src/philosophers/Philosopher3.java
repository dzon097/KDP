package philosophers;

public class Philosopher3 extends Thread {
	private int id;
	private Forks forks;
	
	public Philosopher3(int id, Forks forks) {
		this.id=id; this.forks=forks;
	}
	
	public void run() {
		while(true) {
			think();
			forks.pickup(id);
			eat();
			forks.putdown(id);
		}
	}
	private void think() {
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void eat() {
		try {
			sleep(400);
			System.out.println("Eating Philosopher ID: "+ id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
