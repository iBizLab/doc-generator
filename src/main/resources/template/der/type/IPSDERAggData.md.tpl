<%if(item.getPSDERAggDataDEFieldMaps()){%>\

<p class="panel-title"><b>属性映射</b></p>

<%item.getPSDERAggDataDEFieldMaps().each{ map -> %>\
* 类型：`${ctx.text('DERAggDataDEFMapType',map.getMapType())}`  主属性：`<%if(map.getMajorPSDEField()){%>${map.getMajorPSDEField().getName()}(${map.getMajorPSDEField().getLogicName()})<%}%>` 从属性：`<%if(map.getMinorPSDEField()){%>${map.getMinorPSDEField().getName()}(${map.getMinorPSDEField().getLogicName()})<%}%>` <%if(map.getFormulaFormat()){%>逻辑表达式：`${map.getFormulaFormat()}`<%}%>
<%}}%>