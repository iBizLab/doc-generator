# 后台服务 <!-- {docsify-ignore-all} -->

<% if (item.getAllBackServices()) {
  item.getAllBackServices().each{ backservice -> %>\
### ${backservice.getName()} :id=${backservice.getCodeName()}
${ctx.ignoreNullString(backservice.getMemo())}

<br>
<p class="panel-title"><b>启动模式</b></p>

* `${ctx.text('BackendTaskStartMode',backservice.getStartMode())}`
<% if(backservice.getTaskType() == 'DEACTION') {%>\

<p class="panel-title"><b>服务调用</b></p>

* 调用实体 [${backservice.getPSDataEntity().getDisplayName()}](module/${backservice.getPSDataEntity().getPSSystemModule().getCodeName()}/${backservice.getPSDataEntity().getCodeName()}.md) 行为 [${backservice.getPSDEAction().getDisplayName()}](module/${backservice.getPSDataEntity().getPSSystemModule().getCodeName()}/${backservice.getPSDataEntity().getCodeName()}#行为)
<%}%>\
<% if(backservice.getServiceParams()) {%>\

<p class="panel-title"><b>服务参数</b></p>

* `${backservice.getServiceParams()}`
<%}%>\
<% if (backservice.isTimerMode()) {%>\

<p class="panel-title"><b>定时触发</b></p>

* 定时触发策略 `${backservice.getTimerPolicy()}`
<%}%>\

<% }}%>





