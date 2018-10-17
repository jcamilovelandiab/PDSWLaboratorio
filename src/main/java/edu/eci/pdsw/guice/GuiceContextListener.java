package edu.eci.pdsw.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.eci.pdsw.sampleprj.dao.*;
import edu.eci.pdsw.sampleprj.dao.mybatis.*;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.impl.ServiciosAlquilerImpl;

public class GuiceContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.MySQL);
                setEnvironmentId("development");
                setClassPathResource("mybatis-config.xml");
                bind(ServiciosAlquiler.class).to(ServiciosAlquilerImpl.class);
                bind(ClienteDAO.class).to(MyBATISClienteDAO.class);
	        bind(ItemDAO.class).to(MyBATISItemDAO.class);                   
                bind(ItemRentadoDAO.class).to(MyBATISItemRentadoDAO.class);
                bind(TipoItemDAO.class).to(MyBATISTipoItemDAO.class);
            }
        });
        
           servletContextEvent.getServletContext().setAttribute(Injector.class.getName(), injector);
    }
}