> [!NOTE|label:${ctx.text('DEActionLogicType',item.getActionLogicType())}]
<% if (item.getPSDELogic()) {%>\
> 执行处理逻辑 [${item.getPSDELogic().getDisplayName(true)}](module/${item.getParentPSModelObject().getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${item.getParentPSModelObject().getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()}/logic/${item.getPSDELogic().getCodeName()}.md)
<% } %>\
<% if (item.getPSDENotify()) {%>\
> 实体通知 [${item.getPSDENotify().getDisplayName(true)}]()
<% } %>\
<% if (item.getPSDEDataSync()) {%>\
> 数据同步 [${item.getPSDEDataSync().getDisplayName(true)}]()
<% } %>\
<% if (item.getPSSysSequence()) {%>\
> 获取序列 [${item.getPSSysSequence().getDisplayName(true)}]()
<% } %>\
<% if (item.getPSSysLogic()) {%>\
> 调用系统预置逻辑 [${item.getPSSysLogic().getDisplayName(true)}]()
<% } %>\
<% if (item.getPSSysTranslator()) {%>\
> 数据转换 [${item.getPSSysTranslator().getDisplayName(true)}]()
<% } %>\
<% if (item.getPSDEFValueRule()) {%>\
> 检查值规则 [${item.getPSDEFValueRule().getDisplayName(true)}]()
<% } %>\
<% if (item.getDstPSDEAction()) {%>\
> 执行实体 [${item.getDstPSDE().getDisplayName()}](module/${item.getDstPSDE().getPSSystemModule().getCodeName()}/${item.getParentPSModelObject().getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()}.md) 的 [${item.getDstPSDEAction().getDisplayName()}](module/${item.getParentPSModelObject().getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${item.getParentPSModelObject().getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()}#行为) 行为
<% } %>\
<% if (item.getScriptCode()) {%>\
> ```groovy
> ${item.getScriptCode()}
> ```
<% } %>\
<%if(item.getMemo()){%>><br>${item.getMemo()}<%}%>
