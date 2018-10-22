package edu.eci.pdsw.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.common.primitives.Ints;
import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.samples.entities.*;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import static edu.eci.pdsw.samples.services.client.MyBatisExample.getSqlSessionFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.guice.transactional.Transactional;
import static org.junit.Assert.*;

import org.quicktheories.core.Gen;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;



public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;   
    @Inject
    ServiciosAlquiler serviciosAlquiler;
    
    
    private SqlSessionFactory sessionfact;

    
    public static SqlSessionFactory getSqlSessionFactory() {
		SqlSessionFactory sqlSessionFactory = null;
		if (sqlSessionFactory == null) {
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream("mybatis-config-h2.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return sqlSessionFactory;
    }

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
        sessionfact = getSqlSessionFactory();
    }

    @Before
    public void setUp() {
    }
    
    @Test
    public void consultarValorMultaRetrasoxDia() {
    	qt().forAll(integers().allPositive()).check((idItem) ->{
    		boolean r = true;
    		try {
    			sqlSession = sessionfact.openSession();
    			Item item = serviciosAlquiler.consultarItem(idItem);
    			if(item!=null) {
    				long tarifaxdia = item.getTarifaxDia();
    			}
    			sqlSession.commit();
                sqlSession.close();
    		}catch(ExcepcionServiciosAlquiler e) {
    			//System.err.println(e.getMessage());
                r = false;
            } catch(IndexOutOfBoundsException e){
                r = false;
            }
    		return r;
    	});
    }
    
    
    @Test
    public void consultarClienteTest() {
        qt().forAll(longs().from(1).upTo(1000)).check((documento) -> {
            boolean r = true;
            try {
                sqlSession = sessionfact.openSession();
                Cliente cliente = serviciosAlquiler.consultarCliente(documento);
                sqlSession.commit();
                sqlSession.close();
            } catch(ExcepcionServiciosAlquiler e) {
                r = false;
            } catch(IndexOutOfBoundsException e){
            	
                r = false;
            }
            return r;
        });
    }
    
    @Test
    public void consultarItemsClienteTest() {
    	
        qt().forAll(longs().from(1).upTo(1000)).check((documento) -> {
            boolean r = true;
            try {
                sqlSession = sessionfact.openSession();
                Cliente cliente = serviciosAlquiler.consultarCliente(documento);
                if(cliente!=null) {
                	List<ItemRentado> lrentados=serviciosAlquiler.consultarItemsCliente(documento);
                }
                sqlSession.commit();
                sqlSession.close();
            } catch(ExcepcionServiciosAlquiler e) {
                r = false;
            } catch(IndexOutOfBoundsException e){
            	e.printStackTrace();
                r = false;
            }
            return r;
        });
        
    }
    
    
    @Test
    public void consultarItemsTest() {
    	qt().forAll(integers().allPositive()).check((idItem) ->{
    		boolean r = true;
    		try {
    			sqlSession = sessionfact.openSession();
    			Item item = serviciosAlquiler.consultarItem(idItem);
    			sqlSession.commit();
                sqlSession.close();
    		}catch(ExcepcionServiciosAlquiler e) {
    			System.err.println(e.getMessage());
                r = false;
            } catch(IndexOutOfBoundsException e){
                r = false;
            }
    		return r;
    	});
    }
    
    @Test
    public void consultarMultaAlquilerTest() {
    	qt().forAll(integers().allPositive(), dates().withMilliseconds(0)).check((idItem, fecha) ->{
    		boolean r = true;
    		try {
    			sqlSession = sessionfact.openSession();
    			Item item = serviciosAlquiler.consultarItem(idItem);
    			if(item!=null) {
    				if(fecha.after(item.getFechaLanzamiento())) {
    					long multa = serviciosAlquiler.consultarMultaAlquiler(idItem, (Date) fecha);
    				}
    			}
    			sqlSession.commit();
                sqlSession.close();
    		}catch(ExcepcionServiciosAlquiler e) {
    			System.err.println(e.getMessage());
                r = false;
            } catch(IndexOutOfBoundsException e){
                r = false;
            }
    		return r;
    	});
    }
    
    @Test
    public void consultarTiposItemTest() {
    	qt().forAll(integers().from(1).upTo(50)).check((idTipoItem) ->{
    		boolean r = true;
    		try {
    			sqlSession = sessionfact.openSession();
    			TipoItem ti = serviciosAlquiler.consultarTipoItem(idTipoItem);
    			sqlSession.commit();
                sqlSession.close();
    		}catch(ExcepcionServiciosAlquiler e) {
                r = false;
            } catch(IndexOutOfBoundsException e){
                r = false;
            }
    		return r;
    	});
    }
    
    @Transactional
    @Test
    public void registrarAlquilerClienteTest() {
    	qt().forAll(GeneratorCliente.clientes(), GeneratorItem.items(), integers().between(1, 20)).
    	check((cl, it, numDias)->{
    		boolean r = true;
    		try {
    			sqlSession = sessionfact.openSession();
    			
    			Cliente cliente = serviciosAlquiler.consultarCliente(cl.getDocumento());
    			//System.out.println("CONSULTO CLIENTE");
    			if(cliente==null) {
    				//System.out.println("REGISTRAR NUEVO CLIENTE");
    				cliente=cl; 
    				serviciosAlquiler.registrarCliente(cliente);
    				//System.out.println("REGISTRAR NUEVO CLIENTE COMPLETADO");
    			}
    			Item item = serviciosAlquiler.consultarItem(it.getId());
    			//System.out.println("CONSULTO ITEM");
    			if(item==null) {
    				item=it;
    				//System.out.println("REGISTRAR NUEVO ITEM");
    				serviciosAlquiler.registrarItem(item);
    				//System.out.println("REGISTRAR NUEVO ITEM COMPLETADO");
    			}
    			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    			
    			serviciosAlquiler.registrarAlquilerCliente(date, cliente.getDocumento(), item, numDias);
    			//System.out.println("REGISTRO SATISFACTORIO");
    			
    			sqlSession.commit();
                sqlSession.close();
    		}catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e){
                r = false;
            }
    		return r;
    	});
    }
    
    @Transactional
    @Test
    public void registrarClienteTest() {
    	qt().forAll(GeneratorCliente.clientes()).check((cliente)->{
    		boolean r = true;
    		try {
    			sqlSession = sessionfact.openSession();
    			serviciosAlquiler.registrarCliente(cliente);
    			sqlSession.commit();
                sqlSession.close();
    		}catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e){
                r = false;
            }
    		return r;
    	});
    }
    
    @Test
    public void consultarCostoAlquilerTest() {
    	qt().forAll(integers().allPositive(), integers().between(1, 10)).check((idItem, numDias)->{
    		boolean r = true;
    		try {
    			sqlSession = sessionfact.openSession();
    			long multa = serviciosAlquiler.consultarCostoAlquiler(idItem, numDias);
    			sqlSession.commit();
                sqlSession.close();
    		}catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e){
                r = false;
            }
    		return r;
    	});
    }
    
    @Transactional
    @Test
    public void actualizarTarifaItemTest() {
    	qt().forAll(integers().allPositive(), integers().allPositive()).check((idItem, tarifa)->{
    		boolean r = true;
    		try {
    			Item it = serviciosAlquiler.consultarItem(idItem);
    			if(it!=null) {
    				serviciosAlquiler.actualizarTarifaItem(idItem, tarifa);
    			}
    		}catch(ExcepcionServiciosAlquiler e) {
                r = false;
            } catch(IndexOutOfBoundsException e){
                r = false;
            }
    		return r;
    	});
    }
    
    @Transactional
    @Test
    public void registrarItemTest() {
    	qt().forAll(GeneratorItem.items()).assuming((item) -> {
			try {
				return serviciosAlquiler.consultarItem(item.getId())==null;
			} catch (ExcepcionServiciosAlquiler e1) {
				return false;
			}
		})
		.check((item) -> {
			boolean r = true;
    		try {
    			sqlSession = sessionfact.openSession();
    			Item it = serviciosAlquiler.consultarItem(item.getId());
    			if(it==null) {
    				//System.out.println(item.getId()+" ITEM NO REGISTRADO");
    				serviciosAlquiler.registrarItem(item);
        			//System.out.println("CONGRATS!!! REGISTRE UN ITEM!!");
    			}
    			
    			sqlSession.commit();
                sqlSession.close();
            
    		}catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e){
                r = false;
            }
    		return r;
	    });
    }
    
    @Transactional
    @Test
    public void vetarClienteTest() {
    	qt().forAll(integers().allPositive()).check((documento) -> {
			boolean r = true;
    		try {
    			sqlSession = sessionfact.openSession();
    			serviciosAlquiler.vetarCliente(documento, true);
    			
    			sqlSession.commit();
                sqlSession.close();
            
    		}catch(ExcepcionServiciosAlquiler e) {
                r = false;
            } catch(IndexOutOfBoundsException e){
                r = false;
            }
    		return r;
	    });
    }
}