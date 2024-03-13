# ${item.getName()}(${item.getCodeName()}) <!-- {docsify-ignore-all} -->

<%= (item.getMemo()) ? item.getMemo() : "" %>

<p class="panel-title"><b>接口类型</b></p>

* `${ctx.text('ServiceAPIType',item.getAPIType())}`

<p class="panel-title"><b>接口模式</b></p>

* `${ctx.text('ServiceAPIMode',item.getAPIMode())}`
<% if (item.getServiceType()) {%>\

<p class="panel-title"><b>服务类型</b></p>

* `${ctx.text('ServiceType',item.getServiceType())}`
<%}%>\

#### 服务实体

<% if (item.getPSDEServiceAPIs()) {
  item.getPSDEServiceAPIs().each{ deapi -> %>\
* [${deapi.getDisplayName()}](api/deapi/${deapi.getCodeName()}.md)<%if(deapi.getMemo()){%><br>${deapi.getMemo()}<%}%>
<% }} %>




