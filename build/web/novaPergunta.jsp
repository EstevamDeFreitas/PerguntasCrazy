<%-- 
    Document   : novaPergunta
    Created on : 3 de mai. de 2022, 19:03:26
    Author     : estev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
                            
        String idPergunta = (String) request.getParameter("id");

        String tipo = "";

        if(idPergunta == null){
            tipo = "Pergunta";
        }
        else{
            tipo = "Resposta";
        }

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nova <%= tipo %></title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        <header class="p-1 bg-dark text-white mb-3">
            <div class="d-flex flex-wrap justify-content-center p-3">
                <a href="" class="fs-4 text-decoration-none text-white me-5 me-md-auto">Perguntas<strong class="text-info">Crazy</strong></a>
                <a href="index.jsp" class="btn btn-outline-danger">Cancelar</a>
            </div>
        </header>
        <div class="container">
            <div class="card">
                <div class="card-header">
                    Nova <%= tipo %>
                </div>
                <div class="card-body">
                    <form action="pergunta" method="post">
                        
                        <label for="titulo"><%= tipo %></label>
                        <input type="text" name="titulo" id="titulo" class="form-control mb-3"/>
                        
                        
                        
                        <% 
                        
                            if(idPergunta != null){
                        
                        %>
                        
                        <input type="text" name="perguntaid" id="perguntaid" class="d-none" value="<%= idPergunta %>"/>
                        
                        <input type="text" name="tags" id="tags" class="d-none"/>
                        
                        <% } 
                            else{
                        %>
                        
                        <label for="tags">Tags:</label>
                        <input type="text" name="tags" id="tags" class="form-control mb-3"/>
                        
                        <% } %>
                        
                        <button class="btn btn-info" name="acao" value="inserir">Criar <%= tipo %></button>
                        
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
