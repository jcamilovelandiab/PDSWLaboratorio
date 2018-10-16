package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MyBATISItemDAO implements ItemDAO {

	@Inject
	private ItemMapper itemMapper;

	@Override
	public void save(Item it) throws PersistenceException {
            try {
                    itemMapper.insertarItem(it);
            } catch (org.apache.ibatis.exceptions.PersistenceException e) {
                    throw new PersistenceException("Error al registrar el item " + it.toString());
            }

	}

	@Override
	public Item load(int id) throws PersistenceException {
            try {
                    return itemMapper.consultarItem(id);
            } catch (org.apache.ibatis.exceptions.PersistenceException e) {
                    throw new PersistenceException("Error al consultar el item " + id);
            }
	}

    @Override
    public List<Item> availableItems() throws PersistenceException {
        try {
            return itemMapper.consultarItemsDisponibles();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los items disponibles");
        }
    }
    
    @Override
    public long consultarMultaAlquiler(int idItem, java.util.Date fechaDevolucion) throws PersistenceException {
        try {
            return itemMapper.consultarMultaAlquiler(idItem,fechaDevolucion);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar la multa del alquiler del item "+idItem);
        }
    }

    @Override
    public long consultarCostoAlquiler(int idItem, int numDias) throws PersistenceException {
        try {
            return itemMapper.consultarCostoAlquiler(idItem,numDias);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el costo del alquiler del item "+idItem);
        }
    }

    @Override
    public void actualizarTarifaItem(int idItem, long tarifa) throws PersistenceException {
        try {
            itemMapper.actualizarTarifaItem(idItem,tarifa);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al actualizar la tarifa del item "+idItem);
        }
    }

}
