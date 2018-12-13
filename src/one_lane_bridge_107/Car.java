package one_lane_bridge_107;

public abstract class Car {
	private int id;
	BridgeA bridge;
	
	public Car(int id, BridgeA bridge) {
		this.id=id;
		this.bridge=bridge;
	}
	
	public void crossing() {
		for(int i=0;i<50; i++);
		System.out.println("Car ID: "+ id+ " crossing ridge");

	}
	
	public void starting() {
		for(int i=0;i<500; i++);
		
	}
	
	public abstract void start();

}
