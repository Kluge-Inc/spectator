<%@ page import="org.kluge.spectator.Document" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}"/>
    <title>Список документов</title>
</head>
<body>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="create" action="create">Добавить документ</g:link></li>
    </ul>
</div>

<div id="list-document" class="content scaffold-list" role="main">
    <h1>Список документов</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <th><g:message code="document.activeVersion.label" default="Текущая версия"/></th>

            <g:sortableColumn property="name" title="${message(code: 'document.name.label', default: 'Наименование')}"/>

            <th><g:message code="document.category.label" default="Категория"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${documentInstanceList}" status="i" var="documentInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${documentInstance.id}">${fieldValue(bean: documentInstance, field: "activeVersion.name")}</g:link></td>

                <td>${fieldValue(bean: documentInstance, field: "name")}</td>

                <td>${fieldValue(bean: documentInstance, field: "category.name")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${documentInstanceCount ?: 0}"/>
    </div>
</div>
</body>
</html>
