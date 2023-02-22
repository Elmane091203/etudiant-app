package entity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Etudiant {
    private int id;
    private String matricule;
    private String nom;
    private String prenom;
    private double moyenne;
    private Classe classe;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, double moyenne, Classe classe) {
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
        this.classe = classe;
        this.matricule = generateMatricule();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    public String generateMatricule(){
        /**
        String prefix = "ET@";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = dateFormat.format(new Date());
        return prefix + date + classe.getNom() + "#";
        String d = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
         */
        return "ET@"+ ((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()))+classe.getNom()+"#";
    }
}
