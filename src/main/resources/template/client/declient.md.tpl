# ${item.getLogicName()}(${item.getCodeName()})

<%= (item.getMemo()) ? item.getMemo() : "" %>

<%
String strPath = "/" + cn.ibizlab.codegen.groovy.util.Inflector.getInstance().pluralize(item.getCodeName()).toLowerCase();
if (item.getPSSubSysServiceAPIDEMethods()) {
  item.getPSSubSysServiceAPIDEMethods().each{ method ->
  if ( method.getMethodType() == 'DEACTION' || method.getMethodType() == 'FETCH') {
  %>\

<!-- panels:start -->
<!-- div:left-panel -->
${method.getName()} `${method.getRequestMethod()}` `<%out.print(strPath)%><%= method.isNeedResourceKey() ? "/{key}" : "" %><%if(!method.isNoServiceCodeName()){%>/<%= method.getCodeName2() ? method.getCodeName2().toLowerCase() : method.getCodeName().toLowerCase() %><%}%>`
<%= (method.getMemo()) ? method.getMemo() : "" %>
<%if(method.isNeedResourceKey()){%>
<p class="panel-title"><b>PathVariable</b></p>

 * `key` - 主键
<%}%>

<%if(method.getPSSubSysServiceAPIMethodInput().getType() == 'DTO' || method.getPSSubSysServiceAPIMethodInput().getType() == 'DTOS'){%>
<p class="panel-title"><b>Body</b></p>

<% if (method.getPSSubSysServiceAPIMethodInput().getPSSubSysServiceAPIDTO().getPSSubSysServiceAPIDTOFields()) {
  method.getPSSubSysServiceAPIMethodInput().getPSSubSysServiceAPIDTO().getPSSubSysServiceAPIDTOFields().eachWithIndex{ field,index -> %>\
 * `${field.getName().toLowerCase()}` - ${field.getName()}
<% }} %>
<%}%>


<!-- div:right-panel -->
<!-- tabs:start -->

#### **Request**
<%if(method.isNeedResourceKey()){%>\
<p class="panel-title"><b>PathVariable</b></p>

```json
key
```
<%}%>

<%if(method.getPSSubSysServiceAPIMethodInput().getType() == 'DTO' || method.getPSSubSysServiceAPIMethodInput().getType() == 'DTOS'){%>\
<%if(method.isNeedResourceKey()){%>\
<p class="panel-title"><b>Body</b></p>
<%}%>
```json
{
<% if (method.getPSSubSysServiceAPIMethodReturn().getType() == 'PAGE' || (method.getPSSubSysServiceAPIMethodInput().getPSSubSysServiceAPIDTO() && method.getPSSubSysServiceAPIMethodInput().getPSSubSysServiceAPIDTO().getType() == 'DEFILTER')) { %>\
  "page" : 0,
  "size" : 20,
  "sort" : null,
<% } %>\
<% if (method.getPSSubSysServiceAPIMethodInput().getPSSubSysServiceAPIDTO() && method.getPSSubSysServiceAPIMethodInput().getPSSubSysServiceAPIDTO().getPSSubSysServiceAPIDTOFields()) {
  method.getPSSubSysServiceAPIMethodInput().getPSSubSysServiceAPIDTO().getPSSubSysServiceAPIDTOFields().eachWithIndex{ field,index -> %>\
  "${field.getName().toLowerCase()}" : null,
<% }} %>\
}
```
<%}%>

#### **Response**
```json
<%if(method.getPSSubSysServiceAPIMethodReturn().getType() == 'DTO' || method.getPSSubSysServiceAPIMethodReturn().getType() == 'DTOS' || method.getPSSubSysServiceAPIMethodReturn().getType() == 'PAGE'){%>\
<% if(method.getPSSubSysServiceAPIMethodReturn().getType() == 'PAGE') { %>[<% } %>
<% if(method.getPSSubSysServiceAPIMethodReturn().getType() == 'PAGE') { %>  <% } %>{
<% if (method.getPSSubSysServiceAPIMethodReturn().getPSSubSysServiceAPIDTO() && method.getPSSubSysServiceAPIMethodReturn().getPSSubSysServiceAPIDTO().getPSSubSysServiceAPIDTOFields()) {
  method.getPSSubSysServiceAPIMethodReturn().getPSSubSysServiceAPIDTO().getPSSubSysServiceAPIDTOFields().eachWithIndex{ field,index -> %>\
<% if(method.getPSSubSysServiceAPIMethodReturn().getType() == 'PAGE') { %>  <% } %>  "${field.getName().toLowerCase()}" : null,
<% }} %>\
<% if(method.getPSSubSysServiceAPIMethodReturn().getType() == 'PAGE') { %>  <% } %>}
<% if(method.getPSSubSysServiceAPIMethodReturn().getType() == 'PAGE') { %>]<% } %>
<% } else if(method.getPSSubSysServiceAPIMethodReturn().getType() == 'SIMPLE' || method.getPSSubSysServiceAPIMethodReturn().getType() == 'SIMPLES') { %>\
${net.ibizsys.model.PSModelEnums.StdDataType.from(method.getPSSubSysServiceAPIMethodReturn().getStdDataType()).text}

<% } else if(method.getPSSubSysServiceAPIMethodReturn().getType() == 'VOID') { %>\
void
<%}%>
```
<!-- tabs:end -->
<!-- panels:end -->
<% }} %>
<% } %>
