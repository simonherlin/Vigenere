package modele;

public class Vigenere {

    public String VigenereCryptKey(String Message, String Key) {
        int i = 0;
        String loweredkey = Key.toLowerCase(), cryptedmessage = "";
        for (char ch: Message.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                cryptedmessage+= (char)(Math.floorMod(((((int)ch)-'A')+(((int)loweredkey.charAt(i%loweredkey.length()))-'A')),26)+'A');
                i++;
            } else {
                if (ch >= 'a' && ch <= 'z') {
                    cryptedmessage+= (char)(Math.floorMod(((((int)ch)-'a')+(((int)loweredkey.charAt(i%loweredkey.length()))-'a')),26)+'a');
                    i++;
                } else
                    cryptedmessage+= ch;
            }
        }
        return cryptedmessage;
    }

    public String VigenereDecryptKey(String Message, String Key) {
        int i = 0;
        String loweredkey = Key.toLowerCase(), decryptedmessage = "";
        for (char ch: Message.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                decryptedmessage+= (char)(Math.floorMod(((((int)ch)-'A')-(((int)loweredkey.charAt(i%loweredkey.length()))-'a')),26)+'A');
                i++;
            } else {
                if (ch >= 'a' && ch <= 'z') {
                    decryptedmessage+= (char)(Math.floorMod(((((int)ch)-'a')-(((int)loweredkey.charAt(i%loweredkey.length()))-'a')),26)+'a');
                    i++;
                } else
                    decryptedmessage+= ch;
            }

        }
        return decryptedmessage;
    }

    private double IC(String Message) {
        int[] nbapparitions = new int[26];

        String MessageFormated = format(Message);

        for (char ch: MessageFormated.toCharArray()) {
            nbapparitions[ch - 'A']++;
        }
        double IC = 0;
        for (int i = 0; i < nbapparitions.length; i++) {
            double num = nbapparitions[i] * (nbapparitions[i] - 1);
            double denum = MessageFormated.length() * (MessageFormated.length() - 1);
            IC += num / denum;
        }
        return IC;
    }

    private char[] MostApparedLetters(String text, int Keylength) {
        int greatfreq = 0;
        int letter = 0;
        char[] letters = new char[Keylength];
        int[] nbapparitions = new int[26];
        for (int j = 0; j < Keylength; j++) {
            letter = 0;
            greatfreq = 0;
            nbapparitions = new int[26];
            for (int i = j; i < text.length(); i+=Keylength) {
                nbapparitions[text.charAt(i) - 'A']++;
            }
            for (int i = 0; i< 26; i++) {
                if (nbapparitions[i] > greatfreq) {
                    greatfreq=nbapparitions[i];
                    letter = i;
                }
            }
            letters[j] = (char) (letter+97);
        }
        return letters;
    }

    private int  estimateKeyLength(String text) {
        String formatedtext =  text;
        double resultat = 0.0;
        int LengthKey= 0;
        for (int i=2; i<=10; i++) {
            String textmodifie= "";
            for (int j=0; j<formatedtext.length(); j+=i) { textmodifie += formatedtext.charAt(j); }
            if(IC(textmodifie) > resultat) {
                resultat = IC(textmodifie);
                LengthKey = i;
            }
        }
        return LengthKey;
    }

    private String GetPossibleKey(char[] letters) {
        char[] Keyword = new char[letters.length];
        for (int i =0; i < letters.length; i++) {
            Keyword[i]=(char) (Math.floorMod(letters[i]-'e',26)+97);
        }
        return new String(Keyword);
    }

    private String format(String text) {
        return text.toUpperCase().replaceAll("[^\\p{L}]", "");
    }


    public String GetKeyFromCryptedText(String CryptedText) {
        String FormatedCryptedText = format(CryptedText);
        int EstimatedLengthKey = estimateKeyLength(FormatedCryptedText);
        return GetPossibleKey(MostApparedLetters(FormatedCryptedText,EstimatedLengthKey));
    }
}
