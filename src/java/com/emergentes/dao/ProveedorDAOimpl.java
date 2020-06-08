
package com.emergentes.dao;

import com.emergentes.modelo.Proveedor;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAOimpl extends ConexionBD implements ProveedorDAO {

    @Override
    public void insert(Proveedor proveedor) throws Exception {
         try {
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement("INSERT into proveedores (nombre,antiguedad,estado) values (?, ?,?)");
        ps.setString(1, proveedor.getNombre());
        ps.setInt(2, proveedor.getAntiguedad());
        ps.setBoolean(3, proveedor.isEstado());
        ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void update(Proveedor proveedor) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE proveedores set nombre = ?, antiguedad = ?, estado=? WHERE id = ?");
            ps.setString(1, proveedor.getNombre());
            ps.setInt(2,proveedor.getAntiguedad());
            ps.setBoolean(3, proveedor.isEstado());
            ps.setInt(4, proveedor.getId());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM proveedores where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public Proveedor getById(int id) throws Exception {
        Proveedor pro=new Proveedor();
        
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM proveedores where id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                pro.setId(id);
                pro.setNombre(rs.getString("nombre"));
                pro.setAntiguedad(rs.getInt("antiguedad"));
                pro.setEstado(rs.getBoolean("estado"));
            }
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return pro;
    }

    @Override
    public List<Proveedor> getAll() throws Exception {
     List<Proveedor> lista = null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM proveedores");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Proveedor>();
            while(rs.next()){
                Proveedor pro= new Proveedor();
                pro.setId(rs.getInt("id"));
                pro.setNombre(rs.getString("nombre"));
                pro.setAntiguedad(rs.getInt("antiguedad"));
                pro.setEstado(rs.getBoolean("estado"));
                lista.add(pro);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            throw e;
        } finally{
            this.desconectar();
        }
        return lista;
    }
    
}
