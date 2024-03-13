## ${item.getLogicName()} <!-- {docsify-ignore-all} -->

   <%= (item.getMemo()) ? item.getMemo() : "" %>

### 处理过程

```plantuml
@startuml
hide footbox
<style>
root {
  HyperlinkColor #42b983
}
</style>

${item.mdUmlInfo}

@enduml
```


### 处理步骤说明
<% if (item.getPSDEUILogicNodes()) {
  item.getPSDEUILogicNodes().each{ node -> %>\

#### ${node.getName()} :id=${node.getCodeName()}

<%= (node.getMemo()) ? node.getMemo() : "" %>

<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIActionLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIActionLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIAppendParamLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIAppendParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIBeginLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIBeginLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIBindParamLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIBindParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUICopyParamLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUICopyParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUICtrlFireEventLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUICtrlFireEventLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUICtrlInvokeLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUICtrlInvokeLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIDEActionLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIDEActionLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIDebugParamLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIDebugParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIDecisionLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIDecisionLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIDEDataSetLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIDEDataSetLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIDELogicLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIDELogicLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIEndLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIEndLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUILoopSubCallLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUILoopSubCallLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUILoopSubCallLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIMsgBoxLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIPFPluginLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIPFPluginLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIRawCodeLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIRawCodeLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIRenewParamLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIRenewParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIResetParamLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIResetParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUISortParamLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUISortParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUIThrowExceptionLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUIThrowExceptionLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUserLogic){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUserLogic.md.tpl') %><% }%>\
<% if (node.getLogicNodeType() == 'PREPAREJSPARAM'){ %><%= ctx.output(node,'/module/uilogic/node/IPSDEUILogicNode.md.tpl') %><% }%>\
<% }} %>\

<% if (item.getConditionLinks()) {%>\
### 连接条件说明
<% item.getConditionLinks().each{ link -> %>\
#### ${link.getName()} <%if(link.getPSDEUILogicLinkGroupCond().info){%>:id=${link.getSrcPSDEUILogicNode().getCodeName()}-${link.getDstPSDEUILogicNode().getCodeName()}<%}%>

${link.getPSDEUILogicLinkGroupCond().info}
<% }} %>

### 实体逻辑参数

|    中文名   |    代码名    |  数据类型      |备注 |
| --------| --------| --------  | --------   |
<% if (item.getPSDEUILogicParams()) {
  item.getPSDEUILogicParams().each{ param -> %>\
|${param.getLogicName()}<%if(param.isDefault()){%>(<i class="fa fa-check"/></i>)<%}%>|${param.getCodeName()}|${param.dataType}|<%= (param.getMemo()) ? param.getMemo() : "" %>|
<% }} %>