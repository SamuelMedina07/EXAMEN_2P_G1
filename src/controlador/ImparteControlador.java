/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Asignatura;
import modelo.Catedratico;
import modelo.Horario;
import modelo.Imparte;
import modelo.consultaAsignaturas;
import modelo.consultaCatedraticos;
import modelo.consultaHorarios;
import modelo.consultaImparte;
import vista.frm_Imparte;

/**
 *
 * @author ammcp
 */
public class ImparteControlador implements ActionListener {

    private Imparte imparte;
    private frm_Imparte form;
    private consultaImparte consImparte;
    private consultaAsignaturas consAsignatura = new consultaAsignaturas();
    private consultaCatedraticos consCatedratico = new consultaCatedraticos();
    private consultaHorarios consHorarios = new consultaHorarios();
    private Object datos[] = new Object[10];
    DefaultTableModel modelo;

    public ImparteControlador(Imparte imparte, frm_Imparte form, consultaImparte consImparte) {
        this.imparte = imparte;
        this.form = form;
        this.consImparte = consImparte;
        this.form.btnCrear.addActionListener(this);
        this.form.btnLeer.addActionListener(this);
        this.form.btnModificar.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);
        this.form.btnLimpiar.addActionListener(this);
        this.form.btn_actualizarComboboxs.addActionListener(this);
        this.form.btn_buscarAsignaciones.addActionListener(this);
        this.form.cbCatedraticos.addActionListener(this);
        this.form.cbAsignaturas.addActionListener(this);

