package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.*;
import edu.eci.pdsw.samples.entities.*;
import edu.eci.pdsw.samples.services.*;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   private ClienteDAO clienteDAO;
   private TipoItemDAO tipoItemDAO;
   private ItemRentadoDAO itemRentadoDAO;
   
   @Override
   public long valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler{
       try {
           return itemDAO.load(itemId).getTarifaxDia();
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el valor de la multa por dia del item "+itemId);
       }
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
       try {
           return clienteDAO.load(docu);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el cliente "+docu);
       }
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long docuCliente) throws ExcepcionServiciosAlquiler {
       try {
           return clienteDAO.consultarItemsCliente(docuCliente);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el cliente "+docuCliente);
       }
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try {
            return clienteDAO.loadAll();
         } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los clientes");
        }
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id);
       }
   }

    @Override
    public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
        try {
            return itemDAO.availableItems();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los items disponibles");
        }
    }

   
   @Override
   public long consultarMultaAlquiler(int idItem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.consultarMultaAlquiler(idItem, fechaDevolucion);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar la multa del alquiler");
        }
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return tipoItemDAO.load(id);
        }catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el tipo item");
        }
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        try {
            return tipoItemDAO.loadAll();
        }catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los tipos item");
        }
   }

   @Override
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numDias) throws ExcepcionServiciosAlquiler {
        try {
            itemRentadoDAO.registrarAlquilerCliente(date, docu,  item,  numDias);
        }catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar el alquiler del cliente");
        }
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
        try {
            clienteDAO.save(c);
        }catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar el cliente");
        }
   }

   @Override
   public long consultarCostoAlquiler(int idItem, int numDias) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.consultarCostoAlquiler(idItem, numDias);
        }catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el costo del alquiler "+idItem);
        }
   }

   @Override
    public void actualizarTarifaItem(int idItem, long tarifa) throws ExcepcionServiciosAlquiler {
        try {
            itemDAO.actualizarTarifaItem(idItem, tarifa);
        }catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al actualizar la tarifa del item "+idItem);
        }
   }
   @Override
   public void registrarItem(Item it) throws ExcepcionServiciosAlquiler {
        try {
            itemDAO.save(it);
        }catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar el item");
        }
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       try {
            clienteDAO.vetarCliente(docu,estado);
        }catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al vetar el cliente "+docu);
        }
   }
}
