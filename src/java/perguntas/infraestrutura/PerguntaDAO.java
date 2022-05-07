/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perguntas.infraestrutura;

import java.sql.SQLException;
import java.util.List;
import perguntas.src.Pergunta;

/**
 *
 * @author estev
 */
public interface PerguntaDAO {
    void salvar(Pergunta pergunta) throws ClassNotFoundException, SQLException;
    void atualizar(Pergunta pergunta) throws ClassNotFoundException, SQLException;
    void excluir(Pergunta pergunta) throws ClassNotFoundException, SQLException;
    void excluir(int id) throws ClassNotFoundException, SQLException;
    List<Pergunta> listarTodos() throws ClassNotFoundException, SQLException;
    Pergunta consultarPorId(int id) throws ClassNotFoundException, SQLException;
}
