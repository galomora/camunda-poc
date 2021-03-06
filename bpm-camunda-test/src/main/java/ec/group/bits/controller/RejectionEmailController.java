package ec.group.bits.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;

import ec.group.bits.model.Order;
import ec.group.bits.service.OrderService;

@Named
@ConversationScoped
public class RejectionEmailController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(PrepareOrderController.class.getName());

	// Inject the BusinessProcess to access the process variables
	@Inject
	private BusinessProcess businessProcess;

	@Inject
	private OrderService orderService;

	// Caches the OrderEntity during the conversation
	private Order order;

	@PostConstruct
	public void initPrepareOrderController() {
		if (order == null) {
			// Load the order entity from the database if not already cached
			LOG.info("Buscar order");
			order = orderService.getOrder((Long) businessProcess.getVariable("orderId"));
		}
	}
	
	public void sendRejectionEmail () {
		LOG.info("Your order with id " + order.getId() + " pizza: " + order.getPizza() + " has been rejected");
	}
}
