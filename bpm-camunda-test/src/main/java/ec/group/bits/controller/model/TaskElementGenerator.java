package ec.group.bits.controller.model;

import java.net.URISyntaxException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.camunda.bpm.engine.task.Task;

import ec.group.bits.bpm.tasks.TaskURIUtil;

@Model
public class TaskElementGenerator {
	
	@Inject private TaskURIUtil taskURIUtil;
	
	/**
	 * Genera un objeto con la tarea y el link de acceso
	 * @param task
	 * @return
	 * @throws URISyntaxException
	 */
	public TaskWithInfo generateElement (Task task) throws URISyntaxException {
		TaskWithInfo element = new TaskWithInfo(task);
		element.setLink(taskURIUtil.generateTaskFormURI (task));
		return element;
	}
}
