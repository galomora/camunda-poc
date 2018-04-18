package ec.group.bits.controller;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;

import ec.group.bits.model.Order;
import ec.group.bits.service.OrderService;
import ec.group.bits.service.exception.BPMTaskException;

@Named
@ConversationScoped
public class ApproveOrderController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(ApproveOrderController.class.getName());

	// Inject the BusinessProcess to access the process variables
	@Inject
	private BusinessProcess businessProcess;

	@Inject
	private OrderService orderService;

	// Caches the OrderEntity during the conversation
	private Order order;

	public Order getOrder() {
		if (order == null) {
			// Load the order entity from the database if not already cached
			order = orderService.getOrder((Long) businessProcess.getVariable("orderId"));
		}
		return order;
	}
	
	public void save () {
		// Persist updated order entity and complete task form
		try {
			orderService.mergeOrderAndCompleteTask(order);
		} catch (BPMTaskException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
