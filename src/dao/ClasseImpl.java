package dao;

import entity.Classe;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClasseImpl implements IClasse {
    private DB db = new DB();
    private int ok;
    private ResultSet rs;

    @Override
    public int add(Classe classe) {
        String sql = "Insert into classe values (Null,?,?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, classe.getNom());
            db.getPstm().setDouble(2, classe.getEffectif());
            ok = db.executeMaj();
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int update(Classe classe) {
        String sql = "Update classe set nom=?, effectif=? WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, classe.getNom());
            db.getPstm().setDouble(2, classe.getEffectif());
            db.getPstm().setInt(3, classe.getId());
            ok = db.executeMaj();
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM classe WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1,id);
            ok = db.executeMaj();
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<Classe> list() {
        List<Classe> classes = new ArrayList<>();
        String sql = "SELECT * FROM classe ORDER BY nom ASC";
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()){
                Classe c = new Classe(rs.getString(2));
                c.setId(rs.getInt(1));
                c.setEffectif(rs.getInt(3));
                classes.add(c);
            }
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public Classe get(int id) {
        Classe c = new Classe();
        String sql = "SELECT * FROM classe WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1,id);
            rs = db.executeSelect();
            if (rs.next()) {
                c = new Classe(rs.getString(2));
                c.setId(rs.getInt(1));
                c.setEffectif(rs.getInt(3));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }
}
