package edu.eci.pdsw.view;

import java.util.List;
import java.sql.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

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
	
	private Long documento;
	private Cliente selectCliente;

	public List<Cliente> getData() throws Exception {
		return servicesAlquiler.consultarClientes();
	}
	
	public void registroUsuario(String nombre, long documento, String telefono, String direccion, String email){
		FacesContext context = FacesContext.getCurrentInstance(); 
		Cliente c  = new Cliente(nombre, documento, telefono, direccion, email);
		 try {
			servicesAlquiler.registrarCliente(c);
			context.addMessage(null, new FacesMessage("Successful",  "Ha sido guardado sactifactoriamente"));
		 }catch (ExcepcionServiciosAlquiler e) {
			 e.getStackTrace();
			 context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal","No se pudo guardar sactifactoriamente"));
		 }
	}
	
	
	public Cliente getSelectCliente() {
		return this.selectCliente;
	}
    
	public void setSelectCliente(Cliente selectCliente) {
		this.selectCliente = selectCliente;
	}
	
	public long getDocumento() {
		return this.selectCliente.getDocumento();
	}
	
	public void setDocumento(Long doc) {
		this.documento = doc;
	}
	  
    public void onRowSelect(SelectEvent event) {
    	this.documento =  ((Cliente) event.getObject()).getDocumento();
    }
 
    public void onRowUnselect(UnselectEvent event) {
       /* FacesMessage msg = new FacesMessage("Car Unselected", ((Cliente) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
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
