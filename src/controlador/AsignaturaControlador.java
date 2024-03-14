/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Asignatura;
import modelo.consultaAsignaturas;
import vista.frm_Asignaturas;
import vista.frm_Imparte;

/**
 *
 * @author ammcp
 */
public class AsignaturaControlador implements ActionListener {
    
    private Asignatura asignatura;
    private frm_Asignaturas form;
    private consultaAsignaturas consAsignatura;
    private Object datos[] = new Object[4];
    DefaultTableModel modelo;
    

    public AsignaturaControlador(Asignatura asignatura, frm_Asignaturas form, consultaAsignaturas consAsignatura) {
        this.asignatura = asignatura;
        this.form = form;
        this.consAsignatura = consAsignatura;
        this.form.btnCrear.addActionListener(this);
        this.form.btnLeer.addActionListener(this);
        this.form.btn_buscarAsignatura.addActionListener(this);
        this.form.btnModificar.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);
        this.form.btnLimpiar.addActionListener(this);
    }
    
    public void llenarTabla(){
        modelo = (DefaultTableModel) form.tbl_registrosMaterias.getModel();
        modelo.setRowCount(0);
         ArrayList<Asignatura> listaAsignaturas = consAsignatura.leerTodosAsignaturas();
        int registros =listaAsignaturas.size();
        for(int i = 0; i < registros; i++){
            Asignatura asignaturaTemporal = listaAsignaturas.get(i);
            
            datos[0] = asignaturaTemporal.getCodigo();
            datos[1] = asignaturaTemporal.getNombre();
            modelo.addRow(datos);
        }
        form.tbl_registrosMaterias.setModel(modelo);
    }
    
    public void limpiar(){
        form.txtCodigo.setText("0");
        form.txtDescripcion.setText(null);
        modelo = (DefaultTableModel) form.tbl_registrosMaterias.getModel();
        modelo.setRowCount(0);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //BOTON CREAR
        if(e.getSource()==form.btnCrear){
            asignatura.setNombre(form.txtDescripcion.getText());
            asignatura.setEstado("Activo");
            if(consAsignatura.crearAsignatura(asignatura)){
                JOptionPane.showMessageDialog(null, "ASIGNATURA CREADO");
                limpiar();
            }
            else{
                JOptionPane.showMessageDialog(null, "NO SE PUDO CREAR ASIGNATURA");
            }
                  
        }
        //BOTON LEER TODOS
        if(e.getSource()==form.btnLeer){
            
           llenarTabla();
        }
        
         //BOTON BUSCAR CODIGO
        if(e.getSource()==form.btn_buscarAsignatura){
            
           asignatura.setCodigo(Integer.parseInt(form.txtCodigo.getText()));
           if(consAsignatura.buscarAsignatura(asignatura)){
               JOptionPane.showMessageDialog(null, "ASIGNATURA Encontrado");
               form.txtDescripcion.setText(asignatura.getNombre());
           }else{
               JOptionPane.showMessageDialog(null, "ASIGNATURA no encontrado");
           }
        }
        //boton actualizar
        if(e.getSource()==form.btnModificar){
            
            asignatura.setCodigo(Integer.parseInt(form.txtCodigo.getText()));
            asignatura.setNombre(form.txtDescripcion.getText());
            if(consAsignatura.modificarAsignatura(asignatura)){
                JOptionPane.showMessageDialog(null, "ASIGNATURA ACUALIZADO");
                limpiar();
            }
            else{
                JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR ASIGNATURA");
            }
        }
        //boton eliminar
        if(e.getSource()==form.btnEliminar){
            
            asignatura.setCodigo(Integer.parseInt(form.txtCodigo.getText()));
            if(consAsignatura.eliminarAsignatura(asignatura)){
                JOptionPane.showMessageDialog(null, "ASIGNATURA ELIMINADO");
                limpiar();
            }
            else{
                JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR ASIGNATURA");
            }
        }
        
        if(e.getSource()==form.btnLimpiar){
           limpiar();
        }
        
        
        
        /*if(e.getSource()==form.btnSalir){
           this.form.dispose();
        }*/
    }
    
}
