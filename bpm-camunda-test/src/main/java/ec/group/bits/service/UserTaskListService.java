package ec.group.bits.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;

import ec.group.bits.util.UserUtil;

@Model
public class UserTaskListService {
	
	@Inject
	private TaskService taskService;
	@Inject private UserUtil userUtil;
//	@Inject private TaskElementGenerator taskElementGenerator;
	
	public List<Task> getUnassignedTasks () {
		Set<String> roles = userUtil.getUserRoles();
//		List<Task> unassigned = new ArrayList<>();
//		roles.stream().forEach(role -> unassigned.addAll(getUnassignedTasksByRole (role)));
//		return unassigned;
		return this.taskService.createTaskQuery().taskCandidateGroupIn(new ArrayList<>(roles)).list();
	}
	
	public List<Task> getUnassignedTasksByRole (String roleName) {
//		RoleWithProcesses role = RoleWithProcesses.getRoleByName(roleName);
//		return this.taskService.createTaskQuery().processDefinitionKeyIn (role.getProcessesDefinitionNames()).taskUnassigned().list();
		return this.taskService.createTaskQuery().taskCandidateGroup(roleName).taskUnassigned().list();
	}
	
	public List<Task> getAssignedTasks () {
		return this.taskService.createTaskQuery().taskAssignee(userUtil.getPreferredUserName()).list();
	}
}
