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
import modelo.Catedratico;
import modelo.Horario;
import modelo.consultaAsignaturas;
import modelo.consultaCatedraticos;
import modelo.consultaHorarios;
import modelo.consultaImparte;
import vista.frm_Reportes;

/**
 *
 * @author ammcp
 */
public class ReporteControlador implements ActionListener {

    private frm_Reportes form;
    private DefaultTableModel modelo;
    private Object datos[] = new Object[10];
    private consultaAsignaturas consAsignatura = new consultaAsignaturas();
    private consultaCatedraticos consCatedratico = new consultaCatedraticos();
    private consultaHorarios consHorarios = new consultaHorarios();
    private consultaImparte consImparte = new consultaImparte();

    public ReporteControlador(frm_Reportes form) {
        this.form = form;
        this.form.btn_buscar.addActionListener(this);
        this.form.cbBuscarPor.addActionListener(this);
        this.modelo = (DefaultTableModel) form.tbl_reporte.getModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == form.cbBuscarPor) {
            modelo.setRowCount(0);
            actualizarColumnasTabla();
        } else if (e.getSource() == form.btn_buscar) {
            llenarTablaSegunFiltro();
        }
    }

    private void actualizarColumnasTabla() {
        // Obtener el filtro seleccionado
        String filtro = (String) form.cbBuscarPor.getSelectedItem();

        // Cambiar los nombres de las columnas según el filtro seleccionado
        switch (filtro) {
            case "Todos los catedráticos":
                modelo.setColumnIdentifiers(new Object[]{"CODIGO", "NOMBRE", "CIUEDAD", "TELEFONO"});
                break;
            case "Todas las asignaturas":
                modelo.setColumnIdentifiers(new Object[]{"CODIGO", "MATERIA"});
                break;
            case "Horarios completos":
                modelo.setColumnIdentifiers(new Object[]{"DIA", "HORA INICIO", "HORA GIN"});
                break;
            case "Asignaturas de un catedratico":
                modelo.setColumnIdentifiers(new Object[]{"CODIGO", "ASIGNATURA"});
                break;
            case "Asignaturas con catedraticos":
                modelo.setColumnIdentifiers(new Object[]{"ASIGNATURA", "CATEDRATICO"});
                break;
            case "Asignatura con horarios":
                modelo.setColumnIdentifiers(new Object[]{"DIA", "HORA INICIO", "HORA GIN"});
                break;
            case "Horarios":
                modelo.setColumnIdentifiers(new Object[]{"DIA", "HORA INICIO", "HORA GIN", "ASIGNATURA"});
                break;
            default:
                break;
        }
    }

    private void llenarTablaSegunFiltro() {
        String filtro = (String) form.cbBuscarPor.getSelectedItem();

        switch (filtro) {
            case "Todos los catedráticos":
                llenarTablaCatedraticos();
                break;
            case "Todas las asignaturas":
                llenarTablaAsignaturas();
                break;
            case "Horarios completos":
                llenarTablaHorarios();
                break;
            case "Asignaturas de un catedratico":
                int idCatedratico = Integer.parseInt(form.txtBuscar.getText());
                llenarTablaAsignaturasDeCatedratico(idCatedratico);
                break;
            case "Asignaturas con catedraticos":
                int idAsignatura = Integer.parseInt(form.txtBuscar.getText());
                llenarTablaCatedraticoSegunAsignaturas(idAsignatura);
                break;
            case "Asignatura con horarios":
                int idAsignaturas = Integer.parseInt(form.txtBuscar.getText());
                llenarTablaHorariosSegunAsignaturas(idAsignaturas);
                break;

            //
            default:
                break;
        }

    }

    private void llenarTablaCatedraticos() {
        modelo.setRowCount(0);
        ArrayList<Catedratico> catedraticos = consCatedratico.leerTodosCatedraticos();
        for (Catedratico catedratico : catedraticos) {
            datos[0] = catedratico.getCodigo();
            datos[1] = catedratico.getNombre();
            datos[2] = catedratico.getDireccion();
            datos[3] = catedratico.getTelefono();
            modelo.addRow(datos);
        }
    }

    private void llenarTablaAsignaturas() {
        modelo.setRowCount(0);
        ArrayList<Asignatura> asignaturas = consAsignatura.leerTodosAsignaturas();
        for (Asignatura asignatura : asignaturas) {
            datos[0] = asignatura.getCodigo();
            datos[1] = asignatura.getNombre();
            modelo.addRow(datos);
        }
    }

    private void llenarTablaHorarios() {
        modelo.setRowCount(0);
        ArrayList<Horario> horarios = consHorarios.leerTodosHorarios();
        for (Horario horario : horarios) {
            datos[0] = horario.getDiaSemana();
            datos[1] = horario.getHoraInicio();
            datos[2] = horario.getHoraFin();
            modelo.addRow(datos);
        }
    }

    private void llenarTablaAsignaturasDeCatedratico(int id) {
        modelo.setRowCount(0);
        ArrayList<Asignatura> asignaturas = consImparte.obtenerAsignaturasDeCatedratico(id);
        for (Asignatura asignatura : asignaturas) {
            datos[0] = asignatura.getCodigo();
            datos[1] = asignatura.getNombre();
            modelo.addRow(datos);
        }
    }

    private void llenarTablaCatedraticoSegunAsignaturas(int id) {
        modelo.setRowCount(0);
        ArrayList<Catedratico> catedraticos = consImparte.obtenerCatedraticosPorAsignatura(id);
        for (Catedratico catedratico : catedraticos) {
            datos[0] = catedratico.getCodigo();
            datos[1] = catedratico.getNombre();
            modelo.addRow(datos);
        }
    }
    private void llenarTablaHorariosSegunAsignaturas(int id) {
        modelo.setRowCount(0);
        ArrayList<Horario> horarios = consImparte.obtenerHorariosPorAsignatura(id);
        for (Horario horario : horarios) {
            datos[0] = horario.getDiaSemana();
            datos[1] = horario.getHoraInicio();
            datos[2] = horario.getHoraFin();
            modelo.addRow(datos);
        }
    }
}
