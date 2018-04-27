package ec.group.bits.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.group.bits.util.DatesUtil;

public class BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	DatesUtil datesUtil;

	public void adicionarMensajeGlobal(Severity tipo, String cuerpoMensaje) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, cuerpoMensaje, cuerpoMensaje));
	}

	public void adicionarMensaje(String idComponente, Severity tipo, String cuerpoMensaje) {
		FacesContext.getCurrentInstance().addMessage(idComponente,
				new FacesMessage(tipo, cuerpoMensaje, cuerpoMensaje));
	}

	public void adicionarMensajeError(String idComponente, String cuerpoMensaje) {
		adicionarMensaje(idComponente, FacesMessage.SEVERITY_ERROR, cuerpoMensaje);
		// adicionarMensajeGlobal (FacesMessage.SEVERITY_ERROR , cuerpoMensaje);
	}

	public void adicionarMensajeError(String cuerpoMensaje) {
		adicionarMensajeGlobal(FacesMessage.SEVERITY_ERROR, cuerpoMensaje);
	}

	public void adicionarMensajeInfo(String idComponente, String cuerpoMensaje) {
		adicionarMensaje(idComponente, FacesMessage.SEVERITY_INFO, cuerpoMensaje);
		// adicionarMensajeGlobal (FacesMessage.SEVERITY_ERROR , cuerpoMensaje);
	}

	public void adicionarMensajeInfo(String cuerpoMensaje) {
		adicionarMensajeGlobal(FacesMessage.SEVERITY_INFO, cuerpoMensaje);
	}
	
	public String getCurrentDate () {
		return datesUtil.getDate();
	}

}
