package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Item;
import java.util.*;

public interface ItemDAO {

   public void save(Item it) throws PersistenceException;
   public Item load(int id) throws PersistenceException;
   public List<Item> availableItems() throws PersistenceException; 
   public long consultarMultaAlquiler(int idItem, Date fechaDevolucion) throws PersistenceException;
   public long consultarCostoAlquiler(int idItem, int numDias) throws PersistenceException;
   
   public void actualizarTarifaItem(int idItem, long tarifa) throws PersistenceException;
}
