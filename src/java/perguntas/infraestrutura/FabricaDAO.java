/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perguntas.infraestrutura;

import perguntas.infraestrutura.implementacao.PerguntaDAOPostgreSQL;

/**
 *
 * @author estev
 */
public class FabricaDAO {
    public static PerguntaDAOPostgreSQL getPerguntaDAO(){
        return new PerguntaDAOPostgreSQL();
    }
}
