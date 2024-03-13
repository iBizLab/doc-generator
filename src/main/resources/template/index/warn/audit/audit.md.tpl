<% if (item.getNoAuditUtilEntities()) { %>\

### 启用审计，但未配置审计功能<sup class="footnote-symbol"> <font color=orange>[${item.getNoAuditUtilEntities().size()}]</font></sup>
|    中文名  | 实体名   | 代码名      |  实体类型   |  存储模式 |  表名称  |  逻辑有效   |  联合主键   |  主状态   |  权限控制  |  启用审计    |  备注  |
| --------| --------   |------------| -----   |  --------|  --------|  --------|  -------- |  -------- | -------- | -------- |-------- |
<%  item.getNoAuditUtilEntities().each{ entity -> %>\
|[${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}.md)|${entity.getName()}|${entity.getCodeName()}|<%=
ctx.text('DEType',entity.getDEType()) %>|<%=
ctx.text('DEStorageType',entity.getStorageMode()) %>|${ctx.ignoreNullString(entity.getTableName())}|<%=
(entity.isLogicValid()) ? "是" : "否" %>|<%=
(entity.getUnionKeyValuePSDEFields())? "是" : "否" %>|<%=
(entity.getMainStatePSDEFields())? "是" : "否" %>|<%=
ctx.text('DEDataAccCtrlMode',entity.getDataAccCtrlMode()) %>|<%=
(entity.getAuditMode() > 0)? "是" : "否" %>|${ctx.tableString(entity.getMemo())}|
<% }}%>