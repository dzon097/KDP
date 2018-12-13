package readers_writers_108;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ReadersWritersStemp implements ReadersWriters {
	private final StampedLock sl;
	private Map<Thread, Long> stamps;

	// private ThreadLocal<Long> stamps;
	// opcija sa tim ima malo bolje performanse
	public ReadersWritersStemp() {
		sl = new StampedLock();
		stamps = new ConcurrentHashMap<>();
	}

	@Override
	public void startRead() {
		long stamp = sl.readLock();
		stamps.put(Thread.currentThread(), stamp);

	}

	@Override
	public void endRead() {
		long stamp = stamps.remove(Thread.currentThread());
		sl.unlock(stamp);
	}

	@Override
	public void startWrite() {
		long stamp = sl.writeLock();
		stamps.put(Thread.currentThread(), stamp);
	}

	@Override
	public void endWrite() {
		long stamp = stamps.remove(Thread.currentThread());
		sl.unlock(stamp);

	}

}
