package Main;


import dao.ClasseImpl;
import dao.EtudiantImpl;
import dao.IClasse;
import dao.IEtudiant;
import entity.Classe;
import entity.Etudiant;

public class Main {
    public static void main(String[] args) {
        IClasse classe = new ClasseImpl();
//        classe.add(new Classe("LPIAGE"));
//        System.out.println(classe.get(1).getId());
        IEtudiant etudiant = new EtudiantImpl();
        Etudiant e1 = new Etudiant("MAmi", "mami",20,classe.get(1));
        etudiant.add(e1);
//        etudiant.delete(5);
        etudiant.get(1);
        for (Etudiant e : etudiant.getEtudiantByClasse("LPIAGE")) {
            System.out.println(e.getNom());
        }
    }
}