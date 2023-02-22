package dao;

import entity.Etudiant;

import java.util.List;

public interface IEtudiant extends Repository<Etudiant> {
    List<Etudiant> getEtudiantByClasse(String classe);
}
