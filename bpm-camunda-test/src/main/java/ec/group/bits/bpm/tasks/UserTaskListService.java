package ec.group.bits.bpm.tasks;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.engine.task.IdentityLinkType;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;

import ec.group.bits.bpm.tasks.model.TaskWithInfo;
import ec.group.bits.util.UserUtil;

@Model
public class UserTaskListService {
	
//	private static final Logger LOG = Logger.getLogger(UserTaskListController.class.getName());
	
	@Inject
	private TaskService taskService;
	@Inject private UserUtil userUtil;
	@Inject private TaskElementGenerator taskElementGenerator;
	
	public List<Task> getUnassignedTasks () {
		Set<String> roles = userUtil.getUserRoles();
		return this.taskService.createTaskQuery().taskCandidateGroupIn(new ArrayList<>(roles)).initializeFormKeys().list();

	}
	
	public List<Task> getUnassignedTasksByRole (String roleName) {
//		RoleWithProcesses role = RoleWithProcesses.getRoleByName(roleName);
//		return this.taskService.createTaskQuery().processDefinitionKeyIn (role.getProcessesDefinitionNames()).taskUnassigned().list();
		return this.taskService.createTaskQuery().taskCandidateGroup(roleName).taskUnassigned().initializeFormKeys().list();
	}
	
	public List<Task> getAssignedTasks () {
		return this.taskService.createTaskQuery().taskAssignee(userUtil.getPreferredUserName()).initializeFormKeys().list();
	}
	
	public List<Task> getTasks (String user, String role, Date creationDate) {
		TaskQuery query = this.taskService.createTaskQuery();
		if (user != null && ! user.isEmpty()) {
			query = query.taskAssignee(user);
		}
		if (role != null && ! role.isEmpty()) {
			query = query.taskCandidateGroup(role);
		}
		if (creationDate != null) {
			query = query.taskCreatedBefore(creationDate);
		}
		return query.list();
	}
	
	public List<String> getTaskGroups (Task task) {
		List<IdentityLink> identityLinks = taskService.getIdentityLinksForTask(task.getId());
		List<String> groups = new ArrayList<>(); 
		for (IdentityLink link : identityLinks) {
			if (IdentityLinkType.CANDIDATE.equals(link.getType()) && link.getGroupId() != null && ! link.getGroupId().isEmpty()) {
				groups.add(link.getGroupId());
			}
		}
		return groups;
	}
	
	public TaskWithInfo generateTaskLink (Task task) throws URISyntaxException {
		return taskElementGenerator.generateElementLink(task);
	}
	
	public TaskWithInfo generateTaskGroups (Task task) {
		return taskElementGenerator.generateElementGroups(task, this.getTaskGroups(task));
	}
}
