<%@ page import="org.kluge.spectator.Document" %>



<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'activeVersion', 'error')} ">
    <label for="activeVersion">
        <g:message code="document.activeVersion.label" default="Текущая версия"/>

    </label>
    <g:select id="activeVersion" name="activeVersion.id" from="${org.kluge.spectator.Version.list()}" optionKey="id"
              value="${documentInstance?.activeVersion?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="document.name.label" default="Название"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${documentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'category', 'error')} required">
    <label for="category">
        <g:message code="document.category.label" default="Категория"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="category" name="category.id" from="${org.kluge.spectator.CategoryEntity.list()}" optionKey="id"
              optionValue="name" required="" value="${documentInstance?.category?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'versions', 'error')} ">
    <label for="versions">
        <g:message code="document.versions.label" default="Версии документа"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${documentInstance?.versions ?}" var="v">
            <li><g:link controller="version" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="version" action="create"
                    params="['document.id': documentInstance?.id]">добавить версию</g:link>
        </li>
    </ul>

</div>

