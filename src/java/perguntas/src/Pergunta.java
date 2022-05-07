package perguntas.src;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author estev
 */
public class Pergunta {
    private int id;
    private String titulo;
    private String tags;
    private Date dataPostagem;
    private int avaliacao;
    private Integer perguntaId;
    private List<Pergunta> respostas;

    public List<Pergunta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Pergunta> respostas) {
        this.respostas = respostas;
    }
    
    public void addResposta(Pergunta resposta){
        this.respostas.add(resposta);
    }

    public Integer getPerguntaId() {
        return perguntaId;
    }
    
    public boolean perguntaIdIsNull(){
        return perguntaId == null || perguntaId == 0;
    }

    public void setPerguntaId(Integer perguntaId) {
        this.perguntaId = perguntaId;
    }

    public String getTags() {
        return tags;
    }

    /**
     *
     * Usado para criar um objeto pergunta novo sem id
     */
    public Pergunta(String titulo, String tags, Date dataPostagem, int avaliacao) {
        this.titulo = titulo;
        this.tags = tags;
        this.dataPostagem = dataPostagem;
        this.avaliacao = avaliacao;
        respostas = new ArrayList<>();
    }

    /**
     *
     * Usado para criar um objeto pergunta que aponta para outra pergunta
     */
    public Pergunta(int id, String titulo, String tags, Date dataPostagem, int avaliacao, int perguntaId) {
        this.id = id;
        this.titulo = titulo;
        this.tags = tags;
        this.dataPostagem = dataPostagem;
        this.avaliacao = avaliacao;
        this.perguntaId = perguntaId;
        respostas = new ArrayList<>();
    }
    
    /**
     *
     * Usado para criar novo um objeto pergunta que aponta para outra pergunta
     */
    public Pergunta(String titulo, String tags, Date dataPostagem, int avaliacao, int perguntaId) {
        this.titulo = titulo;
        this.tags = tags;
        this.dataPostagem = dataPostagem;
        this.avaliacao = avaliacao;
        this.perguntaId = perguntaId;
        respostas = new ArrayList<>();
    }

    public Pergunta(int id, String titulo, String tags, Date dataPostagem, int avaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.tags = tags;
        this.dataPostagem = dataPostagem;
        this.avaliacao = avaliacao;
        respostas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(Date dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
