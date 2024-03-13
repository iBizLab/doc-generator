## ${item.getLogicName()}(${item.getCodeName()}) <!-- {docsify-ignore-all} -->

<%= (item.getMemo()) ? item.getMemo() : "" %>

<br>
<p class="panel-title"><b>默认数据集</b></p>

* `<%= (item.isDefaultMode()) ? "是" : "否" %>`

<p class="panel-title"><b>数据集类型</b></p>

* `${ctx.text('DEDataSetType',item.getDataSetType())}`

<p class="panel-title"><b>选择列级别</b></p>

* `${ctx.text('DEDataQueryViewLevel',item.getViewLevel())}`
<% if (item.getPageSize()!=-1) {%>\

<p class="panel-title"><b>默认分页大小</b></p>

* `${item.getPageSize()}`
<%}%>
<% if (item.getMajorSortPSDEField()) {%>\

<p class="panel-title"><b>主排序属性</b></p>

* `${item.getMajorSortPSDEField().getName()}(${item.getMajorSortPSDEField().getLogicName()})` `<%=(item.getMajorSortDir())?item.getMajorSortDir():""%>`
<%}%>
<% if (item.getMinorSortPSDEField()) {%>\

<p class="panel-title"><b>从排序属性</b></p>

* `${item.getMinorSortPSDEField().getName()}(${item.getMinorSortPSDEField().getLogicName()})` `<%=(item.getMinorSortDir())?item.getMinorSortDir():""%>`
<%}%>

### 数据查询
<% if(item.getPSDEDataQueries()){
item.getPSDEDataQueries().each{ query -> %>\
  * [${query.getDisplayName()}](module/${query.getParentPSModelObject().getPSSystemModule().getCodeName()}/${query.getParentPSModelObject().getCodeName()}/query/${query.getCodeName()})
<% }} %>\
<%if(item.getGroupMode()!=0){%>\

### 数据聚合

<br>
<p class="panel-title"><b>聚合模式</b></p>

* `${ctx.text('DEDataSetGroupMode',item.getGroupMode())}`

<%if(item.getGroupMode()==2){%>\

<p class="panel-title"><b>聚合数据关系</b></p>

* [${item.getPSDERAggData().getName()}](der/${item.getPSDERAggData().getName()})
<%}%>\
<%if(item.getGroupMode()==1){%>\

<p class="panel-title"><b>分组参数</b></p>

<% item.getPSDEDataSetGroupParams().each{ param ->
  if(param.isEnableGroup()){%>\
* `<%=(param.getPSDEField())?  param.getPSDEField().getName() + "(" +param.getPSDEField().getLogicName()+ ")" : param.getName() %>` <%if(param.getGroupCode()){%> 分组处理：`${param.getGroupCode()}`<%}%><%if(param.getSortDir()){%> 排序：`${param.getSortDir()}`<%}%>
<%}}%>\

<p class="panel-title"><b>聚合参数</b></p>

<% item.getPSDEDataSetGroupParams().each{ param ->
  if(!param.isEnableGroup()){%>\
* `${param.getName()}` <%if(param.getGroupCode()){%> 聚合函数：`${param.getGroupCode()}`<%}%><%if(param.getSortDir()){%> 排序：`${param.getSortDir()}`<%}%>
<%}}%>\
<%}%>\
<%}%>