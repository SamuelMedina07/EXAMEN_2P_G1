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


public class consultaAsignaturas extends Conexion {

    PreparedStatement ps = null;
    String sentenciaSQL;
    ResultSet rs;
    Asignatura asignatura;

    public boolean crearAsignatura(Asignatura asignatura) {
        Connection con = getConnection();
        sentenciaSQL = "INSERT INTO tbl_asignaturas ( nombreAsignatura, estado)VALUES (?,?)";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, asignatura.getNombre());
            ps.setString(2, asignatura.getEstado());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Asignatura creado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Asignatura no ha sido creado" + ex.getMessage());
            return false;
        }finally {
            closeConnection(con);
        }
    }


    public ArrayList<Asignatura> leerTodosAsignaturas() {
        ArrayList lista_asignaturas = new ArrayList();
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_asignaturas";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                asignatura = new Asignatura();
                asignatura.setCodigo(rs.getInt(1));
                asignatura.setNombre(rs.getString(2));
                asignatura.setEstado(rs.getString(3));
                lista_asignaturas.add(asignatura);
            }
            closeConnection(con);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo leer asignaturas" + ex.getMessage());
        }
        return lista_asignaturas;
    }
    


    public boolean buscarAsignatura(Asignatura asignatura) {
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_asignaturas WHERE idAsignatura=?";
        try {
            
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, asignatura.getCodigo());
            rs = ps.executeQuery();
            if (rs.next()) {
                asignatura.setCodigo(rs.getInt(1));
               asignatura.setNombre(rs.getString(2));
                asignatura.setEstado(rs.getString(3));
                return true;

            }
            return false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo leer datos asignatura" + e.getMessage());
            return false;
        } finally {
            closeConnection(con);
        }
        
    }
    
    public boolean modificarAsignatura(Asignatura asignatura) {
        Connection con = getConnection();
        sentenciaSQL = "UPDATE  tbl_asignaturas SET nombreAsignatura=? WHERE idAsignatura=?";
        
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, asignatura.getNombre());
            ps.setInt(2, asignatura.getCodigo());
            ps.execute();
            JOptionPane.showMessageDialog(null, "asignatura actualizado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "asignatura no ha sido actualizado" + ex.getMessage());
            return false;
        }finally {
            closeConnection(con);
        }
        
        
    }

    public boolean eliminarAsignatura(Asignatura asignatura) {
        Connection con = getConnection();
        sentenciaSQL = "UPDATE  tbl_asignaturas SET estado=? WHERE idAsignatura=?";
        
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, "Inactivo");
            ps.setInt(2, asignatura.getCodigo());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Asignatura desactivado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Asignatura no ha sido desactivado" + ex.getMessage());
            return false;
        }finally {
            closeConnection(con);
        }
    }
    //********
    public String obtenerNombreAsignatura(int id) {
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_asignaturas WHERE idAsignatura = ?";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                asignatura = new Asignatura();
                asignatura.setCodigo(rs.getInt(1));
                asignatura.setNombre(rs.getString(2));
                asignatura.setEstado(rs.getString(3));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo buscar el producto" + ex.getMessage());
        } finally {
            closeConnection(con);
        }
        return asignatura.getNombre();
    }

    public Asignatura obtenerAsignatura(int id) {
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_asignaturas WHERE idAsignatura = ?";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                asignatura = new Asignatura();
                asignatura.setCodigo(rs.getInt(1));
                asignatura.setNombre(rs.getString(2));
                asignatura.setEstado(rs.getString(3));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo buscar el producto" + ex.getMessage());
        } finally {
            closeConnection(con);
        }
        return asignatura;
    }
}
