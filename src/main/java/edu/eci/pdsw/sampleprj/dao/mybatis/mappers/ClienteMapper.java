package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Cliente;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2129082 Y 2131608
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("docucli") long docu); 
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param l
     * @param idi
     * @param fechainicio
     * @param fechafin 
     */
    public void agregarItemRentadoACliente(@Param("docucli") long l,
    		@Param("itemRentadoIDIT")  int idi, 
            @Param("itemRentadoFechainicio") Date fechainicio,
            @Param("itemRentadoFechaFin") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */
    
    public void agregarCliente(@Param("cliente") Cliente cl);
    
    /**
     * @return la lista de todos los clientes
     */
    public List<Cliente> consultarClientes();
    
    /**
     * Veta a un cliente
     * @param docu
     * @param estado 
     */
    public void vetarCliente(@Param("docucli")long docu,@Param("estado") boolean estado);
    
}
