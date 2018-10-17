package edu.eci.pdsw.test;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.quicktheories.core.Gen;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;

public class test {

    @Inject
    private SqlSession sqlSession;    
    ServiciosAlquiler serviciosAlquiler;

    public test() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

    @Test
    public void emptyDB() {
        qt().forAll(longs().from(1).upTo(1000)).check((documento) -> {
            boolean r = true;
            System.out.println("sfajfaddahfdajkfakfahkfeahk");
            try {
                //sqlSession.getConnection();
                Cliente cliente = serviciosAlquiler.consultarCliente(12345);
                System.out.println("sfajfaddahfdajkfakfahkfeahk");
                //return true;
            } catch(ExcepcionServiciosAlquiler e) {
                r = false;
            } catch(IndexOutOfBoundsException e){
                r = false;
            }
            return r;
        });
    }
}