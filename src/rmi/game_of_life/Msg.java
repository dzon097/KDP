package rmi.game_of_life;

import java.io.Serializable;

public class Msg implements Serializable {

	private static final long serialVersionUID = 1L;
	public int i,j;
	public int index;
	boolean status;
	
	public Msg(int i, int j, int index, boolean status) {
		super();
		this.i = i;
		this.j = j;
		this.index = index;
		this.status = status;
	}
	


}
