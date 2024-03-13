# 系统序列 <!-- {docsify-ignore-all} -->

<% if (item.getAllPSSysSequences()) {
  item.getAllPSSysSequences().each{ sequence -> %>\
### ${sequence.getName()} :id=${sequence.getCodeName()}
${ctx.ignoreNullString(sequence.getMemo())}

<br>
<p class="panel-title"><b>序列标记</b></p>

* `${ctx.ignoreNullString(sequence.getSequenceTag(),sequence.getCodeName())}`

<% if(sequence.getSequenceType() == 'DE') {%>\

<p class="panel-title"><b>序列格式化</b></p>

* `${sequence.getSequenceFormat()}`

<p class="panel-title"><b>序列实体</b></p>

* [${sequence.getPSDataEntity().getDisplayName()}](module/${sequence.getPSDataEntity().getPSSystemModule().getCodeName()}/${sequence.getPSDataEntity().getCodeName()}.md)

<p class="panel-title"><b>值存储属性</b></p>

* `${sequence.getValuePSDEField().getLogicName()}(${sequence.getValuePSDEField().getName()})` 值区间为`[<%=(sequence.getMinValue())?sequence.getMinValue():"-∞"%> , <%=(sequence.getMaxValue())?sequence.getMaxValue():"+∞"%>)`

<p class="panel-title"><b>标记存储属性</b></p>

* `${sequence.getKeyPSDEField().getLogicName()}(${sequence.getKeyPSDEField().getName()})`
<% if (sequence.getTimePSDEField()) {%>\

<p class="panel-title"><b>时间存储属性</b></p>

* `${sequence.getTimePSDEField().getLogicName()}(${sequence.getTimePSDEField().getName()})` 格式`${sequence.getTimeFormat()}`
<%}%>\
<% if (sequence.getTypePSDEField()) {%>\

<p class="panel-title"><b>类型存储属性</b></p>

* `${sequence.getTypePSDEField().getLogicName()}(${sequence.getTypePSDEField().getName()})`
<%}%>
<% if (sequence.getExtFormatParams()) {%>\

<p class="panel-title"><b>序列格式化时使用属性</b></p>

<% sequence.getExtFormatParams().each{ param -> %>\
* `${param}`
<%}}%>

##### 序列使用清单

<%
if (item.getAllPSDataEntities()) {
    item.getAllPSDataEntities().each{ entity ->
        entity.getAllPSDEFields().each{ field ->
            if((field.getPSSysSequence()) && field.getPSSysSequence() == sequence){ %>\
> [${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}.md) `${field.getLogicName()}(${field.getName()})` <br>
<%             }
        }
    }
}%>\

<% }%>

<% }}%>