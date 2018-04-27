package ec.group.bits.util;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Model
public class URIUtil {
	@Inject 
	HttpServletRequest request;
	
	public String getCallbackURI () {
		return request.getRequestURL().toString();
	}
}
