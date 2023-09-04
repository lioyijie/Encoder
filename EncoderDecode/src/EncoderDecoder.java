import java.util.Scanner;

public class EncoderDecoder {
    private final String referenceTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
    
    public String encode(String plainText, char offsetChar) {
        int offset = referenceTable.indexOf(offsetChar);
        StringBuilder encodedText = new StringBuilder();
        
        encodedText.append(offsetChar);
        
        for (char c : plainText.toCharArray()) {
            int index = referenceTable.indexOf(c);
            if (index != -1) {
                int shiftedIndex = (index - offset + referenceTable.length()) % referenceTable.length();
                encodedText.append(referenceTable.charAt(shiftedIndex));
            } else {
                encodedText.append(c);
            }
        }
        
        return encodedText.toString();
    }
    
    public String decode(String encodedText) {
        if (encodedText.isEmpty()) {
            return "";
        }
        
        char offsetChar = encodedText.charAt(0);
        int offset = referenceTable.indexOf(offsetChar);
        StringBuilder decodedText = new StringBuilder();
        
        for (int i = 1; i < encodedText.length(); i++) {
            char c = encodedText.charAt(i);
            int index = referenceTable.indexOf(c);
            if (index != -1) {
                int shiftedIndex = (index + offset + referenceTable.length()) % referenceTable.length();
                decodedText.append(referenceTable.charAt(shiftedIndex));
            } else {
                decodedText.append(c);
            }
        }
        
        return decodedText.toString();
    }
    
    public static void main(String[] args) {
        EncoderDecoder encoder = new EncoderDecoder();
        Scanner scanner = new Scanner(System.in);
        
        String plainText = "HELLO WORLD";
        System.out.print("Enter offset character: ");
        char offsetChar = scanner.next().charAt(0);
        String encodedText1 = encoder.encode(plainText, offsetChar);
        String decodedText1 = encoder.decode(encodedText1);
        
        System.out.println("Plaintext = :" + plainText);
        System.out.println("Offset character: " + offsetChar);
        System.out.println("Encoded text: " + encodedText1);
        System.out.println("Decoded text: " + decodedText1);
        System.out.println();
    }
}