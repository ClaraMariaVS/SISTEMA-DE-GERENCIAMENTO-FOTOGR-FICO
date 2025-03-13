package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Servico;
import model.ServicoDAO;

@WebServlet(name = "GerenciarServico", urlPatterns = {"/gerenciarServico"})
public class GerenciarServico extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, 
           HttpServletResponse response)
           throws ServletException, IOException {
           response.setContentType("text/html");
           PrintWriter out = response.getWriter();
           String acao = request.getParameter("acao");
           String idServico = request.getParameter("idServico");
           String mensagem = "";
        
          Servico s = new Servico();
          ServicoDAO sdao = new ServicoDAO();
        
        try {
            if(acao.equals("listar")){
                ArrayList<Servico> servicos = new ArrayList<>();
                servicos = sdao.getLista();
                RequestDispatcher dispatcher =
                        getServletContext().
                                getRequestDispatcher("/listarServicos.jsp");
                request.setAttribute("servicos", servicos);
                dispatcher.forward(request, response);
                        
            
            }else if(acao.equals("alterar")){
                s = sdao.getCarregarPorId(
                        Integer.parseInt(idServico));
                if(s.getIdServico() > 0){
                    RequestDispatcher dispatcher =
                            getServletContext().
                                getRequestDispatcher("/cadastrarServico.jsp");
                    request.setAttribute("servico", s);
                    dispatcher.forward(request, response);
                    
                }else{
                    mensagem = "Serviço não encontrado na base de dados!";
                }
            
            }else if(acao.equals("ativar")){
                s.setIdServico(Integer.parseInt(idServico));
                if(sdao.ativar(s)){
                    mensagem = "Serviço ativado com sucesso!";
                }else{
                    mensagem = "Serviço desativado com sucesso!";
                }

            }else if(acao.equals("desativar")){
                s.setIdServico(Integer.parseInt(idServico));
                if(sdao.desativar(s)){
                    mensagem = "Serviço desativado com sucesso!";
                }else{
                    mensagem = "Falha ao desativar o Serviço!";
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
                "location.href='gerenciarServico?acao=listar';" +
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
        String idServico = request.getParameter("idSevico");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String preco = request.getParameter("preco");
        String duracao = request.getParameter("duracao");
        String status = request.getParameter("status");
        String mensagem = "";
        
        
        Servico s = new Servico();
        ServicoDAO sdao = new ServicoDAO();
        try {
            Locale localeBR = new Locale("pt", "BR");
            NumberFormat din = NumberFormat.getCompactNumberInstance(localeBR, NumberFormat.Style.SHORT);
            
            if(!idServico.isEmpty()){
                s.setIdServico(Integer.parseInt(idServico));
            }
            
            if(nome.isEmpty() || nome.equals("")){
                request.setAttribute("msg", "Informe o nome do Serviço!");
                getServletContext().
                        getRequestDispatcher("/cadastrarServico.jsp").
                        forward(request, response);
                
               
            }else{
                s.setNome(nome);
            }
            
            if(descricao.isEmpty() || descricao.equals("")){
                request.setAttribute("msg", "Informe o descrição do Serviço!");
                getServletContext().
                        getRequestDispatcher("/cadastrarServico.jsp").
                        forward(request, response);
                
               
            }else{
                s.setDescricao(descricao);
            }
            
            if(preco.isEmpty() || preco.equals("")){
                request.setAttribute("msg", "Informe o preço do Serviço!");
                getServletContext().
                        getRequestDispatcher("/cadastrarServico.jsp").
                        forward(request, response);
                
               
            }else{
                s.setPreco((Double) din.parse(preco));
            }
            
            if(duracao.isEmpty() || duracao.equals("")){
                request.setAttribute("msg", "Informe a duração do Servico!");
                getServletContext().
                        getRequestDispatcher("/cadastrarServico.jsp").
                            forward(request, response);
            }else{
                s.setDuracao(Integer.parseInt(duracao));
            }
            
            if(status.isEmpty() || status.equals("")){
                request.setAttribute("msg", "Informe o status do Serviço!");
                getServletContext().
                        getRequestDispatcher("/cadastrarServico.jsp").
                            forward(request, response);
            }else{
                s.setStatus(Integer.parseInt(status));
            }
            
            if(sdao.gravar(s)){
                mensagem = "Serviço salvo na base de dados";
               
            }else{
                mensagem = "Falha ao gravar o serviço na base de dados";
            }
        } catch (SQLException e){
            mensagem = "Erro: " + e.getMessage();
        } catch (ParseException pe) {
            mensagem = "Erro: " + pe.getMessage();
        }
        
        out.println(
                "<script type='text/javascript'>" +
                "alert('" + mensagem + "');" +
                "location.href='gerenciarPerfil?acao=listar';" +
                "</script>"
        
        );
    }
}
