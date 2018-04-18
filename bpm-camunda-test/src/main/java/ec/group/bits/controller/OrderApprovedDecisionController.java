package ec.group.bits.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;

import ec.group.bits.service.OrderService;

@Named
@RequestScoped
public class OrderApprovedDecisionController {
	
	private static final Logger LOG = Logger.getLogger(OrderApprovedDecisionController.class.getName());
	
	@Inject
	OrderService orderService;
	
	@Inject
	private BusinessProcess businessProcess;
	
	public Boolean isApproved () {
		Long orderId = (Long) businessProcess.getVariable("orderId");
		LOG.log(Level.INFO, "id de order " + orderId);
		return this.orderService.getOrder(orderId).isApproved();
	}
}
