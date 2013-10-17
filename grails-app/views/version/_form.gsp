<%@ page import="org.kluge.spectator.Version" %>



<div class="fieldcontain ${hasErrors(bean: versionInstance, field: 'document', 'error')} required">
	<label for="document">
		<g:message code="version.document.label" default="Документ" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="document" name="document.id" from="${org.kluge.spectator.Document.list()}" optionKey="id" optionValue="name" required="" value="${versionInstance?.document?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: versionInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="version.name.label" default="Номер версии" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${versionInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: versionInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="version.date.label" default="Дата обновления" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${versionInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: versionInstance, field: 'file', 'error')} required">
	<label for="file">
		<g:message code="version.file.label" default="Файл" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="file" name="file" />
</div>

<div class="fieldcontain ${hasErrors(bean: versionInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="version.user.label" default="Автор версии" />
		
	</label>
	<g:textField name="user" value="${versionInstance?.user}"/>
</div>

