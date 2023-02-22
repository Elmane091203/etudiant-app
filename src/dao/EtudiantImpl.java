package dao;

import entity.Etudiant;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EtudiantImpl implements IEtudiant{
    private DB db = new DB();
    private int ok;
    private ResultSet rs;
    @Override
    public int add(Etudiant etudiant) {
        String sql = "INSERT INTO etudiant VALUES (null, ?,?,?,?,?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, etudiant.getMatricule());
            db.getPstm().setString(2, etudiant.getNom());
            db.getPstm().setString(3, etudiant.getPrenom());
            db.getPstm().setDouble(4, etudiant.getMoyenne());
            db.getPstm().setInt(5, etudiant.getClasse().getId());
            ok = db.executeMaj();
            db.closeConnection();
            modifEffectif(etudiant.getClasse().getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int update(Etudiant etudiant) {
        String sql = "Update etudiant set matricule=?, nom=?, prenom=?,moyenne=?,classe=? WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, etudiant.getMatricule());
            db.getPstm().setString(2, etudiant.getNom());
            db.getPstm().setString(3, etudiant.getPrenom());
            db.getPstm().setDouble(4, etudiant.getMoyenne());
            db.getPstm().setInt(5, etudiant.getClasse().getId());
            db.getPstm().setInt(6, etudiant.getId());
            ok = db.executeMaj();
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM etudiant WHERE id = ?";
        IEtudiant etudiant = new EtudiantImpl();
        try {
            int id1 =etudiant.get(id).getClasse().getId()-1;
            db.initPrepar(sql);
            db.getPstm().setInt(1,id);
            ok = db.executeMaj();
            db.closeConnection();
            modifEffectif(id1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<Etudiant> list() {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiant ORDER BY nom ASC";
        IClasse classe = new ClasseImpl();
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()){
                Etudiant etudiant = new Etudiant(rs.getString("nom"), rs.getString("prenom"),
                        rs.getDouble("moyenne"), classe.get(rs.getInt("classe")));
                etudiant.setId(rs.getInt("id"));
                etudiant.setMatricule(rs.getString("matricule"));
                etudiants.add(etudiant);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return etudiants;
    }

    @Override
    public Etudiant get(int id) {
        Etudiant etudiant = null;
        IClasse classe = new ClasseImpl();
        String sql = "SELECT * FROM etudiant WHERE id = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1,id);
            rs= db.executeSelect();
            if (rs.next()) {
                etudiant = new Etudiant(rs.getString("nom"), rs.getString("prenom"),
                        rs.getDouble("moyenne"), classe.get(rs.getInt("classe")));
                etudiant.setId(rs.getInt("id"));
                etudiant.setMatricule(rs.getString("matricule"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return etudiant;
    }

    @Override
    public List<Etudiant> getEtudiantByClasse(String classe) {
        List<Etudiant> list = new ArrayList<>();
        /*
        for (Etudiant e : list()) {
            if (e.getClasse().getNom().equals(classe))
                list.add(e);
        }

         */
        IClasse c = new ClasseImpl();
        String sql = "SELECT * FROM etudiant,classe WHERE etudiant.classe=classe.id AND classe.nom=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1,classe);
            rs = db.executeSelect();
            while (rs.next()){
                Etudiant etudiant = new Etudiant(rs.getString("nom"), rs.getString("prenom"),
                        rs.getDouble("moyenne"), c.get(rs.getInt("classe")));
                etudiant.setId(rs.getInt("id"));
                etudiant.setMatricule(rs.getString("matricule"));
                list.add(etudiant);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private void modifEffectif(int id){
        int nb=0;
        String sql = "SELECT * FROM etudiant WHERE classe=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1,id);
            rs = db.executeSelect();
            while (rs.next()){
                nb++;
            }
            sql = "UPDATE classe set effectif=? WHERE id=?";
            db.initPrepar(sql);
            db.getPstm().setInt(1,nb);
            db.getPstm().setInt(2,id);
            db.executeMaj();
            db.closeConnection();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
