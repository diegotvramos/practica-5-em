
package com.emergentes.dao;

import com.emergentes.modelo.Proveedor;
import java.util.List;

public interface ProveedorDAO {
     public void insert(Proveedor proveedor) throws Exception;
    public void update(Proveedor proveedor) throws Exception;
    public void delete(int id) throws Exception;
    public Proveedor getById(int id) throws Exception;
    public List<Proveedor> getAll() throws Exception;
}
