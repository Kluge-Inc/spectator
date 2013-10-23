
<%@ page import="org.kluge.spectator.CategoryEntity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'categoryEntity.label', default: 'CategoryEntity')}" />
		<title>Список категорий</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create">Добавить категорию</g:link></li>
			</ul>
		</div>
		<div id="list-categoryEntity" class="content scaffold-list" role="main">
			<h2>Список категорий</h2>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>

						<g:sortableColumn property="name" title="${message(code: 'categoryEntity.name.label', default: 'Наименование')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${categoryEntityInstanceList}" status="i" var="categoryEntityInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${categoryEntityInstance.id}">${fieldValue(bean: categoryEntityInstance, field: "name")}</g:link></td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${categoryEntityInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
