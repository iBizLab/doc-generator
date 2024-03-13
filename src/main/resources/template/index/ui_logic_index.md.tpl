# 界面逻辑 <!-- {docsify-ignore-all} -->
<% if (item.getAllPSDataEntities()) {
  item.getAllPSDataEntities().each{ entity -> %>\
<% if (entity.getAllPSAppDEUILogics()) { %>\

## [${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}.md) :id=${entity.getCodeName()}

<%  entity.getAllPSAppDEUILogics().each{ uilogic -> %>\
* [${uilogic.getDisplayName(true)}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}/uilogic/${uilogic.getCodeName()}.md)<%if(uilogic.getMemo()){%><br>${uilogic.getMemo()}<%}%>
<% }} %>\
<% }}%>