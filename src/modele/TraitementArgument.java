package modele;

import controller.LectureEcritureFichier;
import view.Vue;

import java.io.IOException;

public class TraitementArgument {
    private String[] argument;
    private Vigenere myVigenere;
    private LectureEcritureFichier RWFile;
    private String cryptedtext;
    private String uncryptedtext;
    private String cle;
    private Vue myView;

    public TraitementArgument(String[] argument){
        this.argument = argument;
        this.myVigenere = new Vigenere();
        this.RWFile = new LectureEcritureFichier();
        this.cryptedtext = "";
        this.uncryptedtext = "";
        this.cle = "";
        this.myView = new Vue();
    }

    public void traitement() {
        //if (this.argument[0] != null && this.argument[1] != null && this.argument[2] != null) {
          if (this.argument.length ==2 || this.argument.length == 3) {
            switch(this.argument[0]) {
                case "-c" : traitementC(); break;
                case "-d" : traitementD(); break;
                case "-h" : this.myView.help(); break;
                default : this.myView.error(); break;
            }
        }
    }

    private void traitementC () {
        try {
            this.uncryptedtext = this.RWFile.LectureFichier(this.argument[2]);
            if (this.argument[3] == null) this.myView.errorKey();
            else {
                this.cle= this.argument[3];
                this.cryptedtext = this.myVigenere.VigenereCryptKey( this.uncryptedtext, this.cle);
                this.RWFile.EcritureFichier( this.uncryptedtext, this.argument[1]);
                this.myView.traitement(this.cle, this.cryptedtext);
            }
        }
        catch (IOException e) { this.myView.errorRW(); }
    }

    private void traitementD () {
        try {
            this.cryptedtext=RWFile.LectureFichier(this.argument[2]);
            if (this.argument[3] == null) {
                this.cle = this.myVigenere.GetKeyFromCryptedText(this.cryptedtext);
                this.myView.decryptageWithoutKey(this.cle);
            } else {
                this.cle= this.argument[3];
                this.myView.decryptageWithKey(this.cle);
            }
            this.uncryptedtext = this.myVigenere.VigenereDecryptKey(this.cryptedtext, this.cle);
            this.RWFile.EcritureFichier(this.uncryptedtext, this.argument[1]);
            this.myView.decryptage(this.uncryptedtext);
        } catch (IOException e) { this.myView.errorRW(); }
    }
}