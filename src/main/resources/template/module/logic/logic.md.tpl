## ${item.getLogicName()} <!-- {docsify-ignore-all} -->

   <%= (item.getMemo()) ? item.getMemo() : "" %>

<%if (item.isCustomCode()){ %>\
### 逻辑处理脚本

```
${item.getScriptCode()}
```
<%}else{%>\
### 处理过程

```plantuml
@startuml
hide empty description
<style>
root {
  HyperlinkColor #42b983
}
</style>

${item.mdUmlInfo}

@enduml
```


### 处理步骤说明
<% if (item.getPSDELogicNodes()) {
  item.getPSDELogicNodes().each{ node -> %>\

#### ${node.getName()} :id=${node.getCodeName()}<sup class="footnote-symbol"> <font color=gray size=1>[${ctx.text('LogicNodeType',node.getLogicNodeType())}]</font></sup>
<% if (node.getPSSysSFPlugin()) { %>\
> 使用插件：[${node.getPSSysSFPlugin().getName()}](index/sfplugin_index#${node.getPSSysSFPlugin().getCodeName().toLowerCase()})
<% } %>\

<%= (node.getMemo()) ? node.getMemo() : "" %>

<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEAggregateParamLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEAggregateParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEAppendParamLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEAppendParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEBeginLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEBeginLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEBindParamLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEBindParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDECancelWFLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDECancelWFLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDECommitLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDECommitLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDECopyParamLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDECopyParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDEActionLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDEActionLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDebugParamLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDebugParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDecisionLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDecisionLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDEDataFlowLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDEDataFlowLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDEDataQueryLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDEDataQueryLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDEDataSetLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDEDataSetLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDEDataSyncLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDEDataSyncLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDEDTSQueueLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDEDTSQueueLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDELogicLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDELogicLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDENotifyLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDENotifyLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDEPrintLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDEPrintLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEDEReportLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEDEReportLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEEndLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEEndLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEFilterParam2Logic){ %><%= ctx.output(node,'/module/logic/node/IPSDEFilterParam2Logic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEFilterParamLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEFilterParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDELoopSubCallLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDELoopSubCallLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEMergeParamLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEMergeParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEPrepareParamLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEPrepareParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDERawCodeLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDERawCodeLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDERawSqlAndLoopCallLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDERawSqlAndLoopCallLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDERawSqlCallLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDERawSqlCallLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDERawWebCallLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDERawWebCallLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDERenewParamLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDERenewParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEResetParamLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEResetParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDERollbackLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDERollbackLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDESFPluginLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDESFPluginLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDESortParamLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDESortParamLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEStartWFLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEStartWFLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDESubSysSAMethodLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDESubSysSAMethodLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDESysBDTableActionLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDESysBDTableActionLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDESysDataSyncAgentOutLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDESysDataSyncAgentOutLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDESysDBTableActionLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDESysDBTableActionLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDESysLogicLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDESysLogicLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDESysSearchDocActionLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDESysSearchDocActionLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDESysUtilLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDESysUtilLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEThrowExceptionLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEThrowExceptionLogic.md.tpl') %><% }%>\
<% if (node instanceof net.ibizsys.model.dataentity.logic.IPSDEUserLogic){ %><%= ctx.output(node,'/module/logic/node/IPSDEUserLogic.md.tpl') %><% }%>\
<% }} %>\

<% if (item.getConditionLinks()) {%>\

### 连接条件说明
<% item.getConditionLinks().each{ link -> %>\
#### ${link.getName()} <%if(link.getPSDELogicLinkGroupCond().info){%>:id=${link.getSrcPSDELogicNode().getCodeName()}-${link.getDstPSDELogicNode().getCodeName()}<%}%>

${link.getPSDELogicLinkGroupCond().info}
<% }} %>

### 实体逻辑参数

|    中文名   |    代码名    |  数据类型    |  实体   |备注 |
| --------| --------| -------- | -------- | --------   |
<% if (item.getPSDELogicParams()) {
  item.getPSDELogicParams().each{ param -> %>\
|${param.getLogicName()}<%if(param.isDefault()){%>(<i class="fa fa-check"/></i>)<%}%>|${param.getCodeName()}|${param.dataType}|<% if (param.getParamPSDataEntity() && (param.isEntityParam() || param.isEntityListParam() || param.isEntityMapParam()) ) { %>[${param.getParamPSDataEntity().getDisplayName()}](module/${param.getParamPSDataEntity().getPSSystemModule().getCodeName()}/${param.getParamPSDataEntity().getCodeName()}.md)<%}%>|<%= (param.getMemo()) ? param.getMemo() : "" %>|
<% }} %>\
<%}%>