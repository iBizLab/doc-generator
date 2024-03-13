<% if (item.getTextFieldQueries()) { %>\

### 包含长文本的查询<sup class="footnote-symbol"> <font color=orange>[${item.getTextFieldQueries().size()}]</font></sup>
| 实体   | 数据查询  |
| --------   |------------|
<%  item.getTextFieldQueries().each{ query -> %>\
|[${query.getParentPSModelObject().getDisplayName()}](module/${query.getParentPSModelObject().getPSSystemModule().getCodeName()}/${query.getParentPSModelObject().getCodeName()})|[${query.getDisplayName()}](module/${query.getParentPSModelObject().getPSSystemModule().getCodeName()}/${query.getParentPSModelObject().getCodeName()}/query/${query.getCodeName()})|
<% }}%>