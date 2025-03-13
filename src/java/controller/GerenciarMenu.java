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
import javax.servlet.http.HttpSession;
import model.Menu;
import model.MenuDAO;

@WebServlet(name = "GerenciarMenu", urlPatterns = {"/gerenciarMenu"})
public class GerenciarMenu extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String acao = request.getParameter("acao");
            String idMenu = request.getParameter("idMenu");
            String mensagem = "";
            
            Menu m = new Menu();
            MenuDAO mdao = new MenuDAO();
            
            try {
            if(acao.equals("listar")){
                ArrayList<Menu> menus = new ArrayList<>();
                menus = mdao.getLista();
                RequestDispatcher dispatcher =
                        getServletContext().
                                getRequestDispatcher("/listarMenus.jsp");
                request.setAttribute("menus", menus);
                dispatcher.forward(request, response);
                        
            
            }else if(acao.equals("alterar")){
                m = mdao.getCarregarPorId(
                        Integer.parseInt(idMenu));
                if(m.getIdMenu() > 0){
                    RequestDispatcher dispatcher =
                            getServletContext().
                                getRequestDispatcher("/cadastrarMenu.jsp");
                    request.setAttribute("menu", m);
                    dispatcher.forward(request, response);
                    
                }else{
                    mensagem = "Menu não encontrado na base de dados!";
                }
            
            }else if(acao.equals("ativar")){
                m.setIdMenu(Integer.parseInt(idMenu));
                if(mdao.ativar(m)){
                    mensagem = "Menu ativado com sucesso!";
                }else{
                    mensagem = "Falha ao ativar o menu!";
                }

            }else if(acao.equals("desativar")){
                m.setIdMenu(Integer.parseInt(idMenu));
                if(mdao.desativar(m)){
                    mensagem = "Menu desativado com sucesso!";
                }else{
                    mensagem = "Falha ao desativar o Menu!";
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
                "location.href='gerenciarMenu?acao=listar';" +
                "</script>"
        );
        
      
        
    }
  
    @Override
    protected void doPost(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String idMenu = request.getParameter("idMenu");
        String nome = request.getParameter("nome");
        String link = request.getParameter("link");
        String icone = request.getParameter("icone");
        String exibir = request.getParameter("exibir");
        String status = request.getParameter("status");
        String mensagem = "";
        
        
        Menu m = new Menu();
        

            if(!idMenu.isEmpty()){
                m.setIdMenu(Integer.parseInt(idMenu));
            }
            
            m.setIcone(icone);
            
            if(nome.equals("") || nome.isEmpty()){
            request.setAttribute("msg", 
                    "Informe o nome do menu!");
            despacharRequisicao(request, response);
            }else{
            m.setNome(nome);
            }
            
            if(link.equals("") || link.isEmpty()){
            request.setAttribute("msg", "Informe o link!");
            despacharRequisicao(request, response);
            }else{
            m.setLink(link);
            }
            
            if(exibir.equals("") || exibir.isEmpty()){
            request.setAttribute("msg", 
                    "Informe o valor para exibir!");
            despacharRequisicao(request, response);
            }else{
            try{
               m.setExibir(Integer.parseInt(exibir));  
            }catch(NumberFormatException e){
              
            }
            }
            
            if(status.equals("") || status.isEmpty()){
            request.setAttribute("msg",
                   "Informe o valor do status!");
            despacharRequisicao(request, response);
            }else{
            try {
                 m.setStatus(Integer.parseInt(status));
            } catch (NumberFormatException e) {
                 mensagem = "Erro: " + e.getMessage(); 
            }
           
            }
            
            MenuDAO mdao = new MenuDAO();
            
            try{
                if(mdao.gravar(m)){
                mensagem = "Menu salvo na base de dados";
                }else{
                mensagem = "Falha ao gravar o menu na base de dados";      
                }
                }catch(SQLException e){
                    mensagem = "Erro: " + e.getMessage();
                    e.printStackTrace();
                }
        
                out.println(
                "<script type='text/javascript'>" +
                "alert('" + mensagem + "');" +
                "location.href='gerenciarMenu?acao=listar';" +
                "</script>"
        
                    );
    
                }
    
    private void despacharRequisicao(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException{
        getServletContext().getRequestDispatcher("/cadastrarMenu.jsp").
                                    forward(request, response);
    }
}
