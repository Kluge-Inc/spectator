
<%@ page import="org.kluge.spectator.Version" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'version.label', default: 'Version')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>

		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create">Добавить версию</g:link></li>
			</ul>
		</div>
		<div id="list-version" class="content scaffold-list" role="main">
			<h2>Список версий всех документов</h2>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="version.document.label" default="Документ" /></th>
					
						<g:sortableColumn property="name" title="${message(code: 'version.name.label', default: 'Номер версии')}" />
					
						<g:sortableColumn property="date" title="${message(code: 'version.date.label', default: 'Дата')}" />
					
						<g:sortableColumn property="file" title="${message(code: 'version.file.label', default: 'Файл')}" />
					
						<g:sortableColumn property="user" title="${message(code: 'version.user.label', default: 'Автор')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${versionInstanceList}" status="i" var="versionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${versionInstance.id}">${fieldValue(bean: versionInstance, field: "document.name")}</g:link></td>
					
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
