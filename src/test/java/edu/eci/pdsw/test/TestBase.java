
import org.junit.Before;
import org.mybatis.guice.XMLMyBatisModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.eci.pdsw.sampleprj.dao.*;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.*;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.impl.ServiciosAlquilerImpl;



public class TestBase {
    protected Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
               setEnvironmentId("test");
               setClassPathResource("mybatis-config-h2.xml");                
               bind(ItemDAO.class).to(MyBATISItemDAO.class);
               bind(ClienteDAO.class).to(MyBATISClienteDAO.class);
               bind(TipoItemDAO.class).to(MyBATISTipoItemDAO.class);
               bind(ItemRentadoDAO.class).to(MyBATISItemRentadoDAO.class);       
               bind(ServiciosAlquiler.class).to(ServiciosAlquilerImpl.class);
            }
    });
    
    @Before
    public void setup() {
        injector.injectMembers(this);
    }
}