package ec.group.bits.controller.model;

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
	
	public TaskWithInfo(Task task) {
		super();
		this.task = task;
	}
	public TaskWithInfo(Task task, String link) {
		super();
		this.task = task;
		this.link = link;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public String getLink() {
		System.out.println("el link " + link);
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
