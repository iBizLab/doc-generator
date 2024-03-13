# ${item.getName()} <!-- {docsify-ignore-all} -->
<% if (item.getParentPSModelObject(net.ibizsys.model.IPSSystem.class).getAllPSDataEntities()) {
  item.getParentPSModelObject(net.ibizsys.model.IPSSystem.class).getAllPSDataEntities().each{ entity -> %>\
<% if (entity.getStorageMode() == 1 && entity.getAllPSDEDataQueries()) { %>\

## [${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()}.md) :id=${entity.getCodeName()}

<%  entity.getAllPSDEDataQueries().each{ query -> %>\
#### ${query.getDisplayName()} :id=${entity.getCodeName()}-${query.getCodeName()}
<%  if (query.getAllPSDEDataQueryCodes()) {
    query.getAllPSDEDataQueryCodes().each{ queryCode ->
       if (queryCode.getDBType() == item.getDBType()) {%>\
```sql
${queryCode.getQueryCode()}
<%if(queryCode.getPSDEDataQueryCodeConds()){%>\
WHERE <%queryCode.getPSDEDataQueryCodeConds().eachWithIndex{cond,index ->
if(index>0){
out << ' AND '
}
out << cond.getCustomCond()
}%>
<%}%>\
```
<% }}} %>
<% }} %>\
<% }}%>