package view;

public class Vue {
    public void pasParametre(){
        System.out.println("Error argument");
    }

    public void help(){
        System.out.println("Aide Vigenere : \n\t -d (--Decrypt--) <sourcefile> <destination> <code(optional)> \n\t -c (--Crypt--) <sourcefile> <destination> <code>");
    }

    public void error() {
        System.out.println("Erreur ! Utilisez -h pour plus d'informations");
    }

    public void traitement (String cle, String cryptedtext) {
        System.out.println("Mode Cryptage");
        System.out.println("Clé saisie : " + cle);
        System.out.println("Cryptage ...");
        System.out.println("Extrait du message traduit : \n" + cryptedtext.substring(0, Math.min(cryptedtext.length(), 30)) + "...\n");
    }

    public void decryptageWithKey (String cle){
        System.out.println("Mode Décryptage");
        System.out.println("Clé saisie : " + cle);
    }

    public void decryptageWithoutKey (String cle){
        System.out.println("Mode Décryptage");
        System.out.println("Mode détection de clé (N'oubliez pas que la clé ne sera précise que si le texte est d'une certaine importance)");
        System.out.println("Détection de la clé ...");
        System.out.println("Taille de la clé : " + cle.length());
        System.out.println("Clé calculée : " + cle);
    }

    public void decryptage(String uncryptedtext){
        System.out.println("Décryptage...");
        System.out.println("Extrait du message traduit : \n" + uncryptedtext.substring(0, Math.min(uncryptedtext.length(), 30)) + "...\n");
    }

    public void errorKey () {
        System.out.println("Erreur : Clé de cryptage nécessaire");
    }

    public void errorRW() {
        System.out.println("Erreur : Problème d'écriture/lecture des fichiers");
    }
}