package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicoDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    
    public ArrayList<Servico> getLista()throws SQLException{
        ArrayList<Servico> servicos = 
                new ArrayList<>();
        sql = "SELECT idServico, nome, descricao, preco, duracao, status " +
               "FROM servico";
        con = ConexaoFactory.conectar();
        ps  = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while(rs.next()){
            Servico s  = new Servico();
            s.setIdServico(rs.getInt("idServico"));
            s.setNome(rs.getString("nome"));
            s.setDescricao(rs.getString("descricao"));
            s.setPreco(rs.getDouble("preco"));
            s.setDuracao(rs.getInt("duracao"));
            s.setStatus(rs.getInt("status"));
            
            servicos.add(s);
            
        }
        
           ConexaoFactory.close(con);
      
       
        
        
        return servicos;
    }

    public boolean gravar(Servico s)throws SQLException{
        con = ConexaoFactory.conectar();
        
        if(s.getIdServico() == 0){
            sql = "INSERT INTO servico (nome, descricao, preco, duracao, status) " +
                  "VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getNome());
            ps.setString(2, s.getDescricao());
            ps.setDouble(3, s.getPreco());
            ps.setInt(4, s.getDuracao());
            ps.setInt(5, s.getStatus());
            
        }else{
            sql = 
               "UPDATE servico set nome = ?, descricao = ?, preco = ?, duracao = ?, status = ? " +
               "WHERE idServico = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getNome());
            ps.setString(2, s.getDescricao());
            ps.setDouble(3, s.getPreco());
            ps.setInt(4, s.getDuracao());
            ps.setInt(5, s.getStatus());
            ps.setInt(6, s.getIdServico());
                  
            
        }
        
        ps.executeUpdate();
        ConexaoFactory.close(con);
               
        return true;
    }
    
    public Servico getCarregarPorId(int idServico)
        throws SQLException{
        Servico s = new Servico();
        sql = "SELECT idServico, nome, descricao, preco, duracao, status " +
              "FROM servico WHERE idServico = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idServico);
        rs = ps.executeQuery();
        if(rs.next()){
            s.setIdServico(rs.getInt("idServico"));
            s.setNome(rs.getString("nome"));
            s.setDescricao(rs.getString("descricao"));
            s.setPreco(rs.getDouble("preco"));
            s.setDuracao(rs.getInt("duracao"));
            s.setStatus(rs.getInt("status"));
            
        }
        
        ConexaoFactory.close(con);
        
        
        return s;
        
    }
    
    public boolean desativar(Servico s)throws SQLException{
        sql = "UPDATE servico set status = 0 " +
              "WHERE idServico = ?";
        
        con = ConexaoFactory.conectar();
        
        ps = con.prepareStatement(sql);
        ps.setInt(1, s.getIdServico());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        
        return true;
    }
    
    public boolean ativar(Servico s)throws SQLException{
        sql = "UPDATE servico set status = 1 " +
              "WHERE idServico = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, s.getIdServico());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        
        
        return true;
    }

}


