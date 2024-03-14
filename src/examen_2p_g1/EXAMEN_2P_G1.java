/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_2p_g1;

import controlador.AsignaturaControlador;
import controlador.CatedraticoControlador;
import controlador.HorarioControlador;
import controlador.ImparteControlador;
import controlador.PrincipalControlador;
import controlador.ReporteControlador;
import modelo.Asignatura;
import modelo.Catedratico;
import modelo.Horario;
import modelo.Imparte;
import modelo.consultaAsignaturas;
import modelo.consultaCatedraticos;
import modelo.consultaHorarios;
import modelo.consultaImparte;
import vista.frm_Asignaturas;
import vista.frm_Catedraticos;
import vista.frm_Horarios;
import vista.frm_Imparte;
import vista.frm_Inicio;
import vista.frm_Principal;
import vista.frm_Reportes;
import vista.frm_login;

/**
 *
 * @author ammcp
 */
public class EXAMEN_2P_G1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        frm_Principal frm_pri = new frm_Principal(("Arington"));//frm principal clase padre
        
        frm_Inicio formInicio = new frm_Inicio(new frm_login(frm_pri));
        formInicio.setVisible(true);
        
        //Catedratico
        Catedratico catedratico = new Catedratico();
        consultaCatedraticos consCatedraticos = new consultaCatedraticos();
        frm_Catedraticos frm_catedraticos = new frm_Catedraticos(frm_pri,true,"");
        CatedraticoControlador contCatedraticos = new CatedraticoControlador(catedratico, frm_catedraticos, consCatedraticos);
        
        //Asignaturas
        Asignatura asignatura = new Asignatura();
        frm_Asignaturas frm_asignaturas = new frm_Asignaturas(frm_pri, true);
        consultaAsignaturas consAsignatura = new consultaAsignaturas();
        AsignaturaControlador contAsignatura = new AsignaturaControlador(asignatura, frm_asignaturas, consAsignatura);
        
        
        //HORARIOS
        Horario horario = new Horario();
        frm_Horarios frmHorario= new frm_Horarios(frm_pri, true,"");
        consultaHorarios consHorario = new consultaHorarios();
        HorarioControlador contHorario = new HorarioControlador(horario, frmHorario, consHorario);
        
        
        //Imparte
        Imparte imp = new Imparte();
        frm_Imparte frmImparte = new frm_Imparte(frmHorario, true);
        consultaImparte consImparte = new consultaImparte();
        ImparteControlador  contImparte = new ImparteControlador(imp, frmImparte, consImparte);
        
        //Reportes
        frm_Reportes frmReportes = new frm_Reportes(frmImparte, true);
        ReporteControlador contReporte = new ReporteControlador(frmReportes);
        
        PrincipalControlador contPri = new PrincipalControlador(frm_pri, frm_catedraticos, frm_asignaturas,frmImparte,frmHorario,frmReportes);
        contPri.inciar();
        
    }
    
}
