# 属性值规则 <!-- {docsify-ignore-all} -->

<% if (item.getAllPSDataEntities()) {
  item.getAllPSDataEntities().each{ entity -> %>\
<% if (entity.hasDEFValueRules()) { %>\

## [${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}.md)  :id=${entity.getCodeName()}
<%  entity.getAllPSDEFields().each{ field -> %>\
<%  if (field.hasDEFValueRules()) { %>\

<p class="panel-title"><b>${field.getLogicName()}(${field.getName()})</b></p>

<%     field.getAllPSDEFValueRules().each{ rule -> %>\
* [${rule.getName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}/value_rule/${field.getCodeName()}#${rule.getCodeName().toLowerCase()})<%if(rule.getMemo()){%><br>${rule.getMemo()}<%}%>
<% }} %>\
<% }} %>\

<% }}%>





