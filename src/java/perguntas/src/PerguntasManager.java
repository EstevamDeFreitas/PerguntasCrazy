package perguntas.src;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import perguntas.infraestrutura.FabricaDAO;
import perguntas.infraestrutura.PerguntaDAO;
import perguntas.infraestrutura.implementacao.PerguntaDAOPostgreSQL;

/**
 *
 * @author estev
 */
public class PerguntasManager extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        
        PerguntaDAOPostgreSQL context = FabricaDAO.getPerguntaDAO();
        
        if(acao == null){
            try{
                request.setAttribute("perguntas", context.listarTodos());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            catch(SQLException | ClassNotFoundException e){
                        
            }
        }
        else if(acao.equalsIgnoreCase("inserir")){
            try{
                String titulo = request.getParameter("titulo");
                String tags = request.getParameter("tags");
                String perguntaId = (String) request.getParameter("perguntaid");
                
                Pergunta pergunta = null;
                
                if(perguntaId == null){
                    pergunta = new Pergunta(titulo, tags, Date.valueOf(LocalDate.now()), 0);
                }
                else{
                    pergunta = new Pergunta(titulo, tags, Date.valueOf(LocalDate.now()), 0, Integer.valueOf(perguntaId));
                }
                
                context.salvar(pergunta);
                
                request.setAttribute("acao", null);
                response.sendRedirect("index.jsp");
            }
            catch(SQLException | ClassNotFoundException e){
                       e.printStackTrace();
            }
        }
        else if(acao.equalsIgnoreCase("vote")){
            try{
                String vote = request.getParameter("vote");
                
                int qtdVote = 0;
                
                if(vote.equalsIgnoreCase("up")){
                    qtdVote = 1;
                }
                else if(vote.equalsIgnoreCase("down")){
                   qtdVote = -1;
                }
                
                int idPergunta = Integer.valueOf(request.getParameter("id"));
                
                Pergunta pergunta = context.consultarPorId(idPergunta);
                
                if(pergunta != null){
                    pergunta.setAvaliacao(pergunta.getAvaliacao() + qtdVote);
                    
                    context.atualizar(pergunta);
                }
                
                response.sendRedirect("index.jsp");
                
            }
            catch(SQLException | ClassNotFoundException e){
                        
            }
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service(request, response);
    }
}
