/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import edu.eci.pdsw.sampleprj.dao.*;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.sql.Date;
import java.util.List;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 *
 * @author estudiante
 */
public class MyBATISItemRentadoDAO implements ItemRentadoDAO {
    
    @Inject
    ItemRentadoMapper itemRentadoMapper;

    @Override
    public void save(ItemRentado itemRentado) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemRentado load(int id) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemRentado> loadAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numDias) {
    	System.out.println("MYBATIS: : voy a alquilar un item");
        itemRentadoMapper.registrarAlquilerCliente( date,  docu,  item,  numDias);
    }
    
}
