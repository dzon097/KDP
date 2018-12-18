package net.klijent_server;

public class ProtocolFactory {
	public Protocol createProtocol(String protocol) {
		Protocol prot = null;
		String protocolPackageRoot = Protocol.class.getPackage().getName();	
		//ovako dohatam ime paketa u kome se nalazi klasa mog trzeng protokola
		try {
			String clsName = protocolPackageRoot + ".protocol." + protocol;
			Class<?> cls = null;
			try {
				cls = Class.forName(clsName);
			}catch(ClassNotFoundException e) {}
			if(cls != null) {
				prot=(Protocol) cls.newInstance();	//na osnovu imena klace prosledjenog argumentom, pravimo insancu te klase
			}
		}catch (Exception e) {}
		return prot;
	}
	
	public static ProtocolFactory pf;
	
	private static Object protocolHandlerLock = new Object();
	
	static void setProtocolFactory(ProtocolFactory protocol) {
		synchronized(protocolHandlerLock) {
			if(pf != null) {
				throw new Error("factory already defined");
			}
			SecurityManager security = System.getSecurityManager();
			if(security != null) {
				security.checkSetFactory();
			}
			pf = protocol;
		}
	}
	
	static {
		setProtocolFactory(new ProtocolFactory());
	}
	
	

}
