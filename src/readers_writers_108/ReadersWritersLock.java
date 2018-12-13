package readers_writers_108;

import java.util.concurrent.locks.*;

//koriscenje gotove klase koja resava problem readersWriters !!
public class ReadersWritersLock implements ReadersWriters {
	ReadWriteLock rw;
	Lock readLock, writeLock;

	public ReadersWritersLock() {
		// rw = new ReentrantReadWriteLock(true); za posteno resenje
		rw = new ReentrantReadWriteLock();
		readLock = rw.readLock();
		writeLock = rw.writeLock();
	}

	@Override
	public void startRead() {
		readLock.lock();

	}

	@Override
	public void endRead() {
		readLock.unlock();
	}

	@Override
	public void startWrite() {
		writeLock.lock();
	}

	@Override
	public void endWrite() {
		writeLock.unlock();
	}

}
