## ${item.getLogicName()}(${item.getCodeName()}) <!-- {docsify-ignore-all} -->

<%= (item.getMemo()) ? item.getMemo() : "" %>

<br>

<p class="panel-title"><b>实体</b></p>

* [${item.getParentPSModelObject().getDisplayName()}](module/${item.getParentPSModelObject().getPSSystemModule().getCodeName()}/${item.getParentPSModelObject().getName()})

<p class="panel-title"><b>映射实体</b></p>

* [${item.getDstPSDE().getDisplayName()}](module/${item.getDstPSDE().getPSSystemModule().getCodeName()}/${item.getDstPSDE().getName()})

<% if (item.getPSDEMapFields()) {%>\

<p class="panel-title"><b>属性映射</b></p>

<% item.getPSDEMapFields().each{ map -> %>\
* `${ctx.text('DEMapFieldSrcType',map.getMapType())}`
<% if (map.getMapType() == 'FIELD') {%>\
`${map.getSrcPSDEField().getName()}(${map.getSrcPSDEField().getLogicName()})` <i class="fa fa-angle-double-right"/></i> `${map.getDstPSDEField().getName()}(${map.getDstPSDEField().getLogicName()})`
<%}%>\
<% if (map.getMapType() == 'VALUE') {%>\
`'${map.getRawValue()}'` <i class="fa fa-angle-double-right"/></i> `${map.getDstPSDEField().getName()}(${map.getDstPSDEField().getLogicName()})`
<%}%>\
<% if (map.getMapType() == 'EXPRESSION') {%>\
`${map.getExpression()}` <i class="fa fa-angle-double-right"/></i> `${map.getDstPSDEField().getName()}(${map.getDstPSDEField().getLogicName()})`
<%}%>\
<% if (map.getMapType() == 'VALUE_SRC') {%>\
`'${map.getRawValue()}'` <i class="fa fa-angle-double-right"/></i> `${map.getSrcPSDEField().getName()}(${map.getSrcPSDEField().getLogicName()})`
<%}%>\
<% if (map.getMapType() == 'EXPRESSION_SRC') {%>\
`${map.getExpression()}` <i class="fa fa-angle-double-right"/></i> `${map.getSrcPSDEField().getName()}(${map.getSrcPSDEField().getLogicName()})`
<%}%>\
<%if(map.getMemo()){%><br>${map.getMemo()}<%}%>\
<% }} %>\
<% if (item.getPSDEMapActions()) {%>\

<p class="panel-title"><b>行为映射</b></p>

<% item.getPSDEMapActions().each{ map -> %>\
* `${ctx.text('DEMapObjectMapMode',map.getMapMode())}`
`${map.getSrcPSDEAction().getLogicName()}` <i class="fa fa-angle-double-right"/></i> `${map.getDstPSDEAction().getLogicName()}`<%if(map.getMemo()){%><br>${map.getMemo()}<%}%>
<% }} %>\
<% if (item.getPSDEMapDataQueries()) {%>\

<p class="panel-title"><b>查询映射</b></p>

<% item.getPSDEMapDataQueries().each{ map -> %>\
* `${ctx.text('DEMapObjectMapMode',map.getMapMode())}`
`${map.getSrcPSDEDataQuery().getName()}(${map.getSrcPSDEDataQuery().getLogicName()})` <i class="fa fa-angle-double-right"/></i> `${map.getDstPSDEDataQuery().getName()}(${map.getDstPSDEDataQuery().getLogicName()})` <%if(map.isEnableDQCond()){%>并且 `启用查询条件`<%}%><%if(map.getMemo()){%><br>${map.getMemo()}<%}%>
<% }} %>\
<% if (item.getPSDEMapDataSets()) {%>\

<p class="panel-title"><b>集合映射</b></p>

<% item.getPSDEMapDataSets().each{ map -> %>\
* `${ctx.text('DEMapObjectMapMode',map.getMapMode())}`
`${map.getSrcPSDEDataSet().getName()}(${map.getSrcPSDEDataSet().getLogicName()})` <i class="fa fa-angle-double-right"/></i> `${map.getDstPSDEDataSet().getName()}(${map.getDstPSDEDataSet().getLogicName()})` <%if(map.isEnableDQCond()){%>并且 `启用查询条件`<%}%><%if(map.getMemo()){%><br>${map.getMemo()}<%}%>
<% }} %>