# 数据字典  <!-- {docsify-ignore-all} -->

<% if (item.getAllPSCodeLists()) {
  item.getAllPSCodeLists().each{ codelist ->
    if (codelist.getPSSystemModule() && codelist.getCodeListType() == 'STATIC') {%>\
##### ${codelist.getName()} :id=${codelist.getCodeName()}

<%= (item.getMemo()) ? item.getMemo() : "" %>

| 值        |    文本    |   代码名    |  备注     |
| --------   |------------|------------|------------|
<% if (codelist.getPSCodeItems()) {
  codelist.getPSCodeItems().each{ codeitem -> %>\
|${codeitem.getValue()}|${codeitem.getText()}|${codeitem.getCodeName()}|${ctx.tableString(codeitem.getMemo())}|
<% }} %>
<% }}} else { %>
    无
<% } %>