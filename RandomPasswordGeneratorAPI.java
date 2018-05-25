import java.security.SecureRandom;

/**
 * This API creates random passwords including alphanumeric and special characters.
 * Requesting entities are able to request a variable length of passwords(8 to 80 characters in length).
 */

/**
 * @author Yang Bai(aka:hyaku-beta)
 *
 */
public class RandomPasswordGeneratorAPI {
	
	public static String USAGE = "Usage: java RandomPasswordGeneratorAPI [password length]";
	public static String LENGTH_ERROR = "Invalid length! Please input an integer number from 8 to 80.";
	/**
	 * Generates random password in given length
	 * @param length of password
	 * @return password string
	 */
	public static String generatePassword(int length) {
		//Create a string for storing random symbols as password
		String password = "";
		
		//Create instance of SecureRandom class
		SecureRandom rand = new SecureRandom();
		
		
		for(int i = 0; i < length; i++) {
			//Generate random integers in range 33(inclusive) to 126(inclusive) which indicates the alphanumeric and special characters in ASCII table
			int num = rand.nextInt(127 - 33) + 33;
			
			//Convert the randomly chosen number to ASCII symbol
			char c = (char)num;
			
			//Append the symbols to the string for storing password
			password += c;
		}
		
		return password;
	}
	
	/**
	 * Checks if the input length is integer number.
	 * @param input key
	 * @return check result
	 */
	public static Boolean isInteger(String len) {
		if (len == null) return false;
		if (len.length() == 0) return false;
		
		int i = 0;
		//Check the possibility of negative number
		if (len.charAt(0) == '-') {
			if (len.length() == 1) return false;
			i = 1;
		}
		for (; i < len.length(); i++) {
			if (len.charAt(i) < '0' || len.charAt(i) > '9') return false;
		}
		return true;
	}
	

	/**
	 * Handles input and output.
	 * @param args.length = 1, and can be converted into Integer as the length of the password
	 */
	public static void main(String[] args) {
		//If the argument length is not 1 then announce the usage instruction
		if(args.length != 1) {
			System.err.println(USAGE);
		} else {
			//Check if the argument can be an Integer 
			if(!isInteger(args[0])) {
				System.err.println(LENGTH_ERROR);
			} else {
				try {
					//Parse the input key string into integer type
					int len = Integer.parseInt(args[0]);
					//Check if the length is between 8 and 80
					if (len < 8 || len > 80) {
						System.err.println(LENGTH_ERROR);
					} else {
						String password = generatePassword(len);
						System.out.println("Generated Password: " + password);
					}
				//Catch the parsing exception
				} catch (NumberFormatException ex) {
					System.err.println("Convert input length failed.");
				}
			}
		}
	}

}
