package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";
    
    public ArrayList<Usuario> getLista()throws SQLException{
        ArrayList<Usuario> usuarios = new ArrayList<>();
        sql = "SELECT u.idUsuario, u.nome, p.nome, u.login, u.senha, u.status " +
               "FROM perfil p " + 
               "INNER JOIN usuario u " +
               "ON p.idPerfil = u.idPerfil ";
        con = ConexaoFactory.conectar();
        ps  = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while(rs.next()){
            Usuario u  = new Usuario();
            u.setIdUsuario(rs.getInt("u.idUsuario"));
            u.setNome(rs.getString("u.nome"));
            u.setLogin(rs.getString("u.login"));
            u.setSenha(rs.getString("u.senha"));
            u.setStatus(rs.getInt("u.status"));
            
            Perfil p = new Perfil();
            p.setNome(rs.getString("p.nome"));
            
            //Associação entre Usuário e Perfil
            u.setPerfil(p);
            usuarios.add(u);
            
        }
        
           ConexaoFactory.close(con);
      
           return usuarios;
    }
     
    public boolean gravar(Usuario u)throws SQLException{
        con = ConexaoFactory.conectar();
        
        if(u.getIdUsuario() == 0){
            sql = "INSERT INTO usuario (nome, login, senha, status, idPerfil) " +
                  "VALUES (?, ?, ?, ?, ?) ";
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getSenha());
            ps.setInt(4, u.getStatus());
            ps.setInt(5, u.getPerfil().getIdPerfil());
            
        }else{
            sql = "UPDATE usuario " +
                  "SET nome = ?, login = ?, senha = ?, status = ?, idPerfil = ? " +
                  "WHERE idUsuario = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getSenha());
            ps.setInt(4, u.getStatus());
            ps.setInt(5, u.getPerfil().getIdPerfil());
            ps.setInt(6, u.getIdUsuario());
  
        }
        
        ps.executeUpdate();
        ConexaoFactory.close(con);
               
        return true;
    }
    
    public Usuario getCarregarPorId(int idUsuario)
        throws SQLException{
        Usuario u = new Usuario();
        sql = "SELECT u.idUsuario, u.nome, p.nome, u.login, u.senha, u.status " +
              "FROM perfil p " + 
              "INNER JOIN usuario u " + 
              "ON p.idPerfil = u.idPerfil " + 
              "WHERE u.idUsuario = ? ";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        rs = ps.executeQuery();
        if(rs.next()){
            u.setIdUsuario(rs.getInt("u.idUsuario"));
            u.setNome(rs.getString("u.nome"));
            u.setLogin(rs.getString("u.login"));
            u.setSenha(rs.getString("u.senha"));
            u.setStatus(rs.getInt("u.status"));
            
            Perfil p = new Perfil();
            p.setNome(rs.getString("p.nome"));
            
            //Associação entre Usuário e Perfil
            u.setPerfil(p);
        }
        
        ConexaoFactory.close(con);
        
        return u;
            
    }
    
    
    public boolean desativar(Usuario u)throws SQLException{
        sql = "UPDATE usuario set status = 0 " +
              "WHERE idUsuario = ? ";
        
        con = ConexaoFactory.conectar();
        
        ps = con.prepareStatement(sql);
        ps.setInt(1, u.getIdUsuario());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        
        return true;
    }
    
    public boolean ativar(Usuario u)throws SQLException{
        sql = "UPDATE usuario set status = 1 " +
              "WHERE idUsuario = ? ";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, u.getIdUsuario());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        
        
        return true;
    }

}
