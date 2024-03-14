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


public class consultaCatedraticos extends Conexion {

    PreparedStatement ps = null;
    String sentenciaSQL;
    ResultSet rs;
    Catedratico catedratico;

    public boolean crearCatedratico(Catedratico catedratico) {
        Connection con = getConnection();
        sentenciaSQL = "INSERT INTO tbl_catedraticos (idCatedratico, nombreCatedratico, direccionCatedratico, telefonoCatedratico, estado)VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, catedratico.getCodigo());
            ps.setString(2, catedratico.getNombre());
            ps.setString(3, catedratico.getDireccion());
            ps.setString(4, catedratico.getTelefono());
            ps.setString(5, catedratico.getEstado());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Catedratico creado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Catedratico no ha sido creado" + ex.getMessage());
            return false;
        }finally {
            closeConnection(con);
        }
    }


    public ArrayList<Catedratico> leerTodosCatedraticos() {
        ArrayList lista_catedraticos = new ArrayList();
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_catedraticos";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                catedratico = new Catedratico();
                catedratico.setCodigo(rs.getInt(1));
                catedratico.setNombre(rs.getString(2));
                catedratico.setDireccion(rs.getString(3));
                catedratico.setTelefono(rs.getString(4));
                catedratico.setEstado(rs.getString(5));
                lista_catedraticos.add(catedratico);
            }
            closeConnection(con);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo leer catedraticos" + ex.getMessage());
        }
        return lista_catedraticos;
    }
    


    public boolean buscarCatedratico(Catedratico catedratico) {
        Connection con = getConnection();
        sentenciaSQL = "SELECT * FROM tbl_catedraticos WHERE idCatedratico=?";
        try {
            
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, catedratico.getCodigo());
            rs = ps.executeQuery();
            if (rs.next()) {
                catedratico.setCodigo(rs.getInt(1));
                catedratico.setNombre(rs.getString(2));
                catedratico.setDireccion(rs.getString(3));
                catedratico.setTelefono(rs.getString(4));
                catedratico.setEstado(rs.getString(5));
                return true;

            }
            return false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo leer datos catedratico" + e.getMessage());
            return false;
        } finally {
            closeConnection(con);
        }
        
    }
    
    public boolean modificarCatedratico(Catedratico catedratico) {
        Connection con = getConnection();
        sentenciaSQL = "UPDATE  tbl_catedraticos SET nombreCatedratico=?, direccionCatedratico=?, telefonoCatedratico=? WHERE idCatedratico=?";
        
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, catedratico.getNombre());
            ps.setString(2, catedratico.getDireccion());
            ps.setString(3, catedratico.getTelefono());
        ps.setInt(4, catedratico.getCodigo());
            ps.execute();
            JOptionPane.showMessageDialog(null, "catedratico actualizado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "catedratico no ha sido actualizado" + ex.getMessage());
            return false;
        }finally {
            closeConnection(con);
        }
        
        
    }

    public boolean eliminarCatedratico(Catedratico catedratico) {
        Connection con = getConnection();
        sentenciaSQL = "UPDATE  tbl_catedraticos SET estado=? WHERE idCatedratico=?";
        
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, "Inactivo");
            ps.setInt(2, catedratico.getCodigo());
            ps.execute();
            JOptionPane.showMessageDialog(null, "catedratico desactivado correctamente");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "catedratico no ha sido desactivado" + ex.getMessage());
            return false;
        }finally {
            closeConnection(con);
        }
    }
    
    //*****************************************
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

     public Catedratico obtenerCatedratico(int id) {
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
        return catedratico;
    }
}
