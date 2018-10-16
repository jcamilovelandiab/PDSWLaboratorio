/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;
import edu.eci.pdsw.samples.entities.Item;
import java.sql.Date;

/**
 *
 * @author estudiante
 */
public interface ItemRentadoMapper{

    /**
     *
     * @param date
     * @param docu
     * @param item
     * @param numDias
     */
    public void registrarAlquilerCliente(@Param("date")Date date,
                                        @Param("docu")long docu,
                                        @Param("item")Item item,
                                        @Param("numDias")int numDias);
    
}
