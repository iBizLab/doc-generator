<% if (item.getNoOPPrivMaps()) { %>\

### 操作标识未配置映射<sup class="footnote-symbol"> <font color=orange>[${item.getNoOPPrivMaps().size()}]</font></sup>
| 实体名   | 操作标识  |
| --------   |------------|
<%  item.getNoOPPrivMaps().each{ entry -> %>\
|[${entry.key.getDisplayName()}](module/${entry.key.getPSSystemModule().getCodeName()}/${entry.key.getCodeName()}.md)|<%
entry.value.eachWithIndex{opriv,index -> out << ((index>0)?'<br>':'') << opriv}
%>|
<% }}%>