# ${item.getName()} <!-- {docsify-ignore-all} -->

${ctx.ignoreNullString(item.getMemo())}

<% if (item.getPSSysERMapNodes()) {%>\
```plantuml
@startuml
<style>
root {
  HyperlinkColor #42b983
}
</style>
left to right direction

<% item.getPSSysERMapNodes().each{ node ->
 if (node.getPSDataEntity()) { %>\
entity "${node.getPSDataEntity().getName()}\\n${node.getPSDataEntity().getLogicName()}" as ${node.getPSDataEntity().getName()} [[\$../module/${node.getPSDataEntity().getPSSystemModule().getCodeName()}/${node.getPSDataEntity().getCodeName()} {${node.getPSDataEntity().getLogicName()}}]] {
<% if (node.getPSDataEntity().getKeyPSDEField()) { %>\
        <&key> ${node.getPSDataEntity().getKeyPSDEField().getName()} - ${node.getPSDataEntity().getKeyPSDEField().getLogicName()}
        --
<% } %>\
<% if (node.getPSDataEntity().getAllPSDEFields()) {
  node.getPSDataEntity().getAllPSDEFields().each{ field ->
  if (field.getDataType() == 'PICKUP') {%>\
        <&link-intact> ${field.getName()} - ${field.getLogicName()}
<% }}} %>\
}
<% }} %>\

<% if (item.getDERs()) {
  item.getDERs().each{ der -> %>\
${der.getMinorPSDataEntity().getName()}${der.getDERTypeLink()} ${der.getMajorPSDataEntity().getName()} : [[\$../der/${der.getName()}{${der.getName()}} ${ctx.text('DERType', der.getDERType())}]]
<% }} %>

hide methods
@enduml
```
<% } %>