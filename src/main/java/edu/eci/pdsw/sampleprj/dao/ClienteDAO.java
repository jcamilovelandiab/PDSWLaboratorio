package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.*;
import java.util.*;

public interface ClienteDAO {
    public void save(Cliente cl) throws PersistenceException;
    public Cliente load(long docu) throws PersistenceException;
    public List<Cliente> loadAll() throws PersistenceException;
    public void vetarCliente(long docu, boolean estado) throws PersistenceException;
	public List<ItemRentado> consultarItemsCliente(long docuCliente) throws PersistenceException;
}
