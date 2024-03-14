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

public class consultaHorarios extends Conexion {

    PreparedStatement ps = null;
    String sentenciaSQL;
    ResultSet rs;
    Horario horario;

    public boolean crearHorario(Horario horario) {
        Connection con = getConnection();
        sentenciaSQL = "INSERT INTO tbl_horario (dia_semana, hora_inicio, hora_fin, estado)VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, horario.getDiaSemana());
            ps.setTime(2, horario.getHoraInicio());
            ps.setTime(3, horario.getHoraFin());
            ps.setString(4, horario.getEstado());
            ps.execute();
            JOptionPane.showMessageDialog(null, "horario creado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Horario no ha sido creado" + ex.getMessage());
            return false;
        } finally {
            closeConnection(con);
        }
    }

    public ArrayList<Horario> leerTodosHorarios() {
        ArrayList lista_horarios = new ArrayList();
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_horario";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                horario = new Horario();
                horario.setIdHorario(rs.getInt(1));
                horario.setDiaSemana(rs.getString(2));
                horario.setHoraInicio(rs.getTime(3));
                horario.setHoraFin(rs.getTime(4));
                horario.setEstado(rs.getString(5));
                lista_horarios.add(horario);
            }
            closeConnection(con);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo leer catedraticos" + ex.getMessage());
        }
        return lista_horarios;
    }

    public boolean buscarHorario(Horario horario) {
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_horario WHERE idHorario=?";
        try {

            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, horario.getIdHorario());
            rs = ps.executeQuery();
            if (rs.next()) {
                horario.setIdHorario(rs.getInt(1));
                horario.setDiaSemana(rs.getString(2));
                horario.setHoraInicio(rs.getTime(3));
                horario.setHoraFin(rs.getTime(4));
                horario.setEstado(rs.getString(5));
                return true;

            }
            return false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo leer datos horarios" + e.getMessage());
            return false;
        } finally {
            closeConnection(con);
        }

    }

    public boolean modificarHorario(Horario horario) {
        Connection con = getConnection();
        sentenciaSQL = "UPDATE  tbl_horario SET dia_semana=?, hora_inicio=?, hora_fin=? WHERE idHorario=?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, horario.getDiaSemana());
            ps.setTime(2, horario.getHoraInicio());
            ps.setTime(3, horario.getHoraFin());
            ps.setInt(4, horario.getIdHorario());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Horario actualizado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Horario no ha sido actualizado" + ex.getMessage());
            return false;
        } finally {
            closeConnection(con);
        }

    }

    public boolean eliminarHorario(Horario horario) {
        Connection con = getConnection();
        sentenciaSQL = "UPDATE  tbl_horario SET estado=? WHERE idHorario=?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, "Inactivo");
            ps.setInt(2, horario.getIdHorario());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Horario desactivado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Horario no se logro desactivar" + ex.getMessage());
            return false;
        } finally {
            closeConnection(con);
        }
    }

    //*****************************************
    /*
    public String obtenerNombreCatedratico(int id) {
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_catedraticos WHERE idCatedratico = ?";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                catedratico = new Catedratico();
                catedratico.setCodigo(rs.getInt(1));
                catedratico.setNombre(rs.getString(2));
                catedratico.setDireccion(rs.getString(3));
                catedratico.setTelefono(rs.getString(4));
                catedratico.setEstado(rs.getString(5));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo buscar el catedratico" + ex.getMessage());
        } finally {
            closeConnection(con);
        }
        return catedratico.getNombre();
    }
*/
     public Horario obtenerHorario(int id) {
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_horario WHERE idHorario = ?";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                horario = new Horario();
                horario.setIdHorario(rs.getInt(1));
                horario.setDiaSemana(rs.getString(2));
                horario.setHoraInicio(rs.getTime(3));
                horario.setHoraFin(rs.getTime(4));
                horario.setEstado(rs.getString(5));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo buscar el horario" + ex.getMessage());
        } finally {
            closeConnection(con);
        }
        return horario;
    }
}
