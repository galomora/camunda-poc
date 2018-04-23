package ec.group.bits.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;

import ec.group.bits.controller.UserTaskListController;
import ec.group.bits.util.UserUtil;

@Model
public class UserTaskListService {
	
	private static final Logger LOG = Logger.getLogger(UserTaskListController.class.getName());
	
	@Inject
	private TaskService taskService;
	@Inject private UserUtil userUtil;
//	@Inject private TaskElementGenerator taskElementGenerator;
	
	public List<Task> getUnassignedTasks () {
		Set<String> roles = userUtil.getUserRoles();
//		List<Task> unassigned = new ArrayList<>();
//		roles.stream().forEach(role -> unassigned.addAll(getUnassignedTasksByRole (role)));
//		return unassigned;
		LOG.info("a consultar unassigned roles " + roles);
		List<Task> tareas = this.taskService.createTaskQuery().taskCandidateGroupIn(new ArrayList<>(roles)).initializeFormKeys().list();
		LOG.info("consultado size " + tareas.size());
		tareas.stream().forEach(tarea -> LOG.info("tarea " + tarea.toString()));
		return tareas;
	}
	
	public List<Task> getUnassignedTasksByRole (String roleName) {
//		RoleWithProcesses role = RoleWithProcesses.getRoleByName(roleName);
//		return this.taskService.createTaskQuery().processDefinitionKeyIn (role.getProcessesDefinitionNames()).taskUnassigned().list();
		return this.taskService.createTaskQuery().taskCandidateGroup(roleName).taskUnassigned().initializeFormKeys().list();
	}
	
	public List<Task> getAssignedTasks () {
		List<Task> tareas = this.taskService.createTaskQuery().taskAssignee(userUtil.getPreferredUserName()).initializeFormKeys().list();
		LOG.info("consultado asignadas size " + tareas.size());
		tareas.stream().forEach(tarea -> LOG.info("tarea " + tarea.toString()));
		return tareas;
	}
}
