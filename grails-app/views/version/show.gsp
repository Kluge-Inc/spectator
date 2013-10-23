
<%@ page import="org.kluge.spectator.Version" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'version.label', default: 'Version')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-version" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-version" class="content scaffold-show" role="main">
			<h2><g:message code="default.show.label" args="[entityName]" /></h2>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list version">
			
				<g:if test="${versionInstance?.document}">
				<li class="fieldcontain">
					<span id="document-label" class="property-label"><g:message code="version.document.label" default="Document" /></span>
					
						<span class="property-value" aria-labelledby="document-label"><g:link controller="document" action="show" id="${versionInstance?.document?.id}">${versionInstance?.document?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${versionInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="version.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${versionInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${versionInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="version.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${versionInstance?.date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${versionInstance?.file}">
				<li class="fieldcontain">
					<span id="file-label" class="property-label"><g:message code="version.file.label" default="File" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${versionInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="version.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:fieldValue bean="${versionInstance}" field="user"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:versionInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${versionInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
