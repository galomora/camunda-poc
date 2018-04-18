package ec.group.bits.service;

import java.io.IOException;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import ec.group.bits.model.Order;
import ec.group.bits.service.exception.BPMTaskException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OrderService {
	@PersistenceContext
	private EntityManager entityManager;

	// Inject task form available through the Camunda cdi artifact
	@Inject
	private TaskForm taskForm;

	public void persistOrder(DelegateExecution delegateExecution) {
		// Create new order instance
		Order order = new Order();

		// Get all process variables
		Map<String, Object> variables = delegateExecution.getVariables();

		// Set order attributes
		order.setCustomer((String) variables.get("customer"));
		order.setAddress((String) variables.get("address"));
		order.setPizza((String) variables.get("pizza"));

		/*
		 * Persist order instance and flush. After the flush the id of the order
		 * instance is set.
		 */
		entityManager.persist(order);
		entityManager.flush();

		// Remove no longer needed process variables
		delegateExecution.removeVariables(variables.keySet());

		// Add newly created order id as process variable
		delegateExecution.setVariable("orderId", order.getId());
	}

	public Order getOrder(Long id) {
		return entityManager.find(Order.class, id);
	}

	public void mergeOrderAndCompleteTask(Order order) throws BPMTaskException {
		this.entityManager.merge(order);
		try {
			taskForm.completeTask();
		} catch (IOException e) {
			throw new BPMTaskException (e);
		}

	}
}
