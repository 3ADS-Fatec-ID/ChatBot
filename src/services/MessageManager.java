package services;

public class MessageManager {

	public static String replaceValue(String text, String variable, String value) {
		
		text = text.replaceAll("[{]"+variable+"[}]", value);
		return text;
	}
	
	public static boolean checkAnswer(String text) {
		
		
		
		
		
		
		return true;
		
		
		
	}
	
	
}
