/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ammcp
 */
public class Imparte {
    private int idImporte;
    private int idCatedratico;
    private int idAsignatura;
    private int idHorario;

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }
    private String estado;

    public int getIdImporte() {
        return idImporte;
    }

    public void setIdImporte(int idImporte) {
        this.idImporte = idImporte;
    }

    public int getIdCatedratico() {
        return idCatedratico;
    }

    public void setIdCatedratico(int idCatedratico) {
        this.idCatedratico = idCatedratico;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
