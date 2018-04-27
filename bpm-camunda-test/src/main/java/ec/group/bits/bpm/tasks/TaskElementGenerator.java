package ec.group.bits.bpm.tasks;

import java.net.URISyntaxException;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.camunda.bpm.engine.task.Task;

import ec.group.bits.bpm.tasks.model.TaskWithInfo;

@Model
public class TaskElementGenerator {
	
	@Inject private TaskURIUtil taskURIUtil;
	
	/**
	 * Genera un objeto con la tarea y el link de acceso
	 * @param task
	 * @return
	 * @throws URISyntaxException
	 */
	public TaskWithInfo generateElementLink (Task task) throws URISyntaxException {
		TaskWithInfo element = new TaskWithInfo(task);
		element.setLink(taskURIUtil.generateTaskFormURI (task));
		return element;
	}
	
	/**
	 * Genera un objeto con la tarea y los grupos/roles asociados
	 * @param task
	 * @return
	 * @throws URISyntaxException
	 */
	public TaskWithInfo generateElementGroups (Task task, List<String> groups) {
		TaskWithInfo element = new TaskWithInfo(task, groups);
		return element;
	}
}
