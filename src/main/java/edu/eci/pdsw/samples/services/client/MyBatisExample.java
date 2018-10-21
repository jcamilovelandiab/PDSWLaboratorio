/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.services.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.util.ToDateParser;

import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

	/**
	 * Método que construye una fábrica de sesiones de MyBatis a partir del archivo
	 * de configuración ubicado en src/main/resources
	 *
	 * @return instancia de SQLSessionFactory
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		SqlSessionFactory sqlSessionFactory = null;
		if (sqlSessionFactory == null) {
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream("mybatis-config.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return sqlSessionFactory;
	}

	/**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     * @throws ParseException 
     */
    public static void main(String args[]) throws SQLException, ParseException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession();

   
        
        /* 	Punto 3 
         *  ClienteMapper cm =sqlss.getMapper(ClienteMapper.class);
         *	SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-M-yyyy");
         * String strdateInicio = "02-04-2011";
	     *  Date newdateInicio = dateformat2.parse(strdateInicio);
	     *   String strdateFin = "02-05-2015";
	     *  Date newdateFin = dateformat2.parse(strdateInicio);
	     *  cm.agregarItemRentadoACliente(22,1321,newdateInicio,newdateFin);
        */
        
        
        /*
         * Punto 4
         *      ItemMapper im = sqlss.getMapper(ItemMapper.class);
         *		Item newItem;
         *		TipoItem  tipoItem = new TipoItem(15,"Soy el nuevo Item de John Prueba1");
         * 		SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-M-dd");
    	 *		String strdateInicio = "2011-02-04";
    	 * 		Date newdateStart = dateformat2.parse(strdateInicio);
         *		newItem = new Item(tipoItem,103,"JohnItem1","SoyElNuevo",newdateStart,11111110, "FormatoRenta","M");
         *		im.insertarItem(newItem);
         * 		System.out.println(im.consultarItems());
         * 
         */
    
        /*
        SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-M-dd");
    	String strdateInicio = "3917-04-30";
    	Date newdateStart = dateformat2.pare(strdateInicio);
        ItemRentadoMapper im = sqlss.getMapper(ItemRentadoMapper.class);
        //System.out.println(im.consultarCostoAlquiler(90,5));
        //im.addTipoItem(new TipoItem(15,));
        ItemMapper IT = sqlss.getMapper(ItemMapper.class);
        ClienteMapper cl = sqlss.getMapper(ClienteMapper.class);
        Item mm = IT.consultarItem(17);
        System.out.println(cl.consultarClientes());
        //im.registrarAlquilerCliente(newdateStart, Long.parseLong("12345"),mm,6);
        */
        
        ServiciosAlquilerFactory serviceA =   ServiciosAlquilerFactory.getInstance();
        ServiciosAlquiler service = serviceA.getServiciosAlquilerTesting();
        try {
                System.out.println(service.consultarCostoAlquiler(2131608, 3));
            } catch (ExcepcionServiciosAlquiler ex) {
                Logger.getLogger(MyBatisExample.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    
 
        sqlss.commit();
        sqlss.close();

        
        
    }

}
