package ec.group.bits.bpm.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum RoleWithProcesses {
//	TODO si vale lo de groups borrar esta enum
	EXTERNAL ("external", new ProcessName[] {ProcessName.PIZZA_EXTERNAL}), 
	MANAGER ("manager", new ProcessName[] {ProcessName.PIZZA_MANAGER});
	
	private String roleName;
	private ProcessName[] processes;

	private RoleWithProcesses(String roleName, ProcessName[] processes) {
		this.roleName = roleName;
		this.processes = processes;
	}

	public String getRoleName() {
		return roleName;
	}

	public ProcessName[] getProcesses() {
		return processes;
	}
	
	public String[] getProcessesDefinitionNames () {
		List<String> names = new ArrayList<String>();
		Arrays.asList(this.processes).stream().forEach(processName -> names.add(processName.getProcessDefinitionName()));
		return (String []) names.toArray();
	}
	
	public static RoleWithProcesses getRoleByName (String name) {
		return Arrays.asList(RoleWithProcesses.values()).stream().filter(profile -> profile.getRoleName().equals(name)).findFirst().get();
	}
}
