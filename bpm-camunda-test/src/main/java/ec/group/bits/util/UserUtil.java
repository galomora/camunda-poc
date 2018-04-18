package ec.group.bits.util;

import java.util.Set;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

@Model
public class UserUtil {
	@Inject 
	HttpServletRequest request;
	
	@Produces
	public AccessToken getAccessToken() {
		return this.getUserPrincipal().getKeycloakSecurityContext().getToken();
	}
	
	private KeycloakPrincipal<KeycloakSecurityContext> getUserPrincipal () {
		KeycloakPrincipal<KeycloakSecurityContext> userPrincipal;
		userPrincipal = (KeycloakPrincipal) request.getUserPrincipal();
		return userPrincipal; 
	}
	
	/**
	 * Clave de usuario de Keycloak, es un key generado, no el username con el que ingresa, pero es el id real
	 * @return key generado
	 */
	public String getKeyUserName () {
		return this.getAccessToken().getName();
	} 
	
	/**
	 * Nombre de usuario con el que ingresa al sistema 
	 * @return
	 */
	public String getPreferredUserName () {
		return this.getAccessToken().getPreferredUsername();
	}
	
	public Set<String> getUserRoles () {
		return this.getUserPrincipal().getKeycloakSecurityContext().getToken().getRealmAccess().getRoles();
	}
}
