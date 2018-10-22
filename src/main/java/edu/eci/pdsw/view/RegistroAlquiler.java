package edu.eci.pdsw.view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import edu.eci.pdsw.samples.entities.*;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;

@SuppressWarnings("deprecation")
@ManagedBean(name = "registroAlquilerBean")
@RequestScoped
public class RegistroAlquiler extends BasePageBean {


	private static final long serialVersionUID = 3594009161252782831L;
	
	@ManagedProperty(value = "#{param.documento}")
	private Long documento;
        

	
	
	@Inject
	private ServiciosAlquiler servicesAlquiler;

	public List<ItemRentado> getData() throws Exception {
		return servicesAlquiler.consultarItemsCliente(documento);
	}
	


	public void getCostoJS(int id,int numDias) {
		RequestContext rc = RequestContext.getCurrentInstance();
	     try {
	    	System.out.println("......");
			rc.addCallbackParam("precioCosto", servicesAlquiler.consultarCostoAlquiler(id, numDias));
		} catch (ExcepcionServiciosAlquiler e) {
			e.printStackTrace();
		}
	}
	

	public void registrarAlquiler(int idItem, int numDias) {

		System.out.println("Registro de alquiler es"+ idItem+  " " + numDias);
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			Item item = servicesAlquiler.consultarItem(idItem);
			servicesAlquiler.registrarAlquilerCliente(date, documento, item, numDias);
			context.addMessage(null, new FacesMessage("Successful",  "El Item "+item.getNombre()+" ha sido alquilado sactifactoriamente"));
			
		}catch (ExcepcionServiciosAlquiler e) {
			 e.getStackTrace();
			 context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal","No se pudo guardar sactifactoriamente"));
		}
	}
	
	public long costoMultaAlquler(int idItem)throws ExcepcionServiciosAlquiler{
		try {
			long res = 0;
			if(servicesAlquiler.consultarItem(idItem) != null) {
				Date fechaDevolucion = Date.valueOf(LocalDate.now());
				res = servicesAlquiler.consultarMultaAlquiler(idItem, fechaDevolucion);
			}
			return res;
			
		} catch (ExcepcionServiciosAlquiler e) {
			e.printStackTrace();
			throw new ExcepcionServiciosAlquiler("Consto Alquiler/RegistroAlquiler ERROR");
		}
	}
	
	public long consultarCostoAlquiler(int idItem, int numDias) {
		System.out.println("Costo de alquiler es"+ idItem+  " " + numDias);
		FacesContext context = FacesContext.getCurrentInstance();
		long costoAlquiler=-1;
		try {
			costoAlquiler=servicesAlquiler.consultarCostoAlquiler(idItem, numDias);
		} catch (ExcepcionServiciosAlquiler e) {
			e.printStackTrace();
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal","No se pudo mirar el costo del alquiler"));
		}
		return costoAlquiler;
	}
	
	public Long getDocumento() {
		return this.documento;
	}
	
	public void setDocumento(Long doc) {
		this.documento = doc;
	}
	
}
