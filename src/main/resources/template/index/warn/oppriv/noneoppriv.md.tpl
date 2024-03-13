<% if (item.getNoneOPPrivMethods()) { %>\

### NONE权限请求接口<sup class="footnote-symbol"> <font color=orange>[${item.getNoneOPPrivMethods().size()}]</font></sup>
 | 请求路径| 请求方式| 实体   |    行为/集合    |
| -------- |-------- | --------|-------- |
<%  item.getNoneOPPrivMethods().each{ method ->
    Object iPSDEServiceAPI = method.getParentPSModelObject(net.ibizsys.model.dataentity.service.IPSDEServiceAPI.class)
    String strPath = "/" + cn.ibizlab.codegen.groovy.util.Inflector.getInstance().pluralize(iPSDEServiceAPI.getCodeName()).toLowerCase();
    if(method.isNeedResourceKey()){
        strPath += "/{key}"
    }
    if(method.getRequestPath()){
        strPath += method.getRequestPath()
    }
    %>\
|${strPath}|${method.getRequestMethod()}|[${iPSDEServiceAPI.getPSDataEntity().getDisplayName()}](module/${iPSDEServiceAPI.getPSDataEntity().getPSSystemModule().getCodeName()}/${iPSDEServiceAPI.getPSDataEntity().getCodeName()}.md)|<%
out << String.format('[%s](module/%s/%s#%s)',(method.getPSDEAction())?method.getPSDEAction().getDisplayName():method.getPSDEDataSet().getDisplayName(),iPSDEServiceAPI.getPSDataEntity().getPSSystemModule().getCodeName(),iPSDEServiceAPI.getPSDataEntity().getCodeName(), (method.getPSDEAction())?"行为":"数据集合")
%>|
<% }}%>