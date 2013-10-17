<%@ page import="org.kluge.spectator.CategoryEntity" %>



<div class="fieldcontain ${hasErrors(bean: categoryEntityInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="categoryEntity.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${categoryEntityInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryEntityInstance, field: 'documents', 'error')} ">
	<label for="documents">
		<g:message code="categoryEntity.documents.label" default="Documents" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${categoryEntityInstance?.documents?}" var="d">
    <li><g:link controller="document" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="document" action="create" params="['categoryEntity.id': categoryEntityInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'document.label', default: 'Document')])}</g:link>
</li>
</ul>

</div>

