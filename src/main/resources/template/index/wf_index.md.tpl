## 流程配置 <!-- {docsify-ignore-all} -->

|  中文名      |   代码名  |  引擎类型 |  版本数量 |  关联实体     |  备注  |
|  --------   |------------| -----   |  :-----:   |  -----   |  -------- |
<% if (item.getAllPSWorkflows()) {
  item.getAllPSWorkflows().each{ workflow -> %>\
|[${workflow.getName()}](workflow/${workflow.getCodeName()}.md)|${workflow.getCodeName()}|<%= ctx.text('WFEngineType',workflow.getWFEngineType())%>|<%= workflow.getPSWFVersions()==null?0:workflow.getPSWFVersions().size() %>|<% if (workflow.getPSWFDEs()) {workflow.getPSWFDEs().each{ wfde -> %>[${wfde.getPSDataEntity().getDisplayName()}](module/${wfde.getPSDataEntity().getPSSystemModule().getCodeName()}/${wfde.getPSDataEntity().getCodeName()}.md) <%}}%>|<%= ctx.tableString(workflow.getMemo()) %>|
<% }} %>
