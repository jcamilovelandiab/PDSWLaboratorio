package edu.eci.pdsw.view;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;

@SuppressWarnings("deprecation")
@ManagedBean(name = "registroAlquilerBean")
@RequestScoped
public class Registroalquiler extends BasePageBean {


	private static final long serialVersionUID = 3594009161252782831L;
	
	@ManagedProperty(value = "#{param.documento}")
	private Long documento;
        

	@Inject
	private ServiciosAlquiler servicesAlquiler;

	public void getData() throws Exception {
		System.out.println(this.documento);
		//return servicesAlquiler.consultarClientes();
	}
	
	
	public Long getDocumento() {
		return this.documento;
	}
	
	public void setDocumento(long documento) {	
		this.documento = documento;
	}
	
}
