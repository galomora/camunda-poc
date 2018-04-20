package ec.group.bits.bpm.event;

import java.util.Collections;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class GenericStartProcessByMessageEvent implements JavaDelegate {
	
	private static final String VARIABLE_CALLING_PROCESS = "callingProcess";
	private static final String VARIABLE_CALLED_PROCESS = "calledProcess";
	
	/**
	 * Field Injection en las actividades de mensajes de inicio de proceso, debe corresponder el nombre messageName al field inyectado
	 * en la actividad en el esquema BPMN
	 */
	private Expression messageName;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Map<String, Object> variables = Collections.<String, Object>singletonMap(VARIABLE_CALLING_PROCESS,
				execution.getProcessInstanceId());
		RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
		
//		esta es otra opcion
//		runtimeService.correlateMessage(MESSAGE_NAME);
		final String messageNameString = (String) messageName.getValue(execution);
		ProcessInstance newProcess = runtimeService.startProcessInstanceByMessage(messageNameString, variables);
		execution.setVariable(VARIABLE_CALLED_PROCESS, newProcess.getId());
	}

	public void setMessageName(Expression messageName) {
		this.messageName = messageName;
	}

}
