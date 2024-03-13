# 消息通知 <!-- {docsify-ignore-all} -->

<%if(item.getAllPSSysMsgQueues()){%>\

## 消息队列

|    中文名   | 代码名       |  类型     |  备注  |
| --------|------------| -----   |  -------- |
<%item.getAllPSSysMsgQueues().each{ queue -> %>\
|${queue.getName()}|${queue.getCodeName()}|<%= ctx.text('MsgQueueType',queue.getMsgQueueType())%>|<%= ctx.tableString(queue.getMemo()) %>|
<%}}%>\

## 实体消息通知

|    实体|    通知名称         |  消息队列  |  消息模板 |  通知目标    |  使用场景    |  备注  |
| --------|------------ |  -------- | -------- | -------- | -------- | -------- |
<%item.getAllPSDataEntities()?.each{ entity ->
    entity.getAllPSDENotifies()?.each{ notify ->  %>\
|[${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()})|[${notify.getDisplayName(true)}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}/notify/${notify.getCodeName()})|${notify.getPSSysMsgQueue().getName()}|[${notify.getPSSysMsgTempl().getDisplayName()}](#${notify.getPSSysMsgTempl().getCodeName()})|<%notify.getPSDENotifyTargets()?.each {target ->%>${target.getName()} <%}%>|<%notify.refs().eachWithIndex { ref,index ->
  if(index>0){
    out << '<br>'
  }
  def str
  def refentity = ref.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class)
  if(ref instanceof net.ibizsys.model.dataentity.action.IPSDEActionLogic) {
    def action = ref.getParentPSModelObject(net.ibizsys.model.dataentity.action.IPSDEAction.class)
    out << String.format('[%s](module/%s/%s) [%s %s](index/action_logic_index#%s_%s)',entity.getDisplayName(),entity.getPSSystemModule().getCodeName(),entity.getCodeName(),action.getDisplayName(),ctx.text('DEActionLogicAttachMode',ref.getAttachMode()),refentity.getCodeName(),action.getName())
  }
  if(ref instanceof net.ibizsys.model.dataentity.logic.IPSDELogic) {
    out << String.format('[%s](module/%s/%s) [%s](module/%s/%s/logic/%s)',entity.getDisplayName(),entity.getPSSystemModule().getCodeName(),entity.getCodeName(),ref.getDisplayName(true),refentity.getPSSystemModule().getCodeName(),refentity.getCodeName(),ref.getCodeName())
  }
}%>|<%= ctx.tableString(notify.getMemo()) %>|
<%}}%>\
<%if(item.getAllPSSysMsgTargets()){%>\

## 通知目标

|    中文名   | 代码名       |  目标类型     |  备注  |
| --------|------------| -----   |  -------- |
<%item.getAllPSSysMsgTargets().each{ target -> %>\
|${target.getName()}|${target.getCodeName()}|<%= ctx.text('MsgTargetType',target.getMsgTargetType())%>|<%= ctx.tableString(target.getMemo()) %>|
<%}}%>\
<%if(item.getAllPSSysMsgTempls()){%>\

## 消息模板

<%item.getAllPSSysMsgTempls().each{ templ -> %>\
#### ${templ.getName()}(${templ.getCodeName()}) :id=${templ.getCodeName()}
${ctx.ignoreNullString(templ.getMemo())}

模板类型：`<%= ctx.text('MsgTemplType',templ.getMsgTemplType())%>`

模板引擎：`<%= ctx.text('MsgTemplEngine',templ.getTemplEngine())%>`

内容类型：`<%= ctx.text('MsgContentType',templ.getContentType())%>`
<%if(templ.getTaskUrl()){ %>\

超链接：`${templ.getTaskUrl()}`
<%}%>\
<%if(templ.getMobTaskUrl()){ %>\

移动端超链接：`${templ.getMobTaskUrl()}`
<%}%>\
<%if(templ.getSubject()){ %>\

标题：
```
${templ.getSubject()}
```
<%}%>\

内容：
```
${templ.getContent()}
```
<%if(templ.getIMContent()){ %>\

即时消息内容：
```
${templ.getIMContent()}
```
<%}%>\
<%if(templ.getSMSContent()){ %>\

短信内容：
```
${templ.getSMSContent()}
```
<%}%>\
<%if(templ.getDDContent()){ %>\

钉钉内容：
```
${templ.getDDContent()}
```
<%}%>\
<%if(templ.getWXContent()){ %>\

微信消息内容：
```
${templ.getWXContent()}
```
<%}%>\
<%}}%>