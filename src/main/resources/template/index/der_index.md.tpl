# 实体关系 <!-- {docsify-ignore-all} -->
<% if (item.getAllPSDERs()) { %>\
| 主实体    | 从实体    | 关系名称    | 关系类型     |   备注  |
| -------- | -------- |---------- |------------|----- |
<%  item.getAllPSDERs().each{ der -> %>\
|[${der.getMajorPSDataEntity().getDisplayName()}](module/${der.getMajorPSDataEntity().getPSSystemModule().getCodeName()}/${der.getMajorPSDataEntity().getCodeName()})|[${der.getMinorPSDataEntity().getDisplayName()}](module/${der.getMinorPSDataEntity().getPSSystemModule().getCodeName()}/${der.getMinorPSDataEntity().getCodeName()})|[${der.getName()}](der/${der.getName()})|${ctx.text('DERType',der.getDERType())}|${ctx.tableString(der.getMemo())}|
<% }}%>