<p class="panel-title"><b>执行sql语句</b></p>

```sql
${item.getSql()}
```

<% if (item.getPSDELogicNodeParams()) {%>\
<p class="panel-title"><b>执行sql参数</b></p>

<%  item.getPSDELogicNodeParams().eachWithIndex{ param,index -> %>\
${index+1}. `${param.srcInfo}`
<% }} %>\
