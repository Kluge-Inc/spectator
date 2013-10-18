<g:set var="entityName" value="${message(code: 'version.label', default: 'Version')}" />
<div class="panel panel-default">
  <div class="panel-heading">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
  </div>
  <div class="panel-body">
    <g:if test="${flash.listMessage}">
    <div class="alert alert-info alert-dismissable" role="status"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>${flash.listMessage}</div>
    </g:if>
    <div class="table-responsive">
      <table class="table table-striped">
        <thead>
          <tr>
            
            <th><g:message code="version.document.label" default="Document" /></th>
            
            <util:remoteSortableColumn property="name" title="${message(code: 'version.name.label', default: 'Name')}" action="list" update="list" method="GET" params="${params}"/>
            
            <util:remoteSortableColumn property="date" title="${message(code: 'version.date.label', default: 'Date')}" action="list" update="list" method="GET" params="${params}"/>
            
            <th><g:message code="default.options.label" default="Options" /></th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${items}" status="i" var="versionInstance">
          <tr>
            
            <td>${fieldValue(bean: versionInstance, field: "document")}</td>
            
            <td>${fieldValue(bean: versionInstance, field: "name")}</td>
            
            <td><g:formatDate date="${versionInstance.date}" /></td>
            
            <td>
              <g:remoteLink action="edit" id="${versionInstance.id}" update="form" method="GET"><span class="label label-success"><span class="glyphicon glyphicon-eye-open"></span></span></g:remoteLink>
              <g:remoteLink action="delete" id="${versionInstance.id}" update="content" before="if(confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}') == false) return false"><span class="label label-danger"><span class="glyphicon glyphicon-remove"></span></span></g:remoteLink>
            </td>
          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
    <util:remotePaginate total="${total}" action="list" update="list" method="GET"/>
  </div>
</div>
