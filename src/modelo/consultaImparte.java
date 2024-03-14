/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class consultaImparte extends Conexion {

    PreparedStatement ps = null;
    String sentenciaSQL;
    ResultSet rs;
    Imparte imparte;

    public boolean crearAsignacion(Imparte imparte) {
        Connection con = getConnection();
        sentenciaSQL = "INSERT INTO tbl_imparte (idCatedratico,idAsignatura,idHorario, estado)VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, imparte.getIdCatedratico());
            ps.setInt(2, imparte.getIdAsignatura());
            ps.setInt(3, imparte.getIdHorario());
            ps.setString(4, imparte.getEstado());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Asignacion creado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Asignacion no ha sido creado" + ex.getMessage());
            return false;
        } finally {
            closeConnection(con);
        }
    }

    public ArrayList<Imparte> leerTodosAsignaciones() {
        ArrayList lista_asignaciones = new ArrayList();
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_imparte";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                imparte = new Imparte();
                imparte.setIdImporte(rs.getInt(1));
                imparte.setIdCatedratico(rs.getInt(2));
                imparte.setIdAsignatura(rs.getInt(3));
                imparte.setIdHorario(rs.getInt(4));
                imparte.setEstado(rs.getString(5));
                lista_asignaciones.add(imparte);
            }
            closeConnection(con);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo leer lista_asignaciones" + ex.getMessage());
        }
        return lista_asignaciones;
    }

    public boolean buscarAsignacion(Imparte imparte) {
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_imparte WHERE idImparte=?";
        try {

            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, imparte.getIdImporte());
            rs = ps.executeQuery();
            if (rs.next()) {
                imparte.setIdImporte(rs.getInt(1));
                imparte.setIdCatedratico(rs.getInt(2));
                imparte.setIdAsignatura(rs.getInt(3));
                imparte.setIdHorario(rs.getInt(4));
                imparte.setEstado(rs.getString(5));
                return true;

            }
            return false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo leer datos asignacion" + e.getMessage());
            return false;
        } finally {
            closeConnection(con);
        }

    }

    public boolean modificarAsignacion(Imparte imparte) {
        Connection con = getConnection();
        sentenciaSQL = "UPDATE  tbl_imparte SET idCatedratico=?,idAsignatura=?,idHorario=? WHERE idImparte=?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, imparte.getIdCatedratico());
            ps.setInt(2, imparte.getIdAsignatura());
            ps.setInt(3, imparte.getIdHorario());
            ps.setInt(4, imparte.getIdImporte());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Asignacion actualizado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Asignacion no ha sido actualizado" + ex.getMessage());
            return false;
        } finally {
            closeConnection(con);
        }

    }

    public boolean eliminarAsignacion(Imparte imparte) {
        Connection con = getConnection();
        sentenciaSQL = "UPDATE  tbl_imparte SET estado=? WHERE idImparte=?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, "Inactivo");
            ps.setInt(2, imparte.getIdImporte());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Asignacion desactivado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Asignacion no ha sido desactivado" + ex.getMessage());
            return false;
        } finally {
            closeConnection(con);
        }
    }

    //*********************
    // Método para validar si un catedrático tiene otra asignatura en el mismo horario
    public boolean catedraticoTieneAsignaturaEnHorario(int idCatedratico, int idHorario) {
        Connection con = getConnection();
        try {
            String sql = "SELECT COUNT(*) FROM tbl_imparte WHERE idCatedratico = ? AND idHorario = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCatedratico);
            ps.setInt(2, idHorario);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al validar asignatura del catedrático en el horario: " + ex.getMessage());
        } finally {
            closeConnection(con);
        }
        return false;
    }

    // Método para validar si una asignatura tiene otro catedrático en el mismo horario
    public boolean asignaturaTieneCatedraticoEnHorario(int idAsignatura, int idHorario) {
        Connection con = getConnection();
        try {
            String sql = "SELECT COUNT(*) FROM tbl_imparte WHERE idAsignatura = ? AND idHorario = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idAsignatura);
            ps.setInt(2, idHorario);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al validar catedrático de la asignatura en el horario: " + ex.getMessage());
        } finally {
            closeConnection(con);
        }
        return false;
    }

    public ArrayList<Asignatura> obtenerAsignaturasDeCatedratico(int idCatedratico) {
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        Connection con = getConnection();
        String consulta = "SELECT a.* FROM tbl_asignaturas a INNER JOIN tbl_imparte i ON a.idAsignatura = i.idAsignatura WHERE i.idCatedratico = ?";
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, idCatedratico);
            rs = ps.executeQuery();
            while (rs.next()) {
                Asignatura asignatura = new Asignatura();
                asignatura.setCodigo(rs.getInt(1));
                asignatura.setNombre(rs.getString(2));
                asignaturas.add(asignatura);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return asignaturas;
    }

    public ArrayList<Catedratico> obtenerCatedraticosPorAsignatura(int idAsignatura) {
        ArrayList<Catedratico> catedraticos = new ArrayList<>();
        Connection con = getConnection();
        String consulta = "SELECT c.* FROM tbl_catedraticos c INNER JOIN tbl_imparte i ON c.idCatedratico = i.idCatedratico WHERE i.idAsignatura = ?";
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, idAsignatura);
            rs = ps.executeQuery();
            while (rs.next()) {
                Catedratico catedratico = new Catedratico();
                catedratico.setCodigo(rs.getInt(1));
                catedratico.setNombre(rs.getString(2));
                catedraticos.add(catedratico);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return catedraticos;
    }

    public ArrayList<Horario> obtenerHorariosPorAsignatura(int idAsignatura) {
        ArrayList<Horario> horarios = new ArrayList<>();
        Connection con = getConnection();
        String consulta = "SELECT h.* FROM tbl_horario h INNER JOIN tbl_imparte i ON h.idHorario = i.idHorario WHERE i.idAsignatura = ?";
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, idAsignatura);
            rs = ps.executeQuery();
            while (rs.next()) {
                Horario horario = new Horario();
                horario.setIdHorario(rs.getInt(1));
                horario.setDiaSemana(rs.getString(2));
                horario.setHoraInicio(rs.getTime(3));
                horario.setHoraFin(rs.getTime(4));
                // Agregar más campos si es necesario
                horarios.add(horario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return horarios;
    }
}
