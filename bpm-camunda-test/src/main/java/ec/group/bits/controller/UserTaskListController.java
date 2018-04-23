package ec.group.bits.controller;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
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
	@Inject private UserTaskListService userTaskListService;
	@Inject private TaskElementGenerator taskElementGenerator;
	
	private List<Task> unassignedTasks;
	private List<Task> assignedTasks;
	private Task assignedTask;
	private Task unassignedTask;
	private TaskWithInfo assignedTaskWithInfo;
	private TaskWithInfo unassignedTaskWithInfo;
	
	private static final Logger LOG = Logger.getLogger(UserTaskListController.class.getName());
	
	@PostConstruct
	public void initUserTaskListController () {
		LOG.info("inicializa UserTaskListController busca no asignadas");
		searchUnassignedTasks ();
		LOG.info("inicializa UserTaskListController busca asignadas");
		searchAssignedTasks ();
	}
	
	public void searchUnassignedTasks () {
		this.unassignedTasks = userTaskListService.getUnassignedTasks();
	}
	
	public void searchAssignedTasks () {
		this.assignedTasks = userTaskListService.getAssignedTasks();
	}
	
	public void claimTask () throws URISyntaxException {
		this.taskService.claim(unassignedTask.getId(), userUtil.getPreferredUserName());
		
	}
	
	public void unclaimTask () throws URISyntaxException {
		this.taskService.claim(assignedTask.getId(), null);
	}
	
	public void selectAssignedTask () throws URISyntaxException {
		LOG.info("la asignada " + assignedTask.getId());
		this.assignedTaskWithInfo = taskElementGenerator.generateElement(assignedTask);
	}
	
	public void selectUnassignedTask () throws URISyntaxException {
		LOG.info("la no asignada " + unassignedTask.getId());
		this.unassignedTaskWithInfo = taskElementGenerator.generateElement(unassignedTask);
	}
	
	//getters setters
	
	public Task getAssignedTask() {
		return assignedTask;
	}

	public void setAssignedTask(Task assignedTask) {
		this.assignedTask = assignedTask;
	}

	public Task getUnassignedTask() {
		return unassignedTask;
	}

	public void setUnassignedTask(Task unassignedTask) {
		this.unassignedTask = unassignedTask;
	}

	public List<Task> getUnassignedTasks() {
		return unassignedTasks;
	}

	public List<Task> getAssignedTasks() {
		return assignedTasks;
	}

	public TaskWithInfo getAssignedTaskWithInfo() {
		return assignedTaskWithInfo;
	}

	public TaskWithInfo getUnassignedTaskWithInfo() {
		return unassignedTaskWithInfo;
	}

}
