# 行为附加 <!-- {docsify-ignore-all} -->

<% if (item.getAllPSDataEntities()) {
  item.getAllPSDataEntities().each{ entity ->
   if (entity.hasActionLogics()) { %>\
## [${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}.md)  :id=${entity.getCodeName()}

<% if (entity.getAllPSDEActions()) {
  entity.getAllPSDEActions().each{ action ->
   if (action.hasActionLogics()) { %>\
#### [${action.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}#行为) :id=${entity.getCodeName()}_${action.getName()}

<% if (action.getCheckPSDEActionLogics()) {%>\
<p class="panel-title"><b>检查</b></p>
<br>

<%  action.getCheckPSDEActionLogics().each{ actionlogic -> %>\
<%= ctx.output(actionlogic,'/module/action/action_logic.md.tpl') %>
<% }} %>
<% if (action.getPreparePSDEActionLogics()) {%>\
<p class="panel-title"><b>数据准备</b></p>
<br>

<%  action.getPreparePSDEActionLogics().each{ actionlogic -> %>\
<%= ctx.output(actionlogic,'/module/action/action_logic.md.tpl') %>
<% }} %>
<% if (action.getBeforePSDEActionLogics()) {%>\
<p class="panel-title"><b>操作之前</b></p>
<br>

<%  action.getBeforePSDEActionLogics().each{ actionlogic -> %>\
<%= ctx.output(actionlogic,'/module/action/action_logic.md.tpl') %>
<% }} %>
<% if (action.getAfterPSDEActionLogics()) {%>\
<p class="panel-title"><b>操作之后</b></p>
<br>

<%  action.getAfterPSDEActionLogics().each{ actionlogic -> %>\
<%= ctx.output(actionlogic,'/module/action/action_logic.md.tpl') %>\
<% }} %>
<% }}} %>
<% }}}%>





