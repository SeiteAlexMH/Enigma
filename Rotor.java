/**
 * 
 * @author Alexandre Seite
 * 
 */

public class Rotor {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private static final String[] ROTORS = { "ekmflgdqvzntowyhxuspaibrcj",
			"ajdksiruxblhwtmcqgznpyfvoe", "bdfhjlcprtxvznyeiwgakmusqo",
			"esovpzjayquirhxlnftgkdcmwb", "vzbrgityupsdnhlxawmjqofeck",
			"jpgvoumfyqbenhzrdkasxlictw", "nzjhgrcxmyswboufaivlpekqdt",
			"fkqhtlxocbjspdzramewniuygv" };
	private static final int[] TICKS = {17,5,22,10,0};
	private int rotorNum;
	private int position=0;

	public Rotor(int num, int position) {
		rotorNum = num - 1;
		for (int count = 0; count < position; count++) {
			turn();
		}
	}

	public void turn() {
		position = (position + 1) % 26;
	}

	public boolean tick(){
		if(rotorNum<5){
			return position==TICKS[rotorNum];
		}else{
		return position==25 || position==13;
		}
	}
	
	public Character encrypt1(char a) {
		int letter = (ALPHABET.indexOf(a)+position)%26;
		Character c = ROTORS[rotorNum].charAt(letter);
		int cypher = ALPHABET.indexOf(c)-position;
		if(cypher<0){
			cypher+=26;
		}
		return ALPHABET.charAt(cypher);
	}

	public Character encrypt2(char a) {
		int letter = (ALPHABET.indexOf(a)+position)%26;
		Character c = ALPHABET.charAt(letter);
		int cypher = ROTORS[rotorNum].indexOf(c)-position;
		if(cypher<0){
			cypher+=26;
		}
		return ALPHABET.charAt(cypher);
	}
}
