<p class="panel-title"><b>执行sql语句</b></p>

```sql
${item.getSql()}
```

<% if (item.getPSDELogicNodeParams()) {%>\
<p class="panel-title"><b>执行sql参数</b></p>

<%  item.getPSDELogicNodeParams().eachWithIndex{ param,index -> %>\
${index+1}. `${param.srcInfo}`
<% }} %>\

<% if (item.isFillDstLogicParam()) { %>\
<% if (item.isIgnoreResetDstLogicParam()) { %>\
重置参数`${item.getDstPSDELogicParam().getCodeName()}<%= item.getDstPSDELogicParam().getLogicName()?  "("+item.getDstPSDELogicParam().getLogicName()+")" : ""%>`，并将执行sql结果赋值给参数`${item.getDstPSDELogicParam().getCodeName()}<%= item.getDstPSDELogicParam().getLogicName()?  "("+item.getDstPSDELogicParam().getLogicName()+")" : ""%>`
<% } else { %>\
将执行sql结果赋值给参数`${item.getDstPSDELogicParam().getCodeName()}<%= item.getDstPSDELogicParam().getLogicName()?  "("+item.getDstPSDELogicParam().getLogicName()+")" : ""%>`
<% } %>\
<% } %>