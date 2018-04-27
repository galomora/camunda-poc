package ec.group.bits.bpm.tasks.model;

import java.util.List;

import org.camunda.bpm.engine.task.Task;

/**
 * Tarea con informacion adicional requerida:
 * - enlace de acceso
 * @author galo
 *
 */
public class TaskWithInfo {
	private Task task;
	private String link;
	private List<String> groups;
	
	public TaskWithInfo(Task task) {
		super();
		this.task = task;
	}
	public TaskWithInfo(Task task, String link) {
		super();
		this.task = task;
		this.link = link;
	}
	
	public TaskWithInfo(Task task, List<String> groups) {
		super();
		this.task = task;
		this.groups = groups;
	}
	
	public String getGroupsCommaSeparated () {
		if (this.groups == null || this.groups.isEmpty()) { return null;}
		StringBuilder builder = new StringBuilder("");
		this.groups.stream().forEach(group -> {
			builder.append(group);
			builder.append(",");
			}
		);
		builder.deleteCharAt(builder.lastIndexOf(","));
		return builder.toString();
	}
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<String> getGroups() {
		return groups;
	}
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
	
}
