package com.example.registro;

public class Comentario {

    int id;
    String comentario;
    int idUsuario;
    int mg;
    int nmg;
    int idRespuesta;

    public Comentario(){

    }

    public Comentario(int id,String comentario,int idUsuario,int mg,int nmg,int idRespuesta){
        super();
        this.id = id;
        this.comentario = comentario;
        this.idUsuario = idUsuario;
        this.mg = mg;
        this.nmg = nmg;
        this.idRespuesta = idRespuesta;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public void setComentario(String comentario){
        this.comentario = comentario;
    }
    public String getComentario(){
        return comentario;
    }

    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    public int getIdUsuario(){
        return idUsuario;
    }

    public void setMg(int mg){
        this.mg = mg;
    }
    public int getMg(){
        return mg;
    }

    public void setNmg(int nmg){
        this.nmg = nmg;
    }
    public int getNmg(){
        return nmg;
    }

    public void setIdRespuesta(int idRespuesta){
        this.idRespuesta = idRespuesta;
    }
    public int getIdRespuesta(){
        return idRespuesta;
    }
}
