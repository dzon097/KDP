package net.klijent_server;

public class BufferWorker extends Thread {
	Buffer<Runnable> buffer;
	
	public BufferWorker(int id, Buffer<Runnable> buffer) {
		super("BufferWorker"+id);
		this.buffer = buffer;
	}
	
	public void run() {
		while(true){
			Runnable r = buffer.get();
			r.run();
		}
	}

}
