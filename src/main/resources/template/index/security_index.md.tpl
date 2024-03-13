## 系统角色 <!-- {docsify-ignore-all} -->

<% if (item.getAllPSSysUserRoles()) { %>\
|  中文名      |   编号  |  模式 |  备注  |
|  --------   | -----   |  :-----:     |  -------- |
<%   item.getAllPSSysUserRoles().each{ role -> %>\
|[${role.getName()}](security/${role.getCodeName()}.md)|${role.getCodeName()}|${ctx.text('SysRoleDefaultUser',role.getDefaultUser())}|${ctx.tableString(role.getMemo())}|
<% } %>


## 角色能力分布

<table>
<tr>
<th colspan="2">
能力
</th>
<% sys.getAllPSSysUserRoles().each{ role -> %>\
<th>
${role.getName()}
</th>
<% } %>
</tr>


<% if (sys.getAllPSSysUniReses()) {
    sys.getAllPSSysUniReses().eachWithIndex{ res,index -> %>\
<tr>
<%if (index == 0){%>
<td rowspan="${sys.getAllPSSysUniReses().size()}">统一资源</td>
<% } %>
<td align="center">${res.getName()}</td>
<% sys.getAllPSSysUserRoles().each{ role -> %>\
<td align="center">
<% role.getPSSysUserRoleReses().each{ sysres ->
  if (sysres.getPSSysUniRes().id == res.id) {%>\
<i class="fa fa-check"></i>
<% }} %>
</td>
<% } %>
</tr>
<% }} %>


<% if (sys.getAllPSDataEntities()) {
    sys.getAllPSDataEntities().each { entity -> %>\
<% if (entity.getDataAccCtrlMode() == 1 && entity.getAllPSDEUserRoles()) {
  entity.getAllPSDEUserRoles().eachWithIndex{ derole,index -> %>\
  <tr>
<%if (index == 0){%>
<td rowspan="${entity.getAllPSDEUserRoles().size()}"><a href ="/#/module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}">${entity.getLogicName()}</a></td>
<% } %>
<td><a href ="/#/module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}#${entity.getCodeName().toLowerCase()}-${derole.getRoleTag().toLowerCase()}">${derole.getName()}</a></td>
<% sys.getAllPSSysUserRoles().each{ role -> %>\
<td align="center">
<% role.getPSSysUserRoleDatas().each{ sysderole ->
  if (sysderole.getPSDEUserRole().id == derole.id) {%>\
<i class="fa fa-check"></i>
<% }} %>
</td>
<% } %>
</tr>
<% }} %>
<% }} %>


</table>

<% } %>






