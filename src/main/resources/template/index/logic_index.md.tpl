# 处理逻辑 <!-- {docsify-ignore-all} -->
<% if (item.getAllPSDataEntities()) {
  item.getAllPSDataEntities().each{ entity -> %>\
<% if (entity.getAllPSDELogics()) { %>\

## [${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}.md) :id=${entity.getCodeName()}

<%  entity.getAllPSDELogics().each{ logic -> %>\
* [${logic.getDisplayName(true)}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}/logic/${logic.getCodeName()}.md)<%if(logic.getMemo()){%><br>${logic.getMemo()}<%}%>
<% }} %>\
<% }}%>