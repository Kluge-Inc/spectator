
<%@ page import="org.klug.spectator.Version" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'version.label', default: 'Version')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-version" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-version" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="version.document.label" default="Document" /></th>
					
						<g:sortableColumn property="name" title="${message(code: 'version.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="date" title="${message(code: 'version.date.label', default: 'Date')}" />
					
						<g:sortableColumn property="file" title="${message(code: 'version.file.label', default: 'File')}" />
					
						<g:sortableColumn property="user" title="${message(code: 'version.user.label', default: 'User')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${versionInstanceList}" status="i" var="versionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${versionInstance.id}">${fieldValue(bean: versionInstance, field: "document")}</g:link></td>
					
						<td>${fieldValue(bean: versionInstance, field: "name")}</td>
					
						<td><g:formatDate date="${versionInstance.date}" /></td>
					
						<td>${fieldValue(bean: versionInstance, field: "file")}</td>
					
						<td>${fieldValue(bean: versionInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${versionInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
