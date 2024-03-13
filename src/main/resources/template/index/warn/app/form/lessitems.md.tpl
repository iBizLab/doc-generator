<% if (item.getLessItemsForms()) { %>\

### 除主键、主信息、预置属性外，不包含其他配置的表单<sup class="footnote-symbol"> <font color=orange>[${item.getLessItemsForms().size()}]</font></sup>
| 实体   | 表单  |  视图 |
| --------   |------------|------------|
<%  item.getLessItemsForms().each{ entry -> %>\
|[${entry.value.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getDisplayName()}](module/${entry.value.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${entry.value.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()})|${entry.value.getLogicName()}(${entry.value.getCodeName()})|[<%=(entry.key.getCaption())?entry.key.getCaption():entry.key.getTitle()%>(${entry.key.getCodeName()})](app/view/${entry.key.getCodeName()})|
<% }}%>