package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;


import edu.eci.pdsw.samples.entities.Item;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(@Param("id") int id);
    
    public void insertarItem(@Param("item") Item it);

    public List<Item> consultarItemsDisponibles();
    
    public long consultarMultaAlquiler(@Param("idItem")int idItem, @Param("fechaDevolucion") java.util.Date fechaDevolucion);
    
    public long consultarCostoAlquiler(@Param("idItem")int idItem, @Param("numDias") int numDias);
    
    public void actualizarTarifaItem(@Param("idItem") int idItem,@Param("tarifa") long tarifa);
}
