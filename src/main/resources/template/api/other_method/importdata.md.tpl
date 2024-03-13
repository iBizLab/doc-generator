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
数据导入 `POST` `<%out.print(strPath)%>/importdata`
<%if(item instanceof net.ibizsys.model.dataentity.service.IPSDEServiceAPIRS){%>\

<p class="panel-title"><b>PathVariable</b></p>

* pkey - `${item.getMajorPSDEServiceAPI().getLogicName()}主键标识`
<%}%>\

<p class="panel-title"><b>RequestParam</b></p>

* srfimporttag - `导入标识`

<p class="panel-title"><b>Body</b></p>

* file - `导入数据.xlsx`

<!-- div:right-panel -->
<!-- tabs:start -->

#### **Request**

<p class="panel-title"><b>RequestParam</b></p>

```json
srfimporttag=IMPORTTAG
```

<p class="panel-title"><b>Body</b></p>

```
file=导入数据.xlsx
```

#### **Response**
```json
{
  1:导入错误信息,
  2:导入错误信息,
  3:导入错误信息,
  ...
}
```
<!-- tabs:end -->
<!-- panels:end -->