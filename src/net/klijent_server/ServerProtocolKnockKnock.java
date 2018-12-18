package net.klijent_server;

public class ServerProtocolKnockKnock extends OneCommunicatorProtocol {

	private static final int WAITING = 0;
	private int state = WAITING;
	private String[] eggs = { "Belo", "Plavo", "Crveno", "Zeleno", "Zuto", "Crno" };
	private String[] questions = { "Kuc Kuc", "Djavo 's neba", "Jedno jaje" };
	private String[] answers = { "Koje?", "Sta Vam treba?", "Koje boje?" };
	private String yesAnswer = "Ima";
	private String noAnswer = "Nema";
	private String byeAnswer = "Zdravo";

	public ServerProtocolKnockKnock() {
		super(); /// Mnogo gresaka u kodu !!!
	}

	@Override
	public void conversation() {
		try {
			String outputLine = null;
			String inputLine = null;
			while ((inputLine = c.readString()) != null) {
				System.out.println("Klient: " + inputLine);
				outputLine = processInput(inputLine);
				c.writeString(outputLine);
				System.out.println("Server: " + outputLine);
				if (outputLine.equals("Zdravo"))
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			c.close();
		}

	}

	public String processInput(String theInput) {
		String theOutput = null;
		if (theInput == null)
			theInput = "";
		switch (state) {
		case 0:
		case 1:
		case 2:
			if (theInput.equalsIgnoreCase(questions[state])) {
				theOutput = answers[state];
				state++;
			} else {
				theOutput = byeAnswer;
				state = 0;
			}
			break;
		case 3:
			theOutput = noAnswer + theInput + " " + answers[2];
			for (int i = 0; i < eggs.length; i++) {
				if (theInput.equalsIgnoreCase(eggs[i])) {
					theOutput = yesAnswer;
					state++;
					break;
				}
			}
			break;
		default:
			theOutput = byeAnswer;
			break;
		}
		return theOutput;
	}

}
