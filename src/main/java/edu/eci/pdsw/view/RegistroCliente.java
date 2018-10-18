package edu.eci.pdsw.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;


@SuppressWarnings("deprecation")
@ManagedBean(name = "registroClienteBean")
@RequestScoped
public class RegistroCliente extends BasePageBean {
	private static final long serialVersionUID = 3594009161252782831L;

	@Inject
	private ServiciosAlquiler servicesAlquiler;
	
	private String message;

	public List<Cliente> getData() throws Exception {
		return servicesAlquiler.consultarClientes();
	}
	
	public void registroUsuario(String nombre, long documento, String telefono, String direccion, String email){
		FacesContext context = FacesContext.getCurrentInstance(); 
		Cliente c  = new Cliente(nombre, documento, telefono, direccion, email);
		 try {
			System.out.println(servicesAlquiler.consultarCliente(12345));
			servicesAlquiler.registrarCliente(c);
			context.addMessage(null, new FacesMessage("Successful",  "Ha sido guardado sactifactoriamente"));
		 }catch (ExcepcionServiciosAlquiler e) {
			 e.getStackTrace();
			 context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal","No se pudo guardar sactifactoriamente"));
		 }
	}
	
	
	public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
     
    public void saveMessage() {
        //context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
    }
	
	
	/*
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	*/
}
