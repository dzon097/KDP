package one_lane_bridge_107;

public class OneLaneBridgeTest {
	public static final int N=89;
	public static void main(String[] args) {
		int n=N;
		BridgeA bridge = new BridgeA(0, 0);
		Car[] south	= new Car[n];
		Car[] north	= new Car[n];
		
		for(int i=0; i<n; i++) {
			south[i] = new South(2*i, bridge);
			north[i] = new North(2*i+1, bridge);
		}
		
		for(int i=0; i<n; i++) {
			south[i].start();
			north[i].start();
		}
	}

}
