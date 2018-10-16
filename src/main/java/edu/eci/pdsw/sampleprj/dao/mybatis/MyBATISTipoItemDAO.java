package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.pdsw.sampleprj.dao.*;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.*;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.util.List;

public class MyBATISTipoItemDAO implements TipoItemDAO{

	@Inject
	private TipoItemMapper tipoItemMapper;
	
	@Override
	public void save(TipoItem tp) throws PersistenceException {
            try {
                    tipoItemMapper.addTipoItem(tp);
            } catch (org.apache.ibatis.exceptions.PersistenceException e) {
                    throw new PersistenceException("Error al registrar el item " + tp.toString());
            }
	}

	@Override
	public TipoItem load(int id) throws PersistenceException {
            try {
                return tipoItemMapper.getTipoItem(id);
            } catch (org.apache.ibatis.exceptions.PersistenceException e) {
                throw new PersistenceException("Error al consultar el tipo item " + id);
            }
	}

    @Override
    public List<TipoItem> loadAll() throws PersistenceException{
        try {
            return tipoItemMapper.getTiposItems();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar todos los tipos item");
        }
    }
	
}
