<% if (item.getLessItemsSearchForms()) { %>\

### 无搜索项的搜索表单<sup class="footnote-symbol"> <font color=orange>[${item.getLessItemsSearchForms().size()}]</font></sup>
| 实体   | 搜索表单  | 视图  |
| --------   |------------|-----------|
<%  item.getLessItemsSearchForms().each{ entry -> %>\
|[${entry.value.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getDisplayName()}](module/${entry.value.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${entry.value.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()})|${entry.value.getLogicName()}(${entry.value.getCodeName()})|[<%=(entry.key.getCaption())?entry.key.getCaption():entry.key.getTitle()%>(${entry.key.getCodeName()})](app/view/${entry.key.getCodeName()})|
<% }}%>