/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perguntas.infraestrutura.implementacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import perguntas.infraestrutura.PerguntaDAO;
import perguntas.infraestrutura.persistencia.BancoDeDados;
import perguntas.src.Pergunta;

/**
 *
 * @author estev
 */
public class PerguntaDAOPostgreSQL implements PerguntaDAO {

    @Override
    public void salvar(Pergunta pergunta) throws ClassNotFoundException, SQLException {
        try(Connection conexao = BancoDeDados.getConexao()){
            PreparedStatement sql = conexao.prepareStatement("INSERT INTO _perguntas(titulo, tags, datapostagem, avaliacao, perguntaid) VALUES(?, ?, ?, ?, ?)");
            sql.setString(1, pergunta.getTitulo());
            sql.setString(2, pergunta.getTags());
            sql.setDate(3, pergunta.getDataPostagem());
            sql.setInt(4, pergunta.getAvaliacao());
            
            if(!pergunta.perguntaIdIsNull()){
                sql.setInt(5, pergunta.getPerguntaId());
            }
            else{
                sql.setNull(5, java.sql.Types.INTEGER);
            }
            
            sql.executeUpdate();
        }
    }

    @Override
    public void atualizar(Pergunta pergunta) throws ClassNotFoundException, SQLException {
        try(Connection conexao = BancoDeDados.getConexao()){
            PreparedStatement sql = conexao.prepareStatement("UPDATE _perguntas SET titulo=?, tags=?, datapostagem=?, avaliacao=?, perguntaid=? WHERE id=?");
            sql.setString(1, pergunta.getTitulo());
            sql.setString(2, pergunta.getTags());
            sql.setDate(3, pergunta.getDataPostagem());
            sql.setInt(4, pergunta.getAvaliacao());
            if(!pergunta.perguntaIdIsNull()){
                sql.setInt(5, pergunta.getPerguntaId());
            }
            else{
                sql.setNull(5, java.sql.Types.INTEGER);
            }
            sql.setInt(6, pergunta.getId());
            sql.executeUpdate();
        }
    }

    @Override
    public void excluir(Pergunta pergunta) throws ClassNotFoundException, SQLException {
        excluir(pergunta.getId());
    }

    @Override
    public void excluir(int id) throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDeDados.getConexao()) {
            PreparedStatement sql = conexao.prepareStatement("DELETE FROM _perguntas WHERE id=?");
            sql.setInt(1, id);
            sql.executeUpdate();
        }
    }

    @Override
    public List<Pergunta> listarTodos() throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDeDados.getConexao()){
            PreparedStatement sql = conexao.prepareStatement("SELECT id, titulo, tags, datapostagem, avaliacao FROM _perguntas WHERE perguntaid is null");
            ResultSet resultado = sql.executeQuery();
            List<Pergunta> perguntas = new ArrayList<>();
            while (resultado.next()){
                Pergunta pergunta = new Pergunta(
                        resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("tags"),
                        resultado.getDate("datapostagem"),
                        Integer.valueOf(resultado.getString("avaliacao"))
                );
                
                PreparedStatement subsql = conexao.prepareStatement("SELECT id, titulo, tags, datapostagem, avaliacao, perguntaid FROM _perguntas WHERE perguntaid = ?");
                subsql.setInt(1, resultado.getInt("id"));
                ResultSet resultadoRespostas = subsql.executeQuery();
                while(resultadoRespostas.next()){
                    Pergunta resposta = new Pergunta(
                        resultadoRespostas.getInt("id"),
                        resultadoRespostas.getString("titulo"),
                        resultadoRespostas.getString("tags"),
                        resultadoRespostas.getDate("datapostagem"),
                        resultadoRespostas.getInt("avaliacao"),
                        resultadoRespostas.getInt("perguntaid")
                    );
                    
                    pergunta.addResposta(resposta);
                }
                
                perguntas.add(pergunta);
            }
            
            return perguntas;
        }
    }

    @Override
    public Pergunta consultarPorId(int i) throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDeDados.getConexao()){
            PreparedStatement sql = conexao.prepareStatement("SELECT id, titulo, tags, datapostagem, avaliacao, perguntaid FROM _perguntas WHERE id=?");
            sql.setInt(1, i);
            
            Pergunta pergunta = null;
            ResultSet resultado = sql.executeQuery();
            if(resultado.next()){
                pergunta = new Pergunta(resultado.getInt("id"), resultado.getString("titulo"), resultado.getString("tags"), resultado.getDate("datapostagem"), resultado.getInt("avaliacao"), resultado.getInt("perguntaid"));
                
                PreparedStatement subsql = conexao.prepareStatement("SELECT id, titulo, tags, datapostagem, avaliacao, perguntaid FROM _perguntas WHERE perguntaid = ?");
                subsql.setInt(1, resultado.getInt("id"));
                ResultSet resultadoRespostas = subsql.executeQuery();
                while(resultadoRespostas.next()){
                    Pergunta resposta = new Pergunta(
                        resultadoRespostas.getInt("id"),
                        resultadoRespostas.getString("titulo"),
                        resultadoRespostas.getString("tags"),
                        resultadoRespostas.getDate("datapostagem"),
                        resultadoRespostas.getInt("avaliacao"),
                        resultadoRespostas.getInt("perguntaid")
                    );
                    
                    pergunta.addResposta(resposta);
                }
            }
            
            return pergunta;
        }
    }
    
}
