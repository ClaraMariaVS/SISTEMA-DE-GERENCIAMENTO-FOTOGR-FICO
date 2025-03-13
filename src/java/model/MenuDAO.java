
package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class MenuDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
 public ArrayList<Menu> getLista()throws SQLException{
     ArrayList<Menu> menus = new ArrayList<>();
     sql = "SELECT idMenu, nome, link, icone, exibir, status " +
           "FROM menu";
     
     con = ConexaoFactory.conectar();
     ps = con.prepareStatement(sql);
     rs = ps.executeQuery();
     while(rs.next()){
         Menu m = new Menu();
         m.setIdMenu(rs.getInt("idMenu"));
         m.setNome(rs.getString("nome"));
         m.setLink(rs.getString("link"));
         m.setIcone(rs.getString("icone"));
         m.setExibir(rs.getInt("exibir"));
         m.setStatus(rs.getInt("status"));
         menus.add(m);
         
     }
     ConexaoFactory.close(con);
     
     return menus;
     
 }
 
 public boolean gravar(Menu m)throws SQLException{
     con = ConexaoFactory.conectar();
     if(m.getIdMenu() == 0){
         sql = "INSERT INTO menu (nome, link, icone, exibir, status) " +
               "VALUES (?, ?, ?, ?, ?)";
         ps = con.prepareStatement(sql);
         ps.setString(1, m.getNome());
         ps.setString(2, m.getLink());
         ps.setString(3, m.getIcone());
         ps.setInt(4, m.getExibir());
         ps.setInt(5, m.getStatus());
         
     }else{
         sql = "UPDATE menu set nome = ?, link = ?, icone = ?, " + 
               "exibir = ?, status = ? " +
               "WHERE idMenu = ? ";
         ps = con.prepareStatement(sql);
         ps.setString(1, m.getNome());
         ps.setString(2, m.getLink());
         ps.setString(3, m.getIcone());
         ps.setInt(4, m.getExibir());
         ps.setInt(5, m.getStatus());
         ps.setInt(6, m.getIdMenu());
    }
     
      ps.executeUpdate();
      ConexaoFactory.close(con);
     
     return true;
 }
   
 public Menu getCarregarPorId(int idMenu)throws SQLException{
     Menu m = new Menu();
     sql = "SELECT idMenu, nome, link, icone, exibir, status " +
           "FROM menu " +
           "WHERE idMenu = ?";
     
     con = ConexaoFactory.conectar();
     ps = con.prepareStatement(sql);
     ps.setInt(1, idMenu);
     rs = ps.executeQuery();
     if(rs.next()){
         m.setIdMenu(rs.getInt("idMenu"));
         m.setNome(rs.getString("nome"));
         m.setLink(rs.getString("link"));
         m.setIcone(rs.getString("icone"));
         m.setExibir(rs.getInt("exibir"));
         m.setStatus(rs.getInt("status"));
         
     }
     ConexaoFactory.close(con);
     
     return m;
     
 }
 
 public boolean desativar(Menu m)throws SQLException{
     sql = "UPDATE menu SET status = 0 " +
           "WHERE idMenu = ?";
     con = ConexaoFactory.conectar();
     ps = con.prepareStatement(sql);
     ps.setInt(1, m.getIdMenu());
     ps.executeUpdate();
     ConexaoFactory.close(con);
     return true;
 }
 
 
 public boolean ativar(Menu m)throws SQLException{
     sql = "UPDATE menu SET status = 1 " +
           "WHERE idMenu = ?";
     con = ConexaoFactory.conectar();
     ps = con.prepareStatement(sql);
     ps.setInt(1, m.getIdMenu());
     ps.executeUpdate();
     ConexaoFactory.close(con);
     
     return true;
 }
}
