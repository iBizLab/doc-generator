
<p class="panel-title"><b>关系子类型</b></p>

* `${ctx.text('DERSubType',item.getDERSubType())}`

<p class="panel-title"><b>关系识别值</b></p>

* `${item.getTypeValue()}`
<%if(item.getPickupPSDEField()){%>\

<p class="panel-title"><b>外键属性</b></p>

* `${item.getPickupPSDEField().getName()}(${item.getPickupPSDEField().getLogicName()})`
<%}%>
<p class="panel-title"><b>主从关系类型</b></p>

* <i class="fa fa-<%=((1&item.getMasterRS())>0)?"check-":""%>square"/></i> 附属关系 <i class="fa fa-<%=((2&item.getMasterRS())>0)?"check-":""%>square"/></i> 附属关系(N:N连接) <i class="fa fa-<%=((4&item.getMasterRS())>0)?"check-":""%>square"/></i> 数据访问控制 <i class="fa fa-<%=((8&item.getMasterRS())>0)?"check-":""%>square"/></i> 嵌套操作 <i class="fa fa-<%=((16&item.getMasterRS())>0)?"check-":""%>square"/></i> 递归关系

<p class="panel-title"><b>删除方式</b></p>

* `${ctx.text('DER1NRemoveActionType',item.getRemoveActionType())}`

<p class="panel-title"><b>删除顺序</b></p>

* `${item.getRemoveOrder()}`
