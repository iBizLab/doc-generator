# ${item.getName()} <!-- {docsify-ignore-all} -->

<%= (item.getMemo()) ? item.getMemo() : "" %>

<% if (item.getPSWFVersions()) {
  item.getPSWFVersions().each{ wfversion -> %>\
## ${wfversion.getName()}

### 流程图

```plantuml
@startuml
hide empty description
<style>
root {
  HyperlinkColor #42b983
}
</style>

<% if (wfversion.getPSWFProcesses()) {
  wfversion.getPSWFProcesses().each{ process -> %>\
state "${process.getName()}" as ${process.getCodeName()}<%if(process instanceof net.ibizsys.model.wf.IPSWFStartProcess) {%> <<start>> #seagreen<% } %><%if(process instanceof net.ibizsys.model.wf.IPSWFEndProcess) {%> <<end>> #red<% } %><%if(process instanceof net.ibizsys.model.wf.IPSWFGatewayProcessBase) {%> <<choice>><% } %><%if(process instanceof net.ibizsys.model.wf.IPSWFDEActionProcess ) { %> #lightgreen<%if(process.getPSDataEntity() && process.getPSDEAction()) {%> : 行为：[[\$../module/${process.getPSDataEntity().getPSSystemModule().getCodeName()}/${process.getPSDataEntity().getCodeName()}#行为{${process.getPSDEAction().getDisplayName()}} ${process.getPSDEAction().getDisplayName()}]]<% } else { %>;line.dashed<% }} %><%if(process instanceof net.ibizsys.model.wf.IPSWFCallActivityProcess) {%> #lightblue : 流程：[[\$${process.getTargetPSWF().getCodeName()}{${process.getTargetPSWF().getDisplayName()}} ${process.getTargetPSWF().getDisplayName()}]]<% } %><%if(process instanceof net.ibizsys.model.wf.IPSWFEmbedWFProcessBase) {%> #orange {
<% if (process.getPSWFProcessSubWFs()) {process.getPSWFProcessSubWFs().eachWithIndex{ subwf,index -> %>\
    state "${subwf.getPSWorkflow().getLogicName()}" [[\$${subwf.getPSWorkflow().getCodeName()}{${subwf.getPSWorkflow().getDisplayName()}} ${subwf.getPSWorkflow().getDisplayName()}]] #orange : 实体：${subwf.getPSDataEntity().getLogicName()}\\n集合：${subwf.getPSDEDataSet().getLogicName()}
<% }} %>\
}
<% } %>
<% }} %>\

<% if (wfversion.getPSWFLinks()) {
    wfversion.getPSWFLinks().each{ link -> %>\
${link.getFromPSWFProcess().getCodeName()} -<%=(link instanceof net.ibizsys.model.wf.IPSWFTimeoutLink)?"[#red]":""%>-> ${link.getToPSWFProcess().getCodeName()} : <%= (link.getLogicName()) ? link.getLogicName() : link.getName() %><%= (link instanceof net.ibizsys.model.wf.IPSWFEmbedWFReturnLink || link instanceof net.ibizsys.model.wf.IPSWFInteractiveLink) ? "\\n" + link.getNextCondition() : "" %>
<% }} %>\

@enduml
```


### 审批步骤

|    序号 |    审批步骤   |    审批人     |
| -------- | --------   |------------|
<% if (wfversion.getPSWFProcesses()) {
  wfversion.getPSWFProcesses().eachWithIndex{ process,index -> %>\
|${index+1}|${process.getName()}||
<% }} %>

<% }} %>
