<% if (item.getNoQueryDatasets()) { %>\

### 未配置查询的数据集合<sup class="footnote-symbol"> <font color=orange>[${item.getNoQueryDatasets().size()}]</font></sup>
| 实体   | 数据集合  |
| --------   |------------|
<%  item.getNoQueryDatasets().each{ dataset -> %>\
|[${dataset.getParentPSModelObject().getDisplayName()}](module/${dataset.getParentPSModelObject().getPSSystemModule().getCodeName()}/${dataset.getParentPSModelObject().getCodeName()})|[${dataset.getDisplayName()}](module/${dataset.getParentPSModelObject().getPSSystemModule().getCodeName()}/${dataset.getParentPSModelObject().getCodeName()}/dataset/${dataset.getCodeName()})|
<% }}%>