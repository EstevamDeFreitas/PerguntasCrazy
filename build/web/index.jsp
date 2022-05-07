<%-- 
    Document   : home
    Created on : 30 de abr. de 2022, 16:07:37
    Author     : estev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="perguntas.src.Pergunta" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perguntas Crazy</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/fontawesome.min.css"
            integrity="sha512-8Vtie9oRR62i7vkmVUISvuwOeipGv8Jd+Sur/ORKDD5JiLgTGeBSkI3ISOhc730VGvA5VVQPwKIKlmi+zMZ71w=="
            crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    </head>
    <body>
        <header class="p-1 bg-dark text-white mb-5">
            <div class="d-flex flex-wrap justify-content-center p-3">
                <a href="" class="fs-4 text-decoration-none text-white me-5 me-md-auto">Perguntas<strong class="text-info">Crazy</strong></a>
                <a href="novaPergunta.jsp" class="btn btn-outline-info">Nova Pergunta</a>
            </div>
        </header>
        <div class="container">
            <% 
                List<Pergunta> perguntas = (List<Pergunta>) request.getAttribute("perguntas");
                
                if(null == perguntas){
                    request.getRequestDispatcher("/pergunta").forward(request, response);
                    return;
                }
            
                for(Pergunta pergunta : perguntas){
            %>
            <div class="card mb-5 shadow">
                <div class="card-body">
                    <div class="card-title d-inline">
                        <strong class="align-top <%= pergunta.getAvaliacao() > 0 ? "text-info" : "text-danger" %>"><%= pergunta.getAvaliacao() %></strong>
                        <a href="pergunta?acao=vote&vote=up&id=<%= pergunta.getId() %>" class="material-symbols-rounded text-decoration-none text-dark">arrow_upward</a>
                        <a href="pergunta?acao=vote&vote=down&id=<%= pergunta.getId() %>" class="material-symbols-rounded text-decoration-none text-dark">arrow_downward</a>
                    </div>
                    <h5 class="card-title"><%= pergunta.getTitulo() %></h5>
                    <div class="d-inline">
                        <%
                        
                            String[] tags = pergunta.getTags().split(", ");
                            
                            for(String tag : tags){
                        %>
                        <span class="badge bg-dark"><%= tag %></span>
                        <% } %>
                    </div>
                    <% if(pergunta.getRespostas().size() > 0){ %>
                    <div class="card-text">
                        <span>Respostas:</span>
                    </div>
                    <% } %>
                </div>
                <ul class="list-group list-group-flush">
                    <% for(Pergunta resposta : pergunta.getRespostas()){ %>
                    <li class="list-group-item">
                        <strong class="align-top <%= resposta.getAvaliacao() > 0 ? "text-info" : "text-danger" %>"><%= resposta.getAvaliacao() %></strong>
                        <a href="pergunta?acao=vote&vote=up&id=<%= resposta.getId() %>" class="material-symbols-rounded text-decoration-none text-dark">arrow_upward</a>
                        <a href="pergunta?acao=vote&vote=down&id=<%= resposta.getId() %>" class="material-symbols-rounded text-decoration-none text-dark">arrow_downward</a>
                        <h6 class="card-subtitle mb-2 text-muted"><%= resposta.getTitulo() %></h6>
                    </li>
                    <% } %>
                    <li class="list-group-item d-grid">
                        <a href="novaPergunta.jsp?id=<%= pergunta.getId() %>" class="btn btn-light">Responder</a>
                    </li>
                </ul>
            </div>
            <%
            
                }
            
            %>
            <div class="d-grid">
                <a href="novaPergunta.jsp" class="btn btn-dark btn-lg mb-3">Nova pergunta</a>
            </div>
        </div>
    </body>
</html>
