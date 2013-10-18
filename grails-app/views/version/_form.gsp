<g:set var="entityName" value="${message(code: 'version.label', default: 'Version')}" />
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
  
      <div class="form-group ${hasErrors(bean:versionInstance, field:'document','has-error' )}">
        <label for="document" class="control-label">
          <g:message code="version.document.label" default="Document" />
          <span class="required-indicator">*</span>
        </label>
        <g:select class="form-control" id="document" name="document.id" from="${org.kluge.Document.list()}" optionKey="id" required="" value="${versionInstance?.document?.id}"/>

        <g:hasErrors bean="${versionInstance}" field="document">
          <g:eachError bean="${versionInstance}" field="document">
          <span class="help-block"><g:message error="${it}"/></span>
          </g:eachError>
        </g:hasErrors>
      </div>
  
      <div class="form-group ${hasErrors(bean:versionInstance, field:'name','has-error' )}">
        <label for="name" class="control-label">
          <g:message code="version.name.label" default="Name" />
          <span class="required-indicator">*</span>
        </label>
        <g:textField class="form-control" placeholder="Enter Name" name="name" required="" value="${versionInstance?.name}"/>

        <g:hasErrors bean="${versionInstance}" field="name">
          <g:eachError bean="${versionInstance}" field="name">
          <span class="help-block"><g:message error="${it}"/></span>
          </g:eachError>
        </g:hasErrors>
      </div>
  
      <div class="form-group ${hasErrors(bean:versionInstance, field:'date','has-error' )}">
        <label for="date" class="control-label">
          <g:message code="version.date.label" default="Date" />
          <span class="required-indicator">*</span>
        </label>
        <g:datePicker name="date" precision="day"  value="${versionInstance?.date}"  />

        <g:hasErrors bean="${versionInstance}" field="date">
          <g:eachError bean="${versionInstance}" field="date">
          <span class="help-block"><g:message error="${it}"/></span>
          </g:eachError>
        </g:hasErrors>
      </div>
  
      <div class="form-group ${hasErrors(bean:versionInstance, field:'file','has-error' )}">
        <label for="file" class="control-label">
          <g:message code="version.file.label" default="File" />
          <span class="required-indicator">*</span>
        </label>
        <input type="file" id="file" name="file" />

        <g:hasErrors bean="${versionInstance}" field="file">
          <g:eachError bean="${versionInstance}" field="file">
          <span class="help-block"><g:message error="${it}"/></span>
          </g:eachError>
        </g:hasErrors>
      </div>
  
      <div class="form-group ${hasErrors(bean:versionInstance, field:'user','has-error' )}">
        <label for="user" class="control-label">
          <g:message code="version.user.label" default="User" />
          
        </label>
        <g:textField class="form-control" placeholder="Enter User" name="user" value="${versionInstance?.user}"/>

        <g:hasErrors bean="${versionInstance}" field="user">
          <g:eachError bean="${versionInstance}" field="user">
          <span class="help-block"><g:message error="${it}"/></span>
          </g:eachError>
        </g:hasErrors>
      </div>
  
      <g:if test="${edit}">
      <g:hiddenField name="id" value="${versionInstance?.id}" />
      <g:hiddenField name="version" value="${versionInstance?.version}" />
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
