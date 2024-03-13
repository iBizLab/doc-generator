<% if (item.getScriptLogics()) { %>\

### 处理逻辑使用脚本<sup class="footnote-symbol"> <font color=orange>[${item.getScriptLogics().size()}]</font></sup>
| 实体   | 处理逻辑  | 脚本模式  |
| --------   |------------|----------|
<%  item.getScriptLogics().each{ logic ->
def entity = logic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class)
%>\
|[${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()})|[${logic.getDisplayName(true)}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}/logic/${logic.getCodeName()}.md)|<%=(logic.isCustomCode())? "是" : "否" %>|
<% }}%>