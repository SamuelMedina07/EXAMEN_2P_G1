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
import modelo.Catedratico;
import modelo.consultaCatedraticos;
import vista.frm_Catedraticos;

/**
 *
 * @author ammcp
 */
public class CatedraticoControlador implements ActionListener {
    
    private Catedratico catedratico;
    private frm_Catedraticos form;
    private consultaCatedraticos consCatedratico;
    private Object datos[] = new Object[4];
    DefaultTableModel modelo;
    

    public CatedraticoControlador(Catedratico catedratico, frm_Catedraticos form, consultaCatedraticos consCatedratico) {
        this.catedratico = catedratico;
        this.form = form;
        this.consCatedratico = consCatedratico;
        this.form.btnCrear.addActionListener(this);
        this.form.btnLeer.addActionListener(this);
        this.form.btn_buscarCodigo.addActionListener(this);
        this.form.btnModificar.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);
        this.form.btnLimpiar.addActionListener(this);
        this.form.btnSalir.addActionListener(this);
    }
    
    public void llenarTabla(){
        modelo = (DefaultTableModel) form.tbl_registrosCatedraticos.getModel();
        modelo.setRowCount(0);
         ArrayList<Catedratico> listaCatedraticos = consCatedratico.leerTodosCatedraticos();
        int registros =listaCatedraticos.size();
        for(int i = 0; i < registros; i++){
            Catedratico catedraticoTemporal = listaCatedraticos.get(i);
            
            datos[0] = catedraticoTemporal.getCodigo();
            datos[1] = catedraticoTemporal.getNombre();
            datos[2] = catedraticoTemporal.getDireccion();
            datos[3] = catedraticoTemporal.getTelefono();
            modelo.addRow(datos);
        }
        form.tbl_registrosCatedraticos.setModel(modelo);
    }
    
    public void limpiar(){
        form.txtCodigo.setText("0");
        form.txtNombre.setText(null);
        form.txtDireccion.setText(null);
        form.txtTelefono.setText(null);
        /*
        int fila = form.tbl_registrosClientes.getRowCount();
        
        for(int i=fila-1;i>=0;i--){
        modelo.removeRow(fila);
    }
         */
        modelo = (DefaultTableModel) form.tbl_registrosCatedraticos.getModel();
        modelo.setRowCount(0);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //BOTON CREAR
        if(e.getSource()==form.btnCrear){
            catedratico.setCodigo(Integer.parseInt(form.txtCodigo.getText()));
            catedratico.setNombre(form.txtNombre.getText());
            catedratico.setDireccion(form.txtDireccion.getText());
            catedratico.setTelefono(form.txtTelefono.getText());
            catedratico.setEstado("Activo");
            if(consCatedratico.crearCatedratico(catedratico)){
                JOptionPane.showMessageDialog(null, "CATEDRATICO CREADO");
                limpiar();
            }
            else{
                JOptionPane.showMessageDialog(null, "NO SE PUDO CREAR CATEDRATICO");
            }
                  
        }
        //BOTON LEER TODOS
        if(e.getSource()==form.btnLeer){
            
           llenarTabla();
        }
        
         //BOTON BUSCAR CODIGO
        if(e.getSource()==form.btn_buscarCodigo){
            
           catedratico.setCodigo(Integer.parseInt(form.txtCodigo.getText()));
           if(consCatedratico.buscarCatedratico(catedratico)){
               JOptionPane.showMessageDialog(null, "CATEDRATIICO Encontrado");
               form.txtNombre.setText(catedratico.getNombre());
               form.txtDireccion.setText(catedratico.getDireccion());
               form.txtTelefono.setText(catedratico.getTelefono());
           }else{
               JOptionPane.showMessageDialog(null, "CATEDRATIICO no encontrado");
           }
        }
        //boton actualizar
        if(e.getSource()==form.btnModificar){
            
            catedratico.setCodigo(Integer.parseInt(form.txtCodigo.getText()));
            catedratico.setNombre(form.txtNombre.getText());
            catedratico.setDireccion(form.txtDireccion.getText());
            catedratico.setTelefono(form.txtTelefono.getText());
            if(consCatedratico.modificarCatedratico(catedratico)){
                JOptionPane.showMessageDialog(null, "CATEDRATICO ACUALIZADO");
                limpiar();
            }
            else{
                JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR CATEDRATICO");
            }
        }
        //boton eliminar
        if(e.getSource()==form.btnEliminar){
            
            catedratico.setCodigo(Integer.parseInt(form.txtCodigo.getText()));
            if(consCatedratico.eliminarCatedratico(catedratico)){
                JOptionPane.showMessageDialog(null, "CATEDRATICO ELIMINADO");
                limpiar();
            }
            else{
                JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR CATEDRATICO");
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
