<% if (item.getScriptActions()) { %>\

### 行为使用脚本<sup class="footnote-symbol"> <font color=orange>[${item.getScriptActions().size()}]</font></sup>
| 实体   | 行为  |
| --------   |------------|
<%  item.getScriptActions().each{ action ->
def entity = action.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class)
%>\
|[${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()})|[${action.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}#行为)|
<% }}%>