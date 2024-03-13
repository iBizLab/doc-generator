# ${item.getName()}(${item.getCodeName()}) <!-- {docsify-ignore-all} -->

<%= (item.getMemo()) ? item.getMemo() : "" %>

|    名称   | 代码名      |  实体类型   |  存储模式 |  表名称  |  逻辑有效   |  联合主键   |  主状态   |  权限控制  |  启用审计    |  备注  |
| --------  |------------| -----   |  --------|  --------|  --------|  -------- |  -------- | -------- | -------- |-------- |
<% if (item.getAllPSDataEntities()) {
  item.getAllPSDataEntities().each{ entity -> %>\
|[${entity.getDisplayName()}](module/${item.getCodeName()}/${entity.getCodeName()})|${entity.getCodeName()}|<%=
ctx.text('DEType',entity.getDEType()) %>|<%=
ctx.text('DEStorageType',entity.getStorageMode()) %>|${ctx.ignoreNullString(entity.getTableName())}|<%=
(entity.isLogicValid()) ? "是" : "否" %>|<%=
(entity.getUnionKeyValuePSDEFields())? "是" : "否" %>|<%=
(entity.getMainStatePSDEFields())? "是" : "否" %>|<%=
ctx.text('DEDataAccCtrlMode',entity.getDataAccCtrlMode()) %>|<%=
(entity.getAuditMode() > 0)? "是" : "否" %>|${ctx.tableString(entity.getMemo())}|
<% }} %>