        actualizarComboboxs();
    }

    public void llenarTabla() {
        modelo = (DefaultTableModel) form.tbl_registroAsignaciones.getModel();
        modelo.setRowCount(0);
        ArrayList<Imparte> listaAsignaciones = consImparte.leerTodosAsignaciones();
        int registros = listaAsignaciones.size();
        for (int i = 0; i < registros; i++) {
            Imparte asignacionTemporal = listaAsignaciones.get(i);

            datos[0] = asignacionTemporal.getIdImporte();
            datos[1] = consCatedratico.obtenerNombreCatedratico(asignacionTemporal.getIdCatedratico());
            datos[2] = consAsignatura.obtenerNombreAsignatura(asignacionTemporal.getIdAsignatura());
            datos[3] = consHorarios.obtenerHorario(asignacionTemporal.getIdHorario()).getDiaSemana();
            datos[4] = consHorarios.obtenerHorario(asignacionTemporal.getIdHorario()).getHoraInicio();
            datos[5] = consHorarios.obtenerHorario(asignacionTemporal.getIdHorario()).getHoraFin();
            modelo.addRow(datos);
        }
        form.tbl_registroAsignaciones.setModel(modelo);
    }

    public void limpiar() {
        form.txtCodigo.setText("0");
        modelo = (DefaultTableModel) form.tbl_registroAsignaciones.getModel();
        modelo.setRowCount(0);
    }

    //Llenar Combobox con los proveedores
    public void llenarComboBoxCatedratico(JComboBox<Catedratico> combobox) {
        // Obtén la lista de todos los proveedores desde la base de datos
        ArrayList<Catedratico> listaCatedratico = consCatedratico.leerTodosCatedraticos();

        // Limpia el ComboBox antes de agregar nuevos elementos
        combobox.removeAllItems();

        // Llena el ComboBox con los nombres de los proveedores
        for (Catedratico catedratico : listaCatedratico) {
            combobox.addItem(catedratico);
        }
    }

    public void llenarComboBoxAsignatura(JComboBox<Asignatura> combobox) {
        // Obtén la lista de todos los proveedores desde la base de datos
        ArrayList<Asignatura> listaAsignatura = consAsignatura.leerTodosAsignaturas();

        // Limpia el ComboBox antes de agregar nuevos elementos
        combobox.removeAllItems();

        // Llena el ComboBox con los nombres de los proveedores
        for (Asignatura asignatura : listaAsignatura) {
            combobox.addItem(asignatura);
        }
    }

    public void llenarComboBoxHorario(JComboBox<Horario> combobox) {
        // Obtén la lista de todos los proveedores desde la base de datos
        ArrayList<Horario> listaHorarios = consHorarios.leerTodosHorarios();

        // Limpia el ComboBox antes de agregar nuevos elementos
        combobox.removeAllItems();

        // Llena el ComboBox con los nombres de los proveedores
        for (Horario horario : listaHorarios) {
            combobox.addItem(horario);
        }
    }

    public void actualizarComboboxs() {
        llenarComboBoxCatedratico(form.cbCatedraticos);
        llenarComboBoxAsignatura(form.cbAsignaturas);
        llenarComboBoxHorario(form.cbHorarios);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //BOTON CREAR
        if (e.getSource() == form.btnCrear) {
            // Obtener los datos de la asignación desde la interfaz de usuari
            Catedratico catedraticoSeleccionado = (Catedratico) form.cbCatedraticos.getSelectedItem();
            Asignatura asignaturaSeleccionado = (Asignatura) form.cbAsignaturas.getSelectedItem();
            Horario horarioSeleccionado = (Horario) form.cbHorarios.getSelectedItem();

            // Validar si el catedrático tiene otra asignatura en el mismo horario
            if (consImparte.catedraticoTieneAsignaturaEnHorario(catedraticoSeleccionado.getCodigo(), horarioSeleccionado.getIdHorario())) {
                JOptionPane.showMessageDialog(null, "El catedrático ya tiene otra asignatura en este horario.");
                return; 
            }

            // Validar si la asignatura tiene otro catedrático en el mismo horario
            if (consImparte.asignaturaTieneCatedraticoEnHorario(asignaturaSeleccionado.getCodigo(), horarioSeleccionado.getIdHorario())) {
                JOptionPane.showMessageDialog(null, "La asignatura ya tiene otro catedrático en este horario.");
                return;
            }
            // Si pasa todas las validaciones, crear la asignación
            imparte.setIdCatedratico(catedraticoSeleccionado.getCodigo());
            imparte.setIdAsignatura(asignaturaSeleccionado.getCodigo());
            imparte.setIdHorario(horarioSeleccionado.getIdHorario());
            imparte.setEstado("Activo");
            if (consImparte.crearAsignacion(imparte)) {
                JOptionPane.showMessageDialog(null, "ASIGNACION CREADO");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE PUDO CREAR ASIGNACION");
            }

        }
        //BOTON LEER TODOS
        if (e.getSource() == form.btnLeer) {

            llenarTabla();
        }

        //BOTON BUSCAR CODIGO
        if (e.getSource() == form.btn_buscarAsignaciones) {

            imparte.setIdImporte(Integer.parseInt(form.txtCodigo.getText()));
            if (consImparte.buscarAsignacion(imparte)) {
                form.cbCatedraticos.removeAllItems();
                form.cbAsignaturas.removeAllItems();
                form.cbHorarios.removeAllItems();
                JOptionPane.showMessageDialog(null, "Asignacion Encontrado");
                form.cbCatedraticos.addItem(consCatedratico.obtenerCatedratico(imparte.getIdCatedratico()));
                form.cbAsignaturas.addItem(consAsignatura.obtenerAsignatura(imparte.getIdAsignatura()));
                form.cbHorarios.addItem(consHorarios.obtenerHorario(imparte.getIdHorario()));
            } else {
                JOptionPane.showMessageDialog(null, "Asignacion no encontrado");
            }
        }
        //boton actualizar
        if (e.getSource() == form.btnModificar) {

            imparte.setIdImporte(Integer.parseInt(form.txtCodigo.getText()));
            Catedratico catedraticoSeleccionado = (Catedratico) form.cbCatedraticos.getSelectedItem();
            imparte.setIdCatedratico(catedraticoSeleccionado.getCodigo());
            Asignatura asignaturaSeleccionado = (Asignatura) form.cbAsignaturas.getSelectedItem();
            imparte.setIdAsignatura(asignaturaSeleccionado.getCodigo());
            Horario horarioSeleccionado = (Horario) form.cbHorarios.getSelectedItem();
            imparte.setIdHorario(horarioSeleccionado.getIdHorario());
            if (consImparte.modificarAsignacion(imparte)) {
                JOptionPane.showMessageDialog(null, "ASIGNATURA ACUALIZADO");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR ASIGNATURA");
            }
        }
        //boton eliminar
        if (e.getSource() == form.btnEliminar) {

            imparte.setIdImporte(Integer.parseInt(form.txtCodigo.getText()));
            if (consImparte.eliminarAsignacion(imparte)) {
                JOptionPane.showMessageDialog(null, "ASIGNACION ELIMINADO");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR ASIGNACION");
            }
        }

        if (e.getSource() == form.btnLimpiar) {
            limpiar();
        }

        if (e.getSource() == form.btn_actualizarComboboxs) {
            actualizarComboboxs();
        }

        /*if(e.getSource()==form.btnSalir){
           this.form.dispose();
        }*/
    }

}
