<% if (item.getGroupDatasets()) { %>\

### 分组数据集合<sup class="footnote-symbol"> <font color=orange>[${item.getGroupDatasets().size()}]</font></sup>   <!-- {docsify-ignore-all} -->
| 实体   | 数据集合  |
| --------   |------------|
<%  item.getGroupDatasets().each{ dataset -> %>\
|[${dataset.getParentPSModelObject().getDisplayName()}](module/${dataset.getParentPSModelObject().getPSSystemModule().getCodeName()}/${dataset.getParentPSModelObject().getCodeName()})|[${dataset.getDisplayName()}](module/${dataset.getParentPSModelObject().getPSSystemModule().getCodeName()}/${dataset.getParentPSModelObject().getCodeName()}/dataset/${dataset.getCodeName()})|
<% }}%>