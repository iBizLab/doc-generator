# 服务插件 <!-- {docsify-ignore-all} -->

|  对象      |  实体  |  类型  | 插件  |
|  --------  | ----- | -----    |-----    |
<%item.getPluginMap()?.each{ entry ->  %>\
|${entry.key.getDisplayName()}|\
<%if(entry.key instanceof net.ibizsys.model.dataentity.IPSDataEntityObject){
    def entity = entry.key.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class)%>\
[${entity.getDisplayName()}](module/${entity.getPSSystemModule().getCodeName()}/${entity.getCodeName()})\
<%}%>\
|${entry.key.getClass().getSimpleName()}|[${entry.value.getName()}](#${entry.value.getCodeName()})|
<%}%>\

<% if (item.getAllPSSysSFPlugins()) {
  item.getAllPSSysSFPlugins().each{ plugin -> %>\
### ${plugin.getName()} :id=${plugin.getCodeName()}
${ctx.ignoreNullString(plugin.getMemo())}

```${plugin.getRTObjectName()}```

```groovy
${plugin.getTemplCode()}
```
<% }}%>





