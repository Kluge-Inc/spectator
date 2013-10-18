<g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}" />
<div class="panel panel-default">
  <div class="panel-heading">
    <g:if test="${edit}">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    </g:if>
    <g:else>
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
    </g:else>
  </div>
  <div class="panel-body">
    <g:if test="${flash.formMessage}">
    <div class="alert alert-info alert-dismissable" role="status"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>${flash.formMessage}</div>
    </g:if>
    <form role="form">
  
      <div class="form-group ${hasErrors(bean:documentInstance, field:'activeVersion','has-error' )}">
        <label for="activeVersion" class="control-label">
          <g:message code="document.activeVersion.label" default="Active Version" />
          
        </label>
        <g:select class="form-control" id="activeVersion" name="activeVersion.id" from="${org.kluge.Version.list()}" optionKey="id" value="${documentInstance?.activeVersion?.id}" noSelection="['null': '']"/>

        <g:hasErrors bean="${documentInstance}" field="activeVersion">
          <g:eachError bean="${documentInstance}" field="activeVersion">
          <span class="help-block"><g:message error="${it}"/></span>
          </g:eachError>
        </g:hasErrors>
      </div>
  
      <div class="form-group ${hasErrors(bean:documentInstance, field:'name','has-error' )}">
        <label for="name" class="control-label">
          <g:message code="document.name.label" default="Name" />
          <span class="required-indicator">*</span>
        </label>
        <g:textField class="form-control" placeholder="Enter Name" name="name" required="" value="${documentInstance?.name}"/>

        <g:hasErrors bean="${documentInstance}" field="name">
          <g:eachError bean="${documentInstance}" field="name">
          <span class="help-block"><g:message error="${it}"/></span>
          </g:eachError>
        </g:hasErrors>
      </div>
  
      <div class="form-group ${hasErrors(bean:documentInstance, field:'category','has-error' )}">
        <label for="category" class="control-label">
          <g:message code="document.category.label" default="Category" />
          <span class="required-indicator">*</span>
        </label>
        <g:select class="form-control" id="category" name="category.id" from="${org.kluge.CategoryEntity.list()}" optionKey="id" required="" value="${documentInstance?.category?.id}"/>

        <g:hasErrors bean="${documentInstance}" field="category">
          <g:eachError bean="${documentInstance}" field="category">
          <span class="help-block"><g:message error="${it}"/></span>
          </g:eachError>
        </g:hasErrors>
      </div>
  
      <div class="form-group ${hasErrors(bean:documentInstance, field:'versions','has-error' )}">
        <label for="versions" class="control-label">
          <g:message code="document.versions.label" default="Versions" />
          
        </label>
        
<ul class="one-to-many">
<g:each in="${documentInstance?.versions?}" var="v">
    <li><g:link controller="version" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="version" action="create" params="['document.id': documentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'version.label', default: 'Version')])}</g:link>
</li>
</ul>


        <g:hasErrors bean="${documentInstance}" field="versions">
          <g:eachError bean="${documentInstance}" field="versions">
          <span class="help-block"><g:message error="${it}"/></span>
          </g:eachError>
        </g:hasErrors>
      </div>
  
      <g:if test="${edit}">
      <g:hiddenField name="id" value="${documentInstance?.id}" />
      <g:hiddenField name="version" value="${documentInstance?.version}" />
      <g:submitToRemote class="btn btn-primary" url="[action: 'update']" update="[success:'form', failure:'form']" name="update" value="${message(code: 'default.button.update.label', default: 'Update')}" onSuccess="${remoteFunction(action:'list', update:'list', method:'GET')}"/>
      <g:field class="btn btn-default" type="reset" name="reset" value="${message(code: 'default.button.reset.label', default: 'Reset')}"/>
      <g:remoteLink class="btn btn-success" action="create" update="form" method="GET" ><g:message code="default.button.new.label" default="New"/></g:remoteLink>
      </g:if>
      <g:else>
      <g:submitToRemote class="btn btn-primary" url="[action: 'save']" update="[success:'form', failure:'form']" name="create" value="${message(code: 'default.button.create.label', default: 'Create')}" onSuccess="${remoteFunction(action:'list', update:'list', method:'GET')}"/>
      <g:field class="btn btn-default" type="reset" name="reset" value="${message(code: 'default.button.reset.label', default: 'Reset')}"/>
      </g:else>
    </form>
  </div>
</div>
