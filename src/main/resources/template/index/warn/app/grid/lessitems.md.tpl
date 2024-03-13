<% if (item.getLessItemsGrids()) { %>\

### 除主键、主信息、预置属性外，不包含其他配置的表格<sup class="footnote-symbol"> <font color=orange>[${item.getLessItemsGrids().size()}]</font></sup>
| 实体   | 表格  |视图|
| --------   |------------|------------|
<%  item.getLessItemsGrids().each{ entry -> %>\
|[${entry.value.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getDisplayName()}](module/${entry.value.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${entry.value.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()})|${entry.value.getLogicName()}(${entry.value.getCodeName()})|[<%=(entry.key.getCaption())?entry.key.getCaption():entry.key.getTitle()%>(${entry.key.getCodeName()})](app/view/${entry.key.getCodeName()})|
<% }}%>