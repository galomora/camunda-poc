package ec.group.bits.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import ec.group.bits.service.OrderService;

@Named
@RequestScoped
public class OrderController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private OrderService orderService;
	
	public void saveOrder (DelegateExecution delegateExecution) {
		this.orderService.persistOrder(delegateExecution);
	}
}
