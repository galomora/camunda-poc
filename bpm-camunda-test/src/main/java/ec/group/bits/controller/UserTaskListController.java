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
	private Boolean claimed, unclaimed;
	
	
	private static final Logger LOG = Logger.getLogger(UserTaskListController.class.getName());
	
	@PostConstruct
	public void initUserTaskListController () {
		searchUnassignedTasks ();
		searchAssignedTasks ();
		claimed = Boolean.FALSE;
		unclaimed = Boolean.FALSE;
	}
	
	public void searchUnassignedTasks () {
		this.unassignedTasks = userTaskListService.getUnassignedTasks();
	}
	
	public void searchAssignedTasks () {
		this.assignedTasks = userTaskListService.getAssignedTasks();
	}
	
	public void claimTask () throws URISyntaxException {
		this.taskService.claim(unassignedTask.getId(), userUtil.getPreferredUserName());
		claimed = true;
	}
	
	public void unclaimTask () throws URISyntaxException {
		this.taskService.claim(assignedTask.getId(), null);
		 unclaimed = Boolean.TRUE;
	}
	
	public void selectAssignedTask () throws URISyntaxException {
		unclaimed = Boolean.FALSE;
		this.assignedTaskWithInfo = taskElementGenerator.generateElement(assignedTask);
	}
	
	public void selectUnassignedTask () throws URISyntaxException {
		claimed = Boolean.FALSE;
		this.unassignedTaskWithInfo = taskElementGenerator.generateElement(unassignedTask);
	}
	
	public String goToAssignedTask () {
		if (this.assignedTaskWithInfo == null) {
			return "#";
		} else {
			return this.assignedTaskWithInfo.getLink();
		}
	}
	
	public String goToUnassignedTask () {
		if (this.unassignedTaskWithInfo == null) {
			return "#";
		} else {
			return this.unassignedTaskWithInfo.getLink();
		}
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

	public Boolean getClaimed() {
		return claimed;
	}

	public Boolean getUnclaimed() {
		return unclaimed;
	}
	
	

}
