package ec.group.bits.bpm.process;

public enum ProcessName {
	PIZZA_MANAGER ("pizza_manager"), PIZZA_EXTERNAL ("pizza_external");
	private String processDefinitionName;

	private ProcessName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}
	
}
