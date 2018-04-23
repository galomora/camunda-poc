package ec.group.bits.bpm.tasks;

import java.net.URISyntaxException;
import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.utils.URIBuilder;
import org.camunda.bpm.engine.form.FormData;
import org.camunda.bpm.engine.task.Task;

@Model
public class TaskURIUtil {
	
	private static final Logger LOG = Logger.getLogger(TaskURIUtil.class.getName());
	
	@Inject
	HttpServletRequest request;
	
	@Inject
	FormData formData;
	
	public String generateTaskFormURI (Task task) throws URISyntaxException {
		
//		"../.." + contextPath (of process application) + "/" + "app" + formKey (from BPMN 2.0 XML) + "processDefinitionKey=" + processDefinitionKey + "&callbackUrl=" + callbackUrl;
		LOG.info("inicial " + request.getContextPath() + "/" + task.getFormKey());
		URIBuilder builder = new URIBuilder(request.getContextPath() + "/" + task.getFormKey());
//		builder.addParameter("processDefinitionKey", task.getProcessDefinitionId());
		builder.addParameter("taskId", task.getId());
		builder.addParameter("callbackUrl", request.getRequestURL().toString());
		builder.addParameter("facesRedirect", "true");
		LOG.info("resultado " + builder.build().toString());
		return builder.build().toString() ;
	}
	
	public String replaceAppKeyword (String formKey) {
		String contextPath =  request.getContextPath() + "/";
		return replaceAppKeyword(formKey, contextPath);
	}
	
	public String replaceAppKeyword (String formKey, String contextPath) {
		return formKey.replace("app:", contextPath);
	}
}
