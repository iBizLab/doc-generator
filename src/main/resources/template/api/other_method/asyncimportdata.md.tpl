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