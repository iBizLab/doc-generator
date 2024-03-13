<% if (item.getAllPSSysUCMaps()) {%>\
*  <i class="fa fa-odnoklassniki"></i> 系统用例
<%  item.getAllPSSysUCMaps().each{ uc -> %>\
  * [${uc.getName()}](uc/${uc.getCodeName()})
<% }} %>
<% if (item.getAllPSSysERMaps()) {%>\
* <i class="fa-solid fa-diagram-project"></i> 系统ER图
<%  item.getAllPSSysERMaps().each{ er -> %>\
  * [${er.getName()}](er/${er.getCodeName()})
<% }} %>
* <i class="fa fa-th-list"></i> 系统模块
<% if (item.getAllPSSystemModules()) {
  item.getAllPSSystemModules().each{ module -> %>\
  * [${module.getName()}](module/${module.getCodeName()})
<% }} %>
<% if ((item.getAllPSWorkflows()) || (item.getAllPSWFRoles())) { %>\
* <i class="fa fa-gg"></i> 工作流
<% if ((item.getAllPSWorkflows()) ) { %>\
  * [流程配置](index/wf_index)
<% } %>\
<% if ((item.getAllPSWFRoles())) { %>\
  * [流程角色](index/wfrole_index)
<% } %>\
<% } %>\
* <i class="fa-solid fa-shield-halved"></i> 系统权限
  * [系统角色](index/security_index)
<% if (item.getAllPSSysServiceAPIs()) {%>\
* <i class="fa fa-usb"></i> 服务接口
<% item.getAllPSSysServiceAPIs().each{ api -> %>\
  * [${api.getName()}](api/${api.getCodeName()})
<% }} %>
<% if (item.getAllPSSubSysServiceAPIs()) {%>\
* <i class="fa fa-plug"></i> 对接外部接口
<% item.getAllPSSubSysServiceAPIs().each{ client -> %>\
  * [${client.getName()}](client/${client.getCodeName()})
<% }} %>
* <i class="fa fa-desktop"></i> 系统应用
<% if (item.getAllPSApps()) {
  item.getAllPSApps().each{ app -> %>\
  * [${app.getName()}](app/${app.getCodeName()})
<% }}%>
* <i class="fa fa-paperclip"></i> 附录
  * [数据字典](index/dictionary_index)
  * [属性值规则](index/value_rule_index)
<% if (item.getAllPSDERs()) {%>\
  * [实体关系](index/der_index)
<% }%>\
<% if (item.hasDEMaps()) {%>\
  * [实体映射](index/demap_index)
<% }%>\
<% if (item.getGroupDatasets()) {%>\
  * [分组数据集合](index/group_dataset_index)
<% }%>\
<% if (item.hasLogics()) {%>\
  * [处理逻辑](index/logic_index)
<% }%>\
<% if (item.hasActionLogics()) {%>\
  * [行为附加](index/action_logic_index)
<% }%>\
<% if (item.hasUILogics()) {%>\
  * [界面逻辑](index/ui_logic_index)
<% }%>\
<% if (item.getAllPSSysValueRules()) {%>\
  * [系统值规则](index/sys_value_rule_index)
<% }%>\
<% if (item.getAllPSSysSequences()) {%>\
  * [系统序列](index/sequence_index)
<% }%>\
<%if(item.getAllPSSysMsgQueues()){%>\
  * [消息通知](index/notify_index)
<% }%>\
<% if (item.getAllBackServices()) {%>\
  * [后台服务](index/task_index)
<% }%>\
<% if (item.getAllPSSysSFPlugins()) {%>\
  * [服务插件](index/sfplugin_index)
<% }%>\
<% if (item.getAllAppPFPluginRefs()) {%>\
  * [前端插件](index/pfplugin_index)
<% }%>\
<% if (item.getAllPSSystemDBConfigs()) {%>\
  * 数据库查询
<% item.getAllPSSystemDBConfigs().each{ db -> %>\
    * [${db.getName()}](index/${db.getName()}_db_query_index)
<% }} %>
* [<i class="fa fa-warning" /></i> 模型预警](index/warn_index)