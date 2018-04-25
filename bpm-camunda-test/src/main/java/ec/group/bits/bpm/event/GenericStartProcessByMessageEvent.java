package ec.group.bits.bpm.event;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class GenericStartProcessByMessageEvent implements JavaDelegate {
	
	private static final String VARIABLE_CALLING_PROCESS = "callingProcess";
	private static final String VARIABLE_CALLED_PROCESS = "calledProcess";
	
	private static final Logger LOG = Logger.getLogger(GenericStartProcessByMessageEvent.class.getName());
	
	/**
	 * Field Injection en las actividades de mensajes de inicio de proceso, debe corresponder el nombre messageName al field inyectado
	 * en la actividad en el esquema BPMN
	 */
	private Expression messageName;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
		
//		esta es otra opcion
//		runtimeService.correlateMessage(MESSAGE_NAME);
		final String messageNameString = (String) messageName.getValue(execution);
		ProcessInstance newProcess = runtimeService.startProcessInstanceByMessage(messageNameString, createVariablesMap(execution));
		execution.setVariable(VARIABLE_CALLED_PROCESS, newProcess.getId());
	}

	public void setMessageName(Expression messageName) {
		this.messageName = messageName;
	}
	
	private Map<String, Object> createVariablesMap (DelegateExecution execution) {
		
		Map<String, Object> variables = new HashMap ();
//				Collections.<String, Object>singletonMap(VARIABLE_CALLING_PROCESS, execution.getProcessInstanceId());
		variables.put(VARIABLE_CALLING_PROCESS, execution.getProcessInstanceId());
		LOG.info("order id en actividad " + execution.getVariable("orderId"));
		LOG.info("order id en proceso " + execution.getProcessInstance().getVariable("orderId"));
		LOG.info("actividad id instance" + execution.getActivityInstanceId() + " current " + execution.getCurrentActivityId() + " name " + execution.getCurrentActivityName()
		+ " id " + execution.getId());
		LOG.info("process id " + execution.getProcessInstance().getId() + " name " + execution.getProcessInstance().getCurrentActivityName());
		variables.put("orderId", execution.getProcessInstance().getVariable("orderId"));
		return variables;
	}

}
