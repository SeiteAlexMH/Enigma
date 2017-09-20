/**
 * 
 * @author Alexandre Seite
 * 
 */

public class Machine {
	private Rotor rotor1;
	private Rotor rotor2;
	private Rotor rotor3;
	private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private final static String REFLECTOR = "yruhqsldpxngokmiebfzcwvjat";
	
	/**
	 * Constructor for the Enigma machine object, take in several parameters:
	 * @param rotorNum1 number of the right rotor.
	 * @param rotorNum2 number of the center rotor.
	 * @param rotorNum3 number of the left rotor.
	 * @param rotorPos1 initial position of the right rotor.
	 * @param rotorPos2 initial position of the center rotor.
	 * @param rotorPos3 initial position of the left rotor.
	 */
	public Machine(int rotorNum1, int rotorNum2, int rotorNum3, int rotorPos1,
			int rotorPos2, int rotorPos3) {
		rotor1 = new Rotor(rotorNum1, rotorPos1);
		rotor2 = new Rotor(rotorNum2, rotorPos2);
		rotor3 = new Rotor(rotorNum3, rotorPos3);
	}
	
	/**
	 * The algorithm to encrypt the message
	 * @param message the message to encipher
	 * @return the ciphered message.
	 */
	public String encrypt(String message) {
		String cipher = "";
		String input = message.toLowerCase();
		for (int index = 0; index < input.length(); index++) {
			if (ALPHABET.indexOf(input.charAt(index)) > -1) {
				Character current = input.charAt(index);
				rotor1.turn();
				if (rotor1.tick()) {
					rotor2.turn();
				}
				if (rotor2.tick()) {
					rotor3.turn();
				}
				current = rotor1.encrypt1(current);
				current = rotor2.encrypt1(current);
				current = rotor3.encrypt1(current);
				current = reflect(current);
				current = rotor3.encrypt2(current);
				current = rotor2.encrypt2(current);
				current = rotor1.encrypt2(current);
				cipher += current;
			} else {
				cipher += input.charAt(index);
			}
		}
		return cipher;
	}
	
	/**
	 * a helper method for the encryption.
	 * @param a the character after the first round of scrambling
	 * @return some other character.
	 */
	public Character reflect(Character a) {
		int letter = ALPHABET.indexOf(a);
		return REFLECTOR.charAt(letter);
	}

	/**
	 * An example of how to use this class.
	 * The result of the first encryption is:
	 * "pdpn on f nqqw"
	 * @param args
	 */
	public static void main(String[] args) {
		Machine test = new Machine(4, 8, 1, 16, 4, 23);
		System.out.println(test.encrypt("this is a test"));
		}
}
