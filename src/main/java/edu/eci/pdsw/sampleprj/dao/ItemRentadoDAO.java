/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;        
import java.sql.Date;
import java.util.List;
/**
 *
 * @author estudiante
 */
public interface ItemRentadoDAO {
    public void save(ItemRentado itemRentado) throws PersistenceException;
    public ItemRentado load(int id) throws PersistenceException;
    public List<ItemRentado> loadAll() throws PersistenceException;
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numDias) throws PersistenceException;;
    
}
