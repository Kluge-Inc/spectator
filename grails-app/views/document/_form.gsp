<%@ page import="org.klug.spectator.Document" %>



<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'activeVersion', 'error')} ">
    <label for="activeVersion">
        <g:message code="document.activeVersion.label" default="Active Version"/>

    </label>
    <g:select id="activeVersion" name="activeVersion.id" from="${org.klug.spectator.Version.list()}" optionKey="id"
              value="${documentInstance?.activeVersion?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="document.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${documentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'category', 'error')} required">
    <label for="category">
        <g:message code="document.category.label" default="Category"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="category" name="category.id" from="${org.klug.spectator.CategoryEntity.list()}" optionKey="id"
              required="" value="${documentInstance?.category?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'versions', 'error')} ">
    <label for="versions">
        <g:message code="document.versions.label" default="Versions"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${documentInstance?.versions ?}" var="v">
            <li><g:link controller="version" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="version" action="create"
                    params="['document.id': documentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'version.label', default: 'Version')])}</g:link>
        </li>
    </ul>

</div>

