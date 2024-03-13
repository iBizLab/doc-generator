# ${item.getName()}(${item.getCodeName()}) <!-- {docsify-ignore-all} -->

<%= (item.getMemo()) ? item.getMemo() : "" %>

<p class="panel-title"><b>接口类型</b></p>

* `${ctx.text('ServiceAPIType',item.getAPIType())}`
<% if (item.getServiceType()) {%>\

<p class="panel-title"><b>服务类型</b></p>

* `${ctx.text('ServiceType',item.getServiceType())}`
<%}%>\
<% if (item.getServicePath()) {%>\

<p class="panel-title"><b>服务路径</b></p>

* `${item.getServicePath()}`
<%}%>\
<% if (item.getServiceParam()) {%>\

<p class="panel-title"><b>服务参数</b></p>

* `${item.getServiceParam()}`
<%}%>\
<% if (item.getServiceParam2()) {%>\

<p class="panel-title"><b>服务参数2</b></p>

* `${item.getServiceParam2()}`
<%}%>\
<% if (item.getServiceParam3()) {%>\

<p class="panel-title"><b>服务参数3</b></p>

* `${item.getServiceParam3()}`
<%}%>\
<% if (item.getServiceParam4()) {%>\

<p class="panel-title"><b>服务参数4</b></p>

* `${item.getServiceParam4()}`
<%}%>\
<% if (item.getAuthMode()) {%>\

<p class="panel-title"><b>认证模式</b></p>

* `${ctx.text('APIAuthMode',item.getAuthMode())}`
<%}%>\
<% if (item.getAuthAccessTokenUrl()) {%>\

<p class="panel-title"><b>认证token路径</b></p>

* `${item.getAuthAccessTokenUrl()}`
<%}%>\
<% if (item.getAuthClientId()) {%>\

<p class="panel-title"><b>认证客户端标识</b></p>

* `${item.getAuthClientId()}`
<%}%>\
<% if (item.getAuthClientSecret()) {%>\

<p class="panel-title"><b>认证客户端密钥</b></p>

* `${item.getAuthClientSecret()}`
<%}%>\
<% if (item.getAuthParam()) {%>\

<p class="panel-title"><b>客户端认证参数</b></p>

* `${item.getAuthParam()}`
<%}%>\
<% if (item.getAuthParam2()) {%>\

<p class="panel-title"><b>客户端认证参数2</b></p>

* `${item.getAuthParam2()}`
<%}%>\
<% if (item.getAuthParam3()) {%>\

<p class="panel-title"><b>客户端认证参数3</b></p>

* `${item.getAuthParam3()}`
<%}%>\
<% if (item.getAuthParam4()) {%>\

<p class="panel-title"><b>客户端认证参数4</b></p>

* `${item.getAuthParam4()}`
<%}%>\

#### 外部接口实体

<% if (item.getAllPSSubSysServiceAPIDEs()) {
  item.getAllPSSubSysServiceAPIDEs().each{ declient -> %>\
* [${declient.getDisplayName()}](client/declient/${declient.getCodeName()}.md)<%if(declient.getMemo()){%><br>${declient.getMemo()}<%}%>
<% }} %>




