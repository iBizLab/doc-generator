# 系统概要

<% if (item.getAllPSSystemDBConfigs()) {%>\
##### 支持数据库

<% item.getAllPSSystemDBConfigs().each{ db -> %>\
* [${db.getName()}](db/${db.getName()})
<% }} %>\

<% if (item.getAllPSSystemModules()) { %>\
##### 系统模块

<% item.getAllPSSystemModules().each{ module -> %>\
* [${module.getName()}](module/${module.getCodeName()})
<% }} %>\

<% if (item.getAllPSWorkflows()) { %>\
##### 工作流

<% item.getAllPSWorkflows().each{ workflow -> %>\
* [${workflow.getName()}](workflow/${workflow.getCodeName()})
<% }} %>\

<% if (item.getAllPSSysServiceAPIs()) { %>\
##### 服务接口

<% item.getAllPSSysServiceAPIs().each{ api -> %>\
* [${api.getName()}](api/${api.getCodeName()})
<% }} %>\

<% if (item.getAllPSSubSysServiceAPIs()) { %>\
##### 对接外部接口

<% item.getAllPSSubSysServiceAPIs().each{ client -> %>\
* [${client.getName()}](client/${client.getCodeName()})
<% }} %>\

<% if (item.getAllPSApps()) { %>\
##### 系统应用

<% item.getAllPSApps().each{ app -> %>\
* [${app.getName()}](app/${app.getPKGCodeName()})
<% }} %>