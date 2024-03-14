/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.frm_Catedraticos;
import vista.frm_Asignaturas;
import vista.frm_Horarios;
import vista.frm_Imparte;
import vista.frm_Principal;
import vista.frm_Reportes;

/**
 *
 * @author ammcp
 */
public class PrincipalControlador implements ActionListener{
    private frm_Principal formPrin;
    private frm_Catedraticos frmCatedraticos;
    private frm_Asignaturas frmAsignaturas;
    private frm_Imparte frmImparte;
    private frm_Horarios frmHorario;
    private frm_Reportes frmReportes;

    public PrincipalControlador(frm_Principal formPrin, frm_Catedraticos frmCatedraticos, frm_Asignaturas frmAsignaturas,frm_Imparte frmImparte,frm_Horarios frmHorario,frm_Reportes frmReportes) {
        this.formPrin = formPrin;
        this.frmCatedraticos = frmCatedraticos;
        this.frmAsignaturas = frmAsignaturas;
        this.frmImparte = frmImparte;
        this.frmHorario = frmHorario;
        this.frmReportes = frmReportes;
        
        this.formPrin.btnCatedraticos.addActionListener(this);
        this.formPrin.btnAsignaturas.addActionListener(this);
        this.formPrin.btnHorarios.addActionListener(this);
        this.formPrin.btnReportes.addActionListener(this);
        this.frmHorario.btnAsignar.addActionListener(this);
    }
    
    public void inciar(){
        formPrin.setLocationRelativeTo(null);
        frmCatedraticos.setLocationRelativeTo(null);
        frmAsignaturas.setLocationRelativeTo(null);
        frmImparte.setLocationRelativeTo(null);
        frmHorario.setLocationRelativeTo(null);
        frmReportes.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==formPrin.btnCatedraticos){
            frmCatedraticos.lblUsuario.setText(formPrin.lblUsuario.getText());
            frmCatedraticos.setVisible(true);
        }
        if(e.getSource()==formPrin.btnSalir){
            System.exit(0);
        }
        //Proveedores
        if(e.getSource()==formPrin.btnAsignaturas){
            frmAsignaturas.lblUsuario.setText(formPrin.lblUsuario.getText());
            frmAsignaturas.setVisible(true);
        }
        
        if(e.getSource()==frmHorario.btnAsignar){
            frmImparte.setVisible(true);
        }
        
        if(e.getSource()==formPrin.btnHorarios){
            frmHorario.setVisible(true);
        }
        
        if(e.getSource()==formPrin.btnReportes){
            frmReportes.setVisible(true);
        }
        
        if(e.getSource()==formPrin.btnSalir){
            System.exit(0);
        }
    }
    
    
    
}
