## ${item.getName()}(${item.getCodeName()}) <!-- {docsify-ignore-all} -->

<%= (item.getMemo()) ? item.getMemo() : "" %>

<br>
<p class="panel-title"><b>是否定时触发</b></p>

* `<%= (item.isTimerMode()) ? "是" : "否" %>`
<%if(item.isTimerMode()){ %>\

<p class="panel-title"><b>数据集合</b></p>

* [${item.getPSDEDataSet().getDisplayName()}](module/${item.getPSDEDataSet().getParentPSModelObject().getPSSystemModule().getCodeName()}/${item.getPSDEDataSet().getParentPSModelObject().getCodeName()}/dataset/${item.getPSDEDataSet().getCodeName()})

<p class="panel-title"><b>通知时间属性</b></p>

* `${item.getBeginTimePSDEField().getLogicName()}(${item.getBeginTimePSDEField().getName()})`
<%if(item.getNotifyStart()){ %>\

<p class="panel-title"><b>提前通知</b></p>

* `${item.getNotifyStart()}分钟`
<%}%>\
<%if(item.getNotifyEnd()){ %>\

<p class="panel-title"><b>延后通知</b></p>

* `${item.getNotifyEnd()}分钟`
<%}%>\
<%}%>\

<p class="panel-title"><b>消息队列</b></p>

* [${item.getPSSysMsgQueue().getName()}](index/notify_index)

<p class="panel-title"><b>消息模板</b></p>

* [${item.getPSSysMsgTempl().getName()}](index/notify_index?id=${item.getPSSysMsgTempl().getCodeName()})

<p class="panel-title"><b>通知类型</b></p>

* <i class="fa fa-<%=((1&item.getMsgType())>0)?"check-":""%>square"/></i> 系统消息 <i class="fa fa-<%=((2&item.getMsgType())>0)?"check-":""%>square"/></i> 电子邮件 <i class="fa fa-<%=((4&item.getMsgType())>0)?"check-":""%>square"/></i> 手机短信 <i class="fa fa-<%=((8&item.getMsgType())>0)?"check-":""%>square"/></i> MSN消息 <i class="fa fa-<%=((16&item.getMsgType())>0)?"check-":""%>square"/></i> 检务通消息 <i class="fa fa-<%=((16&item.getMsgType())>0)?"check-":""%>square"/></i> 微信 <i class="fa fa-<%=((16&item.getMsgType())>0)?"check-":""%>square"/></i> 钉钉 <i class="fa fa-<%=((16&item.getMsgType())>0)?"check-":""%>square"/></i> 企业微信
<%if(item.getPSDENotifyTargets()){ %>\

#### 通知目标

<%item.getPSDENotifyTargets().each{ target -> %>\
* **${target.getName()}**<br>
  目标类型：`${ctx.text('DENotifyTargetType',target.getTargetType())}`<br>
<%if(target.getTargetType() == 'DEFIELD'){ %>\
  目标属性：`${target.getTargetPSDEField().getDisplayName()}`
<%}%>\
<%if(target.getTargetType() == 'SYSMSGTARGET'){ %>\
  系统目标：`${target.getPSSysMsgTarget().getName()}`
<%}%>\
<%}}%>