<% if (item.getScriptActionLogics()) { %>\

### 行为附加使用脚本<sup class="footnote-symbol"> <font color=orange>[${item.getScriptActionLogics().size()}]</font></sup>
| 实体   | 行为  |附加模式|
| --------   |------------|-----------|
<%  item.getScriptActionLogics().each{ actionlogic ->
def entity = actionlogic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class)
def action = actionlogic.getParentPSModelObject(net.ibizsys.model.dataentity.action.IPSDEAction.class)
%>\
|[${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()})|[${action.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}#行为)|[${ctx.text('DEActionLogicAttachMode',actionlogic.getAttachMode())}](index/action_logic_index#${entity.getCodeName()}_${action.getName()})|
<% }}%>