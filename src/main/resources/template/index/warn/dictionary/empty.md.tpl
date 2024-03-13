<% if (item.getEmptyCodeLists()) { %>\

### 未配置数据字典项<sup class="footnote-symbol"> <font color=orange>[${item.getEmptyCodeLists().size()}]</font></sup>
| 数据字典   | 代码标识  |
| --------   |------------|
<%  item.getEmptyCodeLists().each{ codelist -> %>\
|[${codelist.getName()}](index/dictionary_index#${codelist.getCodeName().toLowerCase()})|${codelist.getCodeName()}|
<% }}%>