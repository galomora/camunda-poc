package ec.group.bits.controller;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.omnifaces.cdi.ViewScoped;

import ec.group.bits.controller.model.TaskElementGenerator;
import ec.group.bits.controller.model.TaskWithInfo;
import ec.group.bits.service.UserTaskListService;
import ec.group.bits.util.UserUtil;

@Named
@ViewScoped
public class UserTaskListController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TaskService taskService;
	@Inject private UserUtil userUtil;
	@Inject private TaskElementGenerator taskElementGenerator;
	@Inject private UserTaskListService userTaskListService;
	
	private TaskWithInfo taskWithInto;
	
	public void initUserTaskListController () {
		
	}
	
	public List<Task> getUnassignedTasks () {
		return userTaskListService.getUnassignedTasks();
	}
	
	public List<Task> getAssignedTasks () {
		return userTaskListService.getUnassignedTasks();
	}
	
	public void claimTask (Task task) throws URISyntaxException {
		this.taskService.claim(task.getId(), userUtil.getPreferredUserName());
		this.selectTask(task);
	}
	
	public void selectTask (Task task) throws URISyntaxException {
		taskWithInto = taskElementGenerator.generateElement(task);
	}

	//getters setters
	
	public TaskWithInfo getTaskWithInto() {
		return taskWithInto;
	}

	
}
