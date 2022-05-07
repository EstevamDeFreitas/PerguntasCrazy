/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perguntas.src;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import perguntas.infraestrutura.FabricaDAO;
import perguntas.infraestrutura.PerguntaDAO;

/**
 *
 * @author estev
 */
public class PerguntaMaster {
    public static void main(String[] args) throws Exception{
        PerguntaDAO context = FabricaDAO.getPerguntaDAO();
        
        Pergunta pergunta = new Pergunta("Se gosta de carros","carro, gostar", Date.valueOf(LocalDate.now()), 0);
        
        context.salvar(pergunta);
        
    }
}
