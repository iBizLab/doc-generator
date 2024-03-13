
<p class="panel-title"><b>索引值</b></p>

* `${item.getTypeValue()}`
<%if(item.getPSDERIndexDEFieldMaps()){%>\

<p class="panel-title"><b>属性映射</b></p>

<%item.getPSDERIndexDEFieldMaps().each{ map -> %>\
* `<%if(map.getMajorPSDEField()){%>${map.getMajorPSDEField().getName()}(${map.getMajorPSDEField().getLogicName()})<%}%>=<%if(map.getMinorPSDEField()){%>${map.getMinorPSDEField().getName()}(${map.getMinorPSDEField().getLogicName()})<%}%>`
<%}}%>