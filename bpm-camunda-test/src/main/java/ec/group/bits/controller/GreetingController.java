package ec.group.bits.controller;

import java.security.Principal;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;

@Named
@RequestScoped
public class GreetingController {

	private static final Logger LOG = Logger.getLogger(GreetingController.class.getName());

	// esto solo vale con ejbs
	// @Resource
	// private SessionContext sessionContext;
	@Inject
	private HttpServletRequest request;

	public void getUser () {

//		LOG.info("principal class 1 " + sessionContext.getCallerPrincipal().getClass().getName());
		LOG.info("principal class 2 " + request.getUserPrincipal().getClass().getName());
//		28dfb55e-2614-4747-bf7a-30dfe63e61cd id de keycloak
		LOG.info("principal name " + request.getUserPrincipal().getName());
		
		KeycloakPrincipal<KeycloakSecurityContext> userPrincipal;
//		principal = (KeycloakPrincipal) sessionContext.getCallerPrincipal();
		userPrincipal = (KeycloakPrincipal) request.getUserPrincipal();
		
//		28dfb55e-2614-4747-bf7a-30dfe63e61cd
		LOG.info("principal k name " + userPrincipal.getName());
//		string no util
		LOG.info("principal k idToken " + userPrincipal.getKeycloakSecurityContext().getIdTokenString());
//		string no util
		LOG.info("principal k token " + userPrincipal.getKeycloakSecurityContext().getTokenString());
//		avis
		LOG.info("principal k realm " + userPrincipal.getKeycloakSecurityContext().getRealm());
//		da NPE:
//		otherPrincipal.getKeycloakSecurityContext().getAuthorizationContext().getPermissions();
//		Galo
		LOG.info("principal token givenname " + userPrincipal.getKeycloakSecurityContext().getToken().getGivenName());
//		Galo Mora
		LOG.info("principal token name " + userPrincipal.getKeycloakSecurityContext().getToken().getName());
//		116fe725-6837-4b91-ac4f-6e8ae9d55c0f
		LOG.info("principal token id " + userPrincipal.getKeycloakSecurityContext().getToken().getId());
//		ESTE ES EL VERDADERO NOMBRE
		LOG.info("preferred username " + userPrincipal.getKeycloakSecurityContext().getToken().getPreferredUsername()); 
		
//		es nulo		
		if (userPrincipal.getKeycloakSecurityContext().getAuthorizationContext() != null) {
			LOG.info("principal context permissions " + userPrincipal.getKeycloakSecurityContext().getAuthorizationContext().getPermissions());
		} else {
			LOG.info("AuthorizationContext es nulo");
			}
//		es nulo
		if (userPrincipal.getKeycloakSecurityContext().getToken().getAuthorization() != null) {
			LOG.info("principal token permissions " + userPrincipal.getKeycloakSecurityContext().getToken().getAuthorization().getPermissions());
		} else {
			LOG.info("tToken().getAuthorization() es nulo");
		}
//		subject es nulo
		Subject subject;
		try {
			subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
			if (subject == null) {
				LOG.info("subject es nulo");
			}
			else {
				for (Principal principal : subject.getPrincipals()) {
				    LOG.info("In subject: " + principal.getName());
				}
			}
			
		} catch (PolicyContextException e) {
			LOG.severe("error policy "+ e.getMessage());
		}
		
		String clientId = "test";
		Set<String> roles ;
//		es nulo
		if (userPrincipal.getKeycloakSecurityContext().getToken().getResourceAccess(clientId) == null) {
			LOG.info("resourceaccess en realm es null ");
		} else {
			roles = userPrincipal.getKeycloakSecurityContext().getToken().getResourceAccess(clientId).getRoles();
			LOG.info("roles: " + roles);
		}
//		esto vale: roles: [external, uma_authorization]
		if (userPrincipal.getKeycloakSecurityContext().getToken().getRealmAccess() == null) {
			LOG.info("RealmAccess es null ");
		} else {
			roles = userPrincipal.getKeycloakSecurityContext().getToken().getRealmAccess().getRoles();
			LOG.info("roles: " + roles);
		}
		
		
//		roles si funciona
		LOG.info("rol warever " + request.isUserInRole("warever"));
		LOG.info("rol external " + request.isUserInRole("external"));
		LOG.info("rol manager " + request.isUserInRole("manager"));
	}
}
