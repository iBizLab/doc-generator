# ${item.getName()} <!-- {docsify-ignore-all} -->

${ctx.ignoreNullString(item.getMemo())}

<% if (item.getPSSysUCMapNodes()) {%>\
```plantuml
@startuml
<style>
root {
  HyperlinkColor #42b983
}
</style>
left to right direction

<% item.getPSSysUCMapNodes().each{ node ->
     if(node.getNodeType() == 'ACTOR') {%>\
actor "${node.getPSSysActor().getName()}" as A_${node.getPSSysActor().getCodeName()} [[\$./${item.getCodeName()}#a_${node.getPSSysActor().getCodeName().toLowerCase()} {${node.getPSSysActor().getName()}}]]
<% } else if (node.getNodeType() == 'USECASE') {%>\
usecase "${node.getPSSysUseCase().getName()}" as C_${node.getPSSysUseCase().getCodeName()} [[\$./${item.getCodeName()}#c_${node.getPSSysUseCase().getCodeName().toLowerCase()} {${node.getPSSysUseCase().getName()}}]]
<% }} %>

<% if (item.getPSSysUseCaseRSs()) {
  item.getPSSysUseCaseRSs().each{ rs -> %>\
<%= rs.getFromPSSysActor() ? "A_":"C_"%>${rs.getFromPSUMLObject().getCodeName()} ${rs.getRSTypeLink()} <%= rs.getToPSSysActor() ? "A_":"C_"%>${rs.getToPSUMLObject().getCodeName()} : ${ctx.text('UseCaseRSType', rs.getRSType())}
<% }} %>

@enduml
```
<% } %>

### 用例
<% if (item.getPSSysUCMapNodes()) {
  item.getPSSysUCMapNodes().each{ node ->
    if(node.getNodeType() == 'USECASE') { %>\
####  ${node.getName()}  :id=C_${node.getPSSysUseCase().getCodeName()}

${ctx.ignoreNullString(node.getPSSysUseCase().getContent())}

<% }}} %>


### 角色
<% if (item.getPSSysUCMapNodes()) {
  item.getPSSysUCMapNodes().each{ node ->
    if(node.getNodeType() == 'ACTOR') { %>\
####  ${node.getName()}  :id=A_${node.getPSSysActor().getCodeName()}

${ctx.ignoreNullString(node.getPSSysActor().getContent())}

<% }}} %>
