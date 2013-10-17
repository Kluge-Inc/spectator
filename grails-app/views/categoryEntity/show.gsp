
<%@ page import="org.kluge.spectator.CategoryEntity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'categoryEntity.label', default: 'CategoryEntity')}" />
		<title>Просмотр категори</title>
	</head>
	<body>
		<div id="show-categoryEntity" class="content scaffold-show" role="main">
			<h1>Просмотр категории</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list categoryEntity">
			
				<g:if test="${categoryEntityInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="categoryEntity.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${categoryEntityInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${categoryEntityInstance?.documents}">
				<li class="fieldcontain">
					<span id="documents-label" class="property-label"><g:message code="categoryEntity.documents.label" default="Documents" /></span>
					
						<g:each in="${categoryEntityInstance.documents}" var="d">
						<span class="property-value" aria-labelledby="documents-label"><g:link controller="document" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:categoryEntityInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${categoryEntityInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
