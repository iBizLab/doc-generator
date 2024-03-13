<%
String strPath = ""
if(item instanceof net.ibizsys.model.dataentity.service.IPSDEServiceAPI){
    strPath = "/" + cn.ibizlab.codegen.groovy.util.Inflector.getInstance().pluralize(item.getCodeName()).toLowerCase();
}
if(item instanceof net.ibizsys.model.dataentity.service.IPSDEServiceAPIRS){
    strPath = String.format("/%s/{pkey}/%s",
        cn.ibizlab.codegen.groovy.util.Inflector.getInstance().pluralize(item.getMajorPSDEServiceAPI().getCodeName()).toLowerCase(),
        cn.ibizlab.codegen.groovy.util.Inflector.getInstance().pluralize(item.getMinorPSDEServiceAPI().getCodeName()).toLowerCase()
    );
}
%>
<!-- panels:start -->
<!-- div:left-panel -->
数据打印 `GET` `<%out.print(strPath)%>/printdata/{key}`

<p class="panel-title"><b>PathVariable</b></p>

<%if(item instanceof net.ibizsys.model.dataentity.service.IPSDEServiceAPIRS){%>\
* pkey - `${item.getMajorPSDEServiceAPI().getLogicName()}主键标识`
<%}%>\
* key - `打印数据key`

<p class="panel-title"><b>RequestParam</b></p>

* srfprinttag - `打印标识`
* srfcontenttype - `报表类型`


<!-- div:right-panel -->
<!-- tabs:start -->

#### **Request**

<p class="panel-title"><b>PathVariable</b></p>

```json
key
```

<p class="panel-title"><b>RequestParam</b></p>

```
srfprinttag=PRINTTAG
srfcontenttype=PDF
```

#### **Response**
```file

```
<!-- tabs:end -->
<!-- panels:end -->