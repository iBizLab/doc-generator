# 系统值规则 <!-- {docsify-ignore-all} -->

<% if (item.getAllPSSysValueRules()) {
  item.getAllPSSysValueRules().each{ sysvaluerule -> %>\
### ${sysvaluerule.getName()} :id=${sysvaluerule.getCodeName()}
${ctx.ignoreNullString(sysvaluerule.getMemo())}

<% }}%>





