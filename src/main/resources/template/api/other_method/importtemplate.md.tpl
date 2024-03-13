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
下载导入模板 `GET` `<%out.print(strPath)%>/importtemplate`
<%if(item instanceof net.ibizsys.model.dataentity.service.IPSDEServiceAPIRS){%>\

<p class="panel-title"><b>PathVariable</b></p>

* pkey - `${item.getMajorPSDEServiceAPI().getLogicName()}主键标识`
<%}%>\

<p class="panel-title"><b>RequestParam</b></p>

* srfimporttag - `导入模板标识`

<!-- div:right-panel -->
<!-- tabs:start -->

#### **Request**

<p class="panel-title"><b>RequestParam</b></p>

```json
srfimporttag=IMPORTTAG
```

#### **Response**
```file
数据导入模板.xlsx
```
<!-- tabs:end -->
<!-- panels:end -->