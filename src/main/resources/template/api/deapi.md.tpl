# ${item.getLogicName()}(${item.getCodeName()})

<%= (item.getMemo()) ? item.getMemo() : "" %>

<%
String strPath = "/" + cn.ibizlab.codegen.groovy.util.Inflector.getInstance().pluralize(item.getCodeName()).toLowerCase();
if (item.getPSDEServiceAPIMethods()) {
  item.getPSDEServiceAPIMethods().each{ method ->
  if ( method.getMethodType() == 'DEACTION' || method.getMethodType() == 'FETCH') {
  %>\

<!-- panels:start -->
<!-- div:left-panel -->
${method.getName()} `${method.getRequestMethod()}` `<%out.print(strPath)%><%= method.isNeedResourceKey() ? "/{key}" : "" %><%= method.getRequestPath() ? method.getRequestPath() : "" %>` <%= method.getDataAccessAction() ? '<i class="fa fa-key"></i>`'+method.getDataAccessAction() +"`" : "" %>
<%= (method.getMemo()) ? method.getMemo() : "" %>
<%if(method.isNeedResourceKey()){%>
<p class="panel-title"><b>PathVariable</b></p>

 * `key` - <%= (item.getPSDataEntity() && item.getPSDataEntity().getKeyPSDEField()) ? item.getPSDataEntity().getKeyPSDEField().getLogicName() : "主键" %>
<%}%>

<%if(method.getPSDEServiceAPIMethodInput().getType() == 'DTO' || method.getPSDEServiceAPIMethodInput().getType() == 'DTOS'){%>
<p class="panel-title"><b>Body</b></p>

<% if (method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO().getPSDEMethodDTOFields()) {
  method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO().getPSDEMethodDTOFields().eachWithIndex{ field,index -> %>\
 * `${field.getName().toLowerCase()}` - ${field.getLogicName()}
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

<%if(method.getPSDEServiceAPIMethodInput().getType() == 'DTO' || method.getPSDEServiceAPIMethodInput().getType() == 'DTOS'){%>\
<%if(method.isNeedResourceKey()){%>\
<p class="panel-title"><b>Body</b></p>
<%}%>
```json
{
<% if (method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO() && method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO().getType() == 'DEFILTER') { %>\
  "page" : 0,
  "size" : 20,
  "sort" : null,
<% } %>\
<% if (method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO() && method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO().getPSDEMethodDTOFields()) {
  method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO().getPSDEMethodDTOFields().eachWithIndex{ field,index -> %>\
  "${field.getName().toLowerCase()}" : null,
<% }} %>\
}
```
<%}%>

#### **Response**
```json
<%if(method.getPSDEServiceAPIMethodReturn().getType() == 'DTO' || method.getPSDEServiceAPIMethodReturn().getType() == 'DTOS' || method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE'){%>\
<% if(method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE') { %>[<% } %>
<% if(method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE') { %>  <% } %>{
<% if (method.getPSDEServiceAPIMethodReturn().getPSDEMethodDTO() && method.getPSDEServiceAPIMethodReturn().getPSDEMethodDTO().getPSDEMethodDTOFields()) {
  method.getPSDEServiceAPIMethodReturn().getPSDEMethodDTO().getPSDEMethodDTOFields().eachWithIndex{ field,index -> %>\
<% if(method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE') { %>  <% } %>  "${field.getName().toLowerCase()}" : null,
<% }} %>\
<% if(method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE') { %>  <% } %>}
<% if(method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE') { %>]<% } %>
<% } else if(method.getPSDEServiceAPIMethodReturn().getType() == 'SIMPLE' || method.getPSDEServiceAPIMethodReturn().getType() == 'SIMPLES') { %>\
${net.ibizsys.model.PSModelEnums.StdDataType.from(method.getPSDEServiceAPIMethodReturn().getStdDataType()).text}

<% } else if(method.getPSDEServiceAPIMethodReturn().getType() == 'VOID') { %>\
void
<%}%>
```
<!-- tabs:end -->
<!-- panels:end -->
<% }} %>
<%= ctx.output(item,'/api/other_method/importtemplate.md.tpl') %>
<%= ctx.output(item,'/api/other_method/exportdata.md.tpl') %>
<%= ctx.output(item,'/api/other_method/importdata.md.tpl') %>
<%= ctx.output(item,'/api/other_method/importdata2.md.tpl') %>
<%= ctx.output(item,'/api/other_method/asyncimportdata2.md.tpl') %>
<%= ctx.output(item,'/api/other_method/printdata.md.tpl') %>
<%= ctx.output(item,'/api/other_method/report.md.tpl') %>
<% } %>

<%if(item.getMinorPSDEServiceAPIRSs()){
  item.getMinorPSDEServiceAPIRSs().each{ rs ->
  strPath = "/" + cn.ibizlab.codegen.groovy.util.Inflector.getInstance().pluralize(item.getCodeName()).toLowerCase();
  strPath = String.format('/%s/{pkey}%s',cn.ibizlab.codegen.groovy.util.Inflector.getInstance().pluralize(rs.getMajorPSDEServiceAPI().getCodeName()).toLowerCase(),strPath)
  %>\
### ${rs.getMajorPSDEServiceAPI().getName()}

<%    if(rs.getPSDEServiceAPIMethods()){
      rs.getPSDEServiceAPIMethods().each{ method ->
          if ( method.getMethodType() == 'DEACTION' || method.getMethodType() == 'FETCH') {%>\
<!-- panels:start -->
<!-- div:left-panel -->
${method.getName()} `${method.getRequestMethod()}` `<%out.print(strPath)%><%= method.isNeedResourceKey() ? "/{key}" : "" %><%= method.getRequestPath() ? method.getRequestPath() : "" %>` <%= method.getDataAccessAction() ? '<i class="fa fa-key"></i>`'+method.getDataAccessAction() +"`" : "" %>
<%= (method.getMemo()) ? method.getMemo() : "" %>

<p class="panel-title"><b>PathVariable</b></p>

 * `pkey` - ${rs.getMajorPSDEServiceAPI().getPSDataEntity().getLogicName()}主键
<%if(method.isNeedResourceKey()){%>\
 * `key` - <%= (item.getPSDataEntity() && item.getPSDataEntity().getKeyPSDEField()) ? item.getPSDataEntity().getKeyPSDEField().getLogicName() : "主键" %>
<%}%>

<%if(method.getPSDEServiceAPIMethodInput().getType() == 'DTO' || method.getPSDEServiceAPIMethodInput().getType() == 'DTOS'){%>
<p class="panel-title"><b>Body</b></p>

<% if (method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO().getPSDEMethodDTOFields()) {
  method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO().getPSDEMethodDTOFields().eachWithIndex{ field,index -> %>\
 * `${field.getName().toLowerCase()}` - ${field.getLogicName()}
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

<%if(method.getPSDEServiceAPIMethodInput().getType() == 'DTO' || method.getPSDEServiceAPIMethodInput().getType() == 'DTOS'){%>\
<%if(method.isNeedResourceKey()){%>\
<p class="panel-title"><b>Body</b></p>
<%}%>
```json
{
<% if (method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO() && method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO().getType() == 'DEFILTER') { %>\
  "page" : 0,
  "size" : 20,
  "sort" : null,
<% } %>\
<% if (method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO() && method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO().getPSDEMethodDTOFields()) {
  method.getPSDEServiceAPIMethodInput().getPSDEMethodDTO().getPSDEMethodDTOFields().eachWithIndex{ field,index -> %>\
  "${field.getName().toLowerCase()}" : null,
<% }} %>\
}
```
<%}%>

#### **Response**
```json
<%if(method.getPSDEServiceAPIMethodReturn().getType() == 'DTO' || method.getPSDEServiceAPIMethodReturn().getType() == 'DTOS' || method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE'){%>\
<% if(method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE') { %>[<% } %>
<% if(method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE') { %>  <% } %>{
<% if (method.getPSDEServiceAPIMethodReturn().getPSDEMethodDTO() && method.getPSDEServiceAPIMethodReturn().getPSDEMethodDTO().getPSDEMethodDTOFields()) {
  method.getPSDEServiceAPIMethodReturn().getPSDEMethodDTO().getPSDEMethodDTOFields().eachWithIndex{ field,index -> %>\
<% if(method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE') { %>  <% } %>  "${field.getName().toLowerCase()}" : null,
<% }} %>\
<% if(method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE') { %>  <% } %>}
<% if(method.getPSDEServiceAPIMethodReturn().getType() == 'PAGE') { %>]<% } %>
<% } else if(method.getPSDEServiceAPIMethodReturn().getType() == 'SIMPLE' || method.getPSDEServiceAPIMethodReturn().getType() == 'SIMPLES') { %>\
${net.ibizsys.model.PSModelEnums.StdDataType.from(method.getPSDEServiceAPIMethodReturn().getStdDataType()).text}

<% } else if(method.getPSDEServiceAPIMethodReturn().getType() == 'VOID') { %>\
void
<%}%>
```
<!-- tabs:end -->
<!-- panels:end -->
<%}}}%>
<%= ctx.output(rs,'/api/other_method/importtemplate.md.tpl') %>
<%= ctx.output(rs,'/api/other_method/exportdata.md.tpl') %>
<%= ctx.output(rs,'/api/other_method/importdata.md.tpl') %>
<%= ctx.output(rs,'/api/other_method/importdata2.md.tpl') %>
<%= ctx.output(rs,'/api/other_method/asyncimportdata2.md.tpl') %>
<%= ctx.output(rs,'/api/other_method/printdata.md.tpl') %>
<%= ctx.output(rs,'/api/other_method/report.md.tpl') %>
<%}}%>