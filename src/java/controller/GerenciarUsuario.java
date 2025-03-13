package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import model.Usuario;
import model.UsuarioDAO;


@WebServlet(name = "GerenciarUsuario", urlPatterns = {"/gerenciarUsuario"})
public class GerenciarUsuario extends HttpServlet {

 
 @Override
    protected void doGet(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idUsuario = request.getParameter("idUsuario");
        String mensagem = "";
        
        Usuario u = new Usuario();
        UsuarioDAO udao = new UsuarioDAO();
        
        try {
            if(acao.equals("listar")){
                ArrayList<Usuario> usuarios = new ArrayList<>();
                usuarios = udao.getLista();
                RequestDispatcher dispatcher =
                        getServletContext().
                                getRequestDispatcher("/listarUsuarios.jsp");
                request.setAttribute("usuarios", usuarios);
                dispatcher.forward(request, response);
                        
            
            }else if(acao.equals("alterar")){
                u = udao.getCarregarPorId(
                        Integer.parseInt(idUsuario));
                if(u.getIdUsuario() > 0){
                    RequestDispatcher dispatcher =
                            getServletContext().
                                getRequestDispatcher("/cadastrarUsuario.jsp");
                    request.setAttribute("usuario", u);
                    dispatcher.forward(request, response);
                    
                }else{
                    mensagem = "Usuário não encontrado na base de dados!";
                }
            
            }else if(acao.equals("ativar")){
                u.setIdUsuario(Integer.parseInt(idUsuario));
                if(udao.ativar(u)){
                    mensagem = "Usuário ativado com sucesso!";
                }else{
                    mensagem = "Usuário desativado com sucesso!";
                }
            
            }else if(acao.equals("desativar")){
                u.setIdUsuario(Integer.parseInt(idUsuario));
                if(udao.desativar(u)){
                    mensagem = "Usuário desativado com sucesso!";
                }else{
                    mensagem = "Falha ao desativar o Usuário!";
                }
            }else{
                response.sendRedirect("/index.jsp");
            }
      
        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }
        
        out.println(
                "<script type='text/javascript'>" +
                "alert('" + mensagem + "');" +
                "location.href='gerenciarUsuario?acao=listar';" +
                "</script>"
        );
        
      
        
    }
 
    @Override
    protected void doPost(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String idUsuario = request.getParameter("idUsuario");
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String status = request.getParameter("status");
        String idPerfil = request.getParameter("idPerfil");
        String mensagem = "";
        
         
        Usuario u = new Usuario();
            
            if(!idUsuario.isEmpty()){
                u.setIdUsuario(Integer.parseInt(idUsuario));
            }
            
            if(nome.isEmpty() || nome.equals("")){
                request.setAttribute("msg", "Informe o nome do Usuário!");
                getServletContext().
                        getRequestDispatcher("/cadastrarUsuario.jsp").
                        forward(request, response);
               
            }else{
                u.setNome(nome);
            }
            
            if(login.isEmpty() || login.equals("")){
                request.setAttribute("msg", "Informe o login!");
                despacharRequisicao(request, response);
                
            }else{
                u.setLogin(login);
            }
            
            if(senha.isEmpty() || senha.equals("")){
                request.setAttribute("msg", "Informe a senha do Usuário!");
                despacharRequisicao(request, response);
            }else{
                u.setSenha(senha);
            }
            
            if(status.isEmpty() || status.equals("")){
                request.setAttribute("msg", "Informe o status do Usuário!");
                despacharRequisicao(request, response);
            }else{
                try{
                    u.setStatus(Integer.parseInt(status));
                } catch(NumberFormatException e) {
                mensagem = "Erro: " + e.getMessage();
                e.printStackTrace();
            }
            }
            
            Perfil p = new Perfil();
        
        if(idPerfil.equals("") || idPerfil.isEmpty()){
            request.setAttribute("msg", "Informe o Perfil!");
            despacharRequisicao(request, response);
        }else{
            try {
                p.setIdPerfil(Integer.parseInt(idPerfil));
                //associacao entre objeto usuario e o objeto perfil
                u.setPerfil(p);
            } catch (Exception e) {
                mensagem = "Erro: " + e.getMessage();
                e.printStackTrace();
            }
            
        }
        
        UsuarioDAO udao = new UsuarioDAO();
        try {
            if(udao.gravar(u)){
                mensagem = 
                    "Usuário gravado com sucesso na base de dados!";
            }else{
                mensagem =
                    "Falha ao gravar o Usuário na base de dados!";
            }
            
        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }
        
        
        out.println(
                "<script type='text/javascript'>" +
                "alert('" + mensagem + "');" +
                "location.href='gerenciarUsuario?acao=listar';" +
                "</script>"
        
        );
    
}
        private void despacharRequisicao(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException{
                    getServletContext().getRequestDispatcher("/cadastrarUsuario.jsp").
                    forward(request, response);
        }
}