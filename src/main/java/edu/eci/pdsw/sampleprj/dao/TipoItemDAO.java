package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.*;
import java.util.List;

public interface TipoItemDAO {
    public void save(TipoItem tp) throws PersistenceException;
    public TipoItem load(int id) throws PersistenceException;
    public List<TipoItem> loadAll() throws PersistenceException;
}