package edu.eci.pdsw.sampleprj.dao.mybatis;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.List;

public class MyBATISClienteDAO implements ClienteDAO{

	
    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public void save(Cliente cl) throws PersistenceException {
        try {
        		clienteMapper.agregarCliente(cl);
                for (ItemRentado ir : cl.getRentados()){
                        clienteMapper.agregarItemRentadoACliente(cl.getDocumento(),ir.getId(), ir.getFechainiciorenta(), ir.getFechafinrenta());
                }
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
                throw new PersistenceException("Error al registrar el cliente " + cl.toString());
        }
    }

    @Override
    public Cliente load(long docu) throws PersistenceException {
        try {
        		
                return clienteMapper.consultarCliente(docu);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
        		e.getMessage();
                throw new PersistenceException("Error al consultar1 el cliente " + docu);
        }
    }

    @Override
    public List<Cliente> loadAll() throws PersistenceException {
        try {
            return clienteMapper.consultarClientes();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los clientes");
        }
    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws PersistenceException {
        try {
            clienteMapper.vetarCliente(docu, estado);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al vetar el cliente "+docu);
        }
    }

	@Override
	public List<ItemRentado> consultarItemsCliente(long docuCliente) throws PersistenceException {
		try {
			return clienteMapper.consultarItemsCliente(docuCliente);
		}catch (org.apache.ibatis.exceptions.PersistenceException e) {
			throw new PersistenceException("Error al vetar el cliente "+docuCliente);
		}
	}

}
