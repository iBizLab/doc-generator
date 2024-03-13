# 应用插件 <!-- {docsify-ignore-all} -->

<% if (item.getAllAppPFPluginRefs()) {
  item.getAllAppPFPluginRefs().each{ plugin -> %>\
### ${plugin.getName()} :id=${plugin.getPluginCode()}
${ctx.ignoreNullString(plugin.getMemo())}

插件标识：`${plugin.getPluginCode()}`
<% if (plugin.getRTObjectName()) {%>\

运行对象：`${plugin.getRTObjectName()}`
<%}%>\
<% if (plugin.getRTObjectRepo()) {%>\

远程仓库：`${plugin.getRTObjectRepo()}`
<%}%>\

<% }}%>





