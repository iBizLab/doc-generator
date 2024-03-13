<% if (item.getSqlNodeLogics()) { %>\

### 存在直接SQL调用的处理逻辑<sup class="footnote-symbol"> <font color=orange>[${item.getSqlNodeLogics().size()}]</font></sup>
| 实体   | 处理逻辑  |
| --------   |------------|
<%  item.getSqlNodeLogics().each{ logic -> %>\
|[${logic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getDisplayName()}](module/${logic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${logic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()})|[${logic.getDisplayName(true)}](module/${logic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${logic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()}/logic/${logic.getCodeName()}.md)|
<% }}%>