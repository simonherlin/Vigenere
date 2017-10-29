package modele;

import view.Vue;

public class Main {
    public static void main(String[] args) {
        Vue maVue = new Vue();
        if (args.length > 2) {
            TraitementArgument traitement = new TraitementArgument(args);
            traitement.traitement();
        }
        else
            maVue.pasParametre();
    }
}