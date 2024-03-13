## ${item.getLogicName()}(${item.getName()}) <!-- {docsify-ignore-all} -->

   <%= (item.getMemo()) ? item.getMemo() : "" %>

<% if (item.getAllPSDEFValueRules()) {
   item.getAllPSDEFValueRules().each{ valuerule -> %>\
### ${valuerule.getName()} :id=${valuerule.getCodeName()}

```plantuml
@startuml
hide empty description
<style>
root {
  HyperlinkColor #42b983
}
</style>

${valuerule.mdUmlInfo}

@enduml
```

<% if (valuerule.getAllConditions()) {%>\
#### 条件说明
<% valuerule.getAllConditions().each{ entry ->
    if(entry.key instanceof net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRSingleCondition) {%>\

##### ${entry.key.getName()} :id=a${entry.value}

<% if (entry.key.isKeyCond()){ %>
*关键条件*
<% } %>
<% if (entry.key.isNotMode()){ %>
*取反*
<% } %>
<% if (entry.key instanceof net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRQueryCountCondition){ %><%= ctx.output(entry.key,'/module/field/value_rule/condtion/IPSDEFVRQueryCountCondition.md.tpl') %><% }%>\
<% if (entry.key instanceof net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRRegExCondition){ %><%= ctx.output(entry.key,'/module/field/value_rule/condtion/IPSDEFVRRegExCondition.md.tpl') %><% }%>\
<% if (entry.key instanceof net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRSimpleCondition){ %><%= ctx.output(entry.key,'/module/field/value_rule/condtion/IPSDEFVRSimpleCondition.md.tpl') %><% }%>\
<% if (entry.key instanceof net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRStringLengthCondition){ %><%= ctx.output(entry.key,'/module/field/value_rule/condtion/IPSDEFVRStringLengthCondition.md.tpl') %><% }%>\
<% if (entry.key instanceof net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRSysValueRuleCondition){ %><%= ctx.output(entry.key,'/module/field/value_rule/condtion/IPSDEFVRSysValueRuleCondition.md.tpl') %><% }%>\
<% if (entry.key instanceof net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRValueRange2Condition){ %><%= ctx.output(entry.key,'/module/field/value_rule/condtion/IPSDEFVRValueRange2Condition.md.tpl') %><% }%>\
<% if (entry.key instanceof net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRValueRange3Condition){ %><%= ctx.output(entry.key,'/module/field/value_rule/condtion/IPSDEFVRValueRange3Condition.md.tpl') %><% }%>\
<% if (entry.key instanceof net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRValueRangeCondition){ %><%= ctx.output(entry.key,'/module/field/value_rule/condtion/IPSDEFVRValueRangeCondition.md.tpl') %><% }%>\
<% if (entry.key instanceof net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRValueRecursionCondition){ %><%= ctx.output(entry.key,'/module/field/value_rule/condtion/IPSDEFVRValueRecursionCondition.md.tpl') %><% }%>\
<% if (entry.key.getRuleInfo()){ %>

> [!ATTENTION|label:规则信息|icon:fa fa-warning]
> ${entry.key.getRuleInfo()}
<% } %>
<% }}} %>

<% }} %>



