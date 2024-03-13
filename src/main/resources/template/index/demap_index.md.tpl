# 实体映射 <!-- {docsify-ignore-all} -->
<% if (item.getAllPSDataEntities()) {
  item.getAllPSDataEntities().each{ entity -> %>\
<% if (entity.getAllPSDEMaps()) { %>\

## [${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}.md) :id=${entity.getCodeName()}

<%  entity.getAllPSDEMaps().each{ demap -> %>\
* [${demap.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}/demap/${demap.getCodeName()}.md)<%if(demap.getMemo()){%><br>${demap.getMemo()}<%}%>
<% }} %>\
<% }}%>