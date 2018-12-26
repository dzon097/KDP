package rmi.game_of_life;

import java.awt.List;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class MsgBoxImpl implements MsgBox {
	
	private transient LinkedList<Msg> buffer;
	
	public MsgBoxImpl() {
		buffer = new LinkedList<Msg>();
	}

	@Override
	public Msg get() throws RemoteException {
		synchronized(this) {
			while(buffer.size()==0)
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			return buffer.remove(0);
		}
	}

	@Override
	public void put(Msg m) throws RemoteException {
		synchronized(this) {
			buffer.push(m);
		}

	}

}
