package one_lane_bridge_B_107;

public abstract class Car {
	private int id;
	Bridge bridge;
	
	public Car(int id, Bridge bridge) {
		this.id=id;
		this.bridge=bridge;
	}
	
	public void crossing() {
		for(int i=0;i<50; i++);
		System.out.println("Car ID: "+ id+ " crossing ridge");

	}
	
	public void starting() {
		for(int i=0;i<500; i++);
		System.out.println("Car ID: "+ id+ " starting ridge");
		
	}
	
	public abstract void start();

}
