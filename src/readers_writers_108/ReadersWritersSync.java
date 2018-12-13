package readers_writers_108;

//kao monitor sa SC disciplinom
public class ReadersWritersSync implements ReadersWriters {
	private int readCount;
	private int writeCount;
	private int waitCount; // brojac koji prati koliko postoji blokiranih niti, ako je !=0 onda ce nova nit
							// takodje da se blokira, tako se sprecava izgladnjivanje

	public ReadersWritersSync() {
		readCount = writeCount = waitCount = 0;
	}

	@Override
	public synchronized void startRead() {
		if (writeCount != 0 || waitCount != 0) {
			waitCount++;
			while (writeCount != 0)
				try {
					wait();
				} catch (InterruptedException e) {
				}
			waitCount--;
		}
		readCount++;
	}

	@Override
	public void endRead() {
		readCount--;
		if (readCount == 0)
			notifyAll();

	}

	@Override
	public void startWrite() {
		if (readCount != 0 || writeCount != 0 || waitCount != 0) {
			waitCount++;
			while (readCount != 0 || writeCount != 0)
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			waitCount--;
		}
		writeCount++;
	}

	@Override
	public void endWrite() {
		writeCount--;
		notifyAll();
	}

}
