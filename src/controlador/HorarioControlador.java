/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Horario;
import modelo.consultaHorarios;
import vista.frm_Horarios;

/**
 *
 * @author ammcp
 */
public class HorarioControlador implements ActionListener {
    
    private Horario horario;
    private frm_Horarios form;
    private consultaHorarios consHorario;
    private Object datos[] = new Object[4];
    DefaultTableModel modelo;
    

    public HorarioControlador(Horario horario, frm_Horarios form, consultaHorarios consHorario) {
        this.horario = horario;
        this.form = form;
        this.consHorario = consHorario;
        this.form.btnCrear.addActionListener(this);
        this.form.btnLeer.addActionListener(this);
        this.form.btn_buscarCodigo.addActionListener(this);
        this.form.btnModificar.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);
        this.form.btnLimpiar.addActionListener(this);
        this.form.btnSalir.addActionListener(this);
    }
    
    public void llenarTabla(){
        modelo = (DefaultTableModel) form.tbl_registroHorarios.getModel();
        modelo.setRowCount(0);
         ArrayList<Horario> listaHorarios = consHorario.leerTodosHorarios();
        int registros =listaHorarios.size();
        for(int i = 0; i < registros; i++){
            Horario horarioTemporal = listaHorarios.get(i);
            datos[0] = horarioTemporal.getIdHorario();
            datos[1] = horarioTemporal.getDiaSemana();
            datos[2] = horarioTemporal.getHoraInicio();
            datos[3] = horarioTemporal.getHoraFin();
            modelo.addRow(datos);
        }
        form.tbl_registroHorarios.setModel(modelo);
    }
    
    public void limpiar(){
        form.txtCodigo.setText("0");
        /*
        int fila = form.tbl_registrosClientes.getRowCount();
        
        for(int i=fila-1;i>=0;i--){
        modelo.removeRow(fila);
    }
         */
        modelo = (DefaultTableModel) form.tbl_registroHorarios.getModel();
        modelo.setRowCount(0);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //BOTON CREAR
        if(e.getSource()==form.btnCrear){
            horario.setDiaSemana((String) form.cbDias.getSelectedItem());
            java.util.Date horaInicioDate = (java.util.Date) form.spinnerInicio.getValue();
            Time horaInicio = new Time(horaInicioDate.getTime());
            horario.setHoraInicio(horaInicio);
            java.util.Date horaFinDate = (java.util.Date) form.spinnerFin.getValue();
            Time horaFin = new Time(horaFinDate.getTime());
            horario.setHoraFin(horaFin);
            horario.setEstado("Activo");
            if(consHorario.crearHorario(horario)){
                JOptionPane.showMessageDialog(null, "HORARIO CREADO");
                limpiar();
            }
            else{
                JOptionPane.showMessageDialog(null, "NO SE PUDO CREAR HORARIO");
            }
                  
        }
        //BOTON LEER TODOS
        if(e.getSource()==form.btnLeer){
            
           llenarTabla();
        }
        
         //BOTON BUSCAR CODIGO
        if(e.getSource()==form.btn_buscarCodigo){
            
           horario.setIdHorario(Integer.parseInt(form.txtCodigo.getText()));
           if(consHorario.buscarHorario(horario)){
               JOptionPane.showMessageDialog(null, "HORARIO Encontrado");
               form.spinnerInicio.setValue(new Date(horario.getHoraInicio().getTime()));
               form.spinnerFin.setValue(new Date(horario.getHoraFin().getTime()));
           }else{
               JOptionPane.showMessageDialog(null, "HORARIO no encontrado");
           }
        }
        //boton actualizar
        if(e.getSource()==form.btnModificar){
            
            horario.setIdHorario(Integer.parseInt(form.txtCodigo.getText()));
            horario.setDiaSemana((String) form.cbDias.getSelectedItem());
            java.util.Date horaInicioDate = (java.util.Date) form.spinnerInicio.getValue();
            Time horaInicio = new Time(horaInicioDate.getTime());
            horario.setHoraInicio(horaInicio);
            java.util.Date horaFinDate = (java.util.Date) form.spinnerFin.getValue();
            Time horaFin = new Time(horaFinDate.getTime());
            horario.setHoraFin(horaFin);
            if(consHorario.modificarHorario(horario)){
                JOptionPane.showMessageDialog(null, "HORARIO ACUALIZADO");
                limpiar();
            }
            else{
                JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR HORARIO");
            }
        }
        //boton eliminar
        if(e.getSource()==form.btnEliminar){
            
            horario.setIdHorario(Integer.parseInt(form.txtCodigo.getText()));
            if(consHorario.eliminarHorario(horario)){
                JOptionPane.showMessageDialog(null, "HORARIO ELIMINADO");
                limpiar();
            }
            else{
                JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR HORARIO");
            }
        }
        
        if(e.getSource()==form.btnLimpiar){
           limpiar();
        }
        
        if(e.getSource()==form.btnSalir){
           this.form.dispose();
        }
    }
    
}
