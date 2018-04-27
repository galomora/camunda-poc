package ec.group.bits.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.omnifaces.cdi.ViewScoped;

import ec.group.bits.bpm.tasks.UserTaskListService;
import ec.group.bits.bpm.tasks.model.TaskWithInfo;

@Named
@ViewScoped
public class AdminTaskListController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private TaskService taskService;
//	@Inject
//	private UserUtil userUtil;
	@Inject
	private UserTaskListService userTaskListService;

	private String user, role, userToAssign;
	private Date creationDate;
	private Task task;
	private TaskWithInfo taskWithInfo;
	private List<Task> tasks;

	public void searchTasks() {
		this.tasks = this.userTaskListService.getTasks(user, role, creationDate);
	}

	public void selectTask() {
		taskWithInfo = this.userTaskListService.generateTaskGroups(task);
	}
	
	public void assignTask () {
		this.taskService.setAssignee(task.getId(), userToAssign);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getUserToAssign() {
		return userToAssign;
	}

	public void setUserToAssign(String userToAssign) {
		this.userToAssign = userToAssign;
	}

	public TaskWithInfo getTaskWithInfo() {
		return taskWithInfo;
	}

}
