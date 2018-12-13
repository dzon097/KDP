package readers_writers_108;

import java.util.concurrent.*;

public class ReadersWritersSem implements ReadersWriters {
	
	private Semaphore rw, mutexR, enter;
	private int readCount;
	
	public ReadersWritersSem() {
		rw = new Semaphore(1);
		mutexR = new Semaphore(1);
		enter = new Semaphore(1);
		readCount=0;
	}

	
	@Override
	public void startRead() {
		enter.acquireUninterruptibly();
		mutexR.acquireUninterruptibly();
		readCount++;
		if(readCount==1) rw.acquireUninterruptibly();
		mutexR.release();
		enter.release();
	}

	@Override
	public void endRead() {
		mutexR.acquireUninterruptibly();
		readCount--;
		if(readCount==0) rw.release();
		mutexR.release();
	}

	@Override
	public void startWrite() {
		enter.acquireUninterruptibly();
		rw.acquireUninterruptibly();		//ako ima citalaca vec ili neki pisac, zablokirace se ovde,  a  enter nece dozvoljavati nikome vise da ulazi
		enter.release();
	}

	@Override
	public void endWrite() {
		rw.release();

	}

}
