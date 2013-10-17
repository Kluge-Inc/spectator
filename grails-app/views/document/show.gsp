<%@ page import="org.kluge.spectator.Document" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="create" action="create">Добавить документ</g:link></li>
    </ul>
</div>

<div id="show-document" class="content scaffold-show" role="main">
    <h1>Просмотр документа</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list document">

        <g:if test="${documentInstance?.activeVersion}">
            <li class="fieldcontain">
                <span id="activeVersion-label" class="property-label"><g:message code="document.activeVersion.label"
                                                                                 default="Active Version"/></span>

                <span class="property-value" aria-labelledby="activeVersion-label"><g:link controller="version"
                                                                                           action="show"
                                                                                           id="${documentInstance?.activeVersion?.id}">${documentInstance?.activeVersion?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${documentInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="document.name.label"
                                                                        default="Наименование:"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${documentInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${documentInstance?.category}">
            <li class="fieldcontain">
                <span id="category-label" class="property-label"><g:message code="document.category.label"
                                                                            default="Категория:"/></span>

                <span class="property-value" aria-labelledby="category-label"><g:link controller="categoryEntity"
                                                                                      action="show"
                                                                                      id="${documentInstance?.category?.id}">${documentInstance?.category?.name}</g:link></span>

            </li>
        </g:if>

        <g:if test="${documentInstance?.versions}">
            <li class="fieldcontain">
                <span id="versions-label" class="property-label"><g:message code="document.versions.label"
                                                                            default="Версии документа"/></span>

                <g:each in="${documentInstance.versions}" var="v">
                    <span class="property-value" aria-labelledby="versions-label"><g:link controller="version"
                                                                                          action="show"
                                                                                          id="${v.name}">${v?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

    </ol>
    <g:form url="[resource: documentInstance, action: 'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${documentInstance}"><g:message
                    code="default.button.edit.label" default="Редактировать"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
