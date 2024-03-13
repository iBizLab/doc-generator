## 流程角色 <!-- {docsify-ignore-all} -->

|    中文名 | 编号   | 代码名       |  类型     |  备注  |
| --------| --------   |------------| -----   |  -------- |
<% if (item.getAllPSWFRoles()) {
  item.getAllPSWFRoles().each{ wfrole -> %>\
|${wfrole.getName()}|<%= ctx.ignoreNullString(wfrole.getWFRoleSN()) %>|${wfrole.getCodeName()}|<%= ctx.text('WFRoleType',wfrole.getWFRoleType())%>|<%= ctx.tableString(wfrole.getMemo()) %>|
<% }} %>

