# ${item.getLogicName()}(${item.getCodeName()})  <!-- {docsify-ignore-all} -->

${ctx.ignoreNullString(item.getMemo())}

## 属性
|    中文名 | 属性名称           | 类型     | 长度     |值规则   |  序列     | 快速搜索     |  备注  |
| --------   |------------| -----  | -----  | ----- | -----  | :---:   |  -------- |
<% if (item.getAllPSDEFields()) {
  item.getAllPSDEFields().each{ field -> %>\
|${field.getLogicName()}|${field.getName()}|<%
if (field.getPSCodeList() && field.getPSCodeList().getPSSystemModule() && field.getPSCodeList().getCodeListType() == 'STATIC') {%>[<%}%><%= ctx.text('DEFDataType',field.getDataType()) %><% if (field.getPSCodeList() && field.getPSCodeList().getPSSystemModule() && field.getPSCodeList().getCodeListType() == 'STATIC') {%>](index/dictionary_index#${field.getPSCodeList().getCodeName()} "${field.getPSCodeList().getName()}")<%}%>|${field.getLength()}|<%
if (field.hasDEFValueRules()) { field.getAllPSDEFValueRules().eachWithIndex{ valuerule,index ->%><%=(index>0)?"<br>":""%>[${valuerule.getName()}](module/${item.getPSSystemModule().getCodeName()}/${item.getCodeName()}/value_rule/${field.getCodeName()}#${valuerule.getCodeName().toLowerCase()})<%}}%>|<%
if(field.getPSSysSequence()){%>[${field.getPSSysSequence().getName()}](index/sequence_index#${field.getPSSysSequence().getCodeName()})<%}%>|<%= (field.isEnableQuickSearch()) ? "√" : "" %>|${ctx.tableString(field.getMemo())}|
<% }} %>
<% if(item.getUnionKeyValuePSDEFields()){%>\

<p class="panel-title"><b>联合主键</b></p>

<% item.getUnionKeyValuePSDEFields().each{ field -> %>\
  * `${field.getLogicName()}(${field.getName()})`
<% }}%>\
<% if (item.getMajorPSDERs() || item.getMinorPSDERs()) {%>\

## 关系
<!-- tabs:start -->

<% if (item.getMajorPSDERs()) {%>\

#### **主关系**
| 名称     |   从实体 | 关系类型     |   备注  |
| -------- |---------- |------------|----- |
<% item.getMajorPSDERs().each{ der -> %>\
|[${der.getName()}](der/${der.getName()})|[${der.getMinorPSDataEntity().getDisplayName()}](module/${der.getMinorPSDataEntity().getPSSystemModule().getCodeName()}/${der.getMinorPSDataEntity().getCodeName()})|${ctx.text('DERType',der.getDERType())}|${ctx.tableString(der.getMemo())}|
<% }}%>\
<% if (item.getMinorPSDERs()) {%>\

#### **从关系**
|  名称   | 主实体   | 关系类型   |    备注  |
| -------- |---------- |-----------|----- |
<% item.getMinorPSDERs().each{ der -> %>\
|[${der.getName()}](der/${der.getName()})|[${der.getMajorPSDataEntity().getDisplayName()}](module/${der.getMajorPSDataEntity().getPSSystemModule().getCodeName()}/${der.getMajorPSDataEntity().getCodeName()})|${ctx.text('DERType',der.getDERType())}|${ctx.tableString(der.getMemo())}|
<% }}%>\
<!-- tabs:end -->
<% }%>\
<% if (item.getAllPSDEMaps()) {%>\

## 映射
| 名称    | 映射实体   | 备注  |
| -------- |----------  |----- |
<% item.getAllPSDEMaps().each{ map -> %>\
|[${map.getName()}](module/${item.getPSSystemModule().getCodeName()}/${item.getCodeName()}/demap/${map.getCodeName()})|[${map.getDstPSDE().getDisplayName()}](module/${map.getDstPSDE().getPSSystemModule().getCodeName()}/${map.getDstPSDE().getCodeName()})|${ctx.tableString(map.getMemo())}|
<% }}%>\

## 行为
| 中文名    | 代码名    | 类型    | 事务   | 批处理   | 附加操作  | 插件    |  备注  |
| -------- |---------- |----------- |------------|----------|---------| ----- | ----- |
<% if (item.getAllPSDEActions()) {
  item.getAllPSDEActions().each{ action -> %>\
|${action.getLogicName()}|${action.getCodeName()}|<%if (action.getActionType()=='DELOGIC' && action.getPSDELogic()){%>[<%}%>${ctx.text('DEActionType',action.getActionType())}<%if (action.getActionType()=='DELOGIC' && action.getPSDELogic()){%>](module/${item.getPSSystemModule().getCodeName()}/${item.getCodeName()}/logic/${action.getPSDELogic().getCodeName()} "${action.getPSDELogic().getLogicName()}")<%}%>|<%= ctx.text('DEActionTSMode',action.getTransactionMode()) %>|<%= ctx.text('DEActionBatchMode',action.getBatchActionMode()) %>|<% if (action.hasActionLogics()){%>[附加操作](index/action_logic_index#${item.getCodeName()}_${action.getName()})<%}%>|${action.getPluginLink()}|${ctx.tableString(action.getMemo())}|
<% }} %>
<% if (item.getAllPSDELogics()) {%>\

## 处理逻辑
| 中文名    | 代码名    | 子类型    | 插件    |  说明  |
| -------- |---------- |----------- |------------|----------|
<%   item.getAllPSDELogics().each{ logic -> %>\
|[${logic.getLogicName()}](module/${item.getPSSystemModule().getCodeName()}/${item.getCodeName()}/logic/${logic.getCodeName()})|${logic.getCodeName()}|${ctx.text('LogicSubType',logic.getLogicSubType())}||${ctx.tableString(logic.getMemo())}|
<% }} %>
<% if (item.getMainStatePSDEFields()) {%>\

## 主状态控制

<p class="panel-title"><b>控制属性</b></p>

<% if (item.getMainStatePSDEFields()) {
  item.getMainStatePSDEFields().each{ field -> %>\
* `${field.getLogicName()}(${field.getName()})` <%if (field.getPSCodeList() && field.getPSCodeList().getPSSystemModule() && field.getPSCodeList().getCodeListType() == 'STATIC') {%>[${field.getPSCodeList().getName()}](index/dictionary_index#${field.getPSCodeList().getCodeName()} "${field.getPSCodeList().getName()}")<%}%>
<% }}%>
<%
boolean checkMainStteField = true
item.getMainStatePSDEFields().each{field ->
    if(!(field.getPSCodeList() && field.getPSCodeList().getPSCodeItems())){
       checkMainStteField = false
    }
} %>
<% if (checkMainStteField) {%>

<p class="panel-title"><b>操作标识分布</b></p>
<br>
<table>
  <tr>
    <th rowspan="${item.getMainStatePSDEFields().size()}">操作标识</th>
<%  colspan = 1

    if(item.getMainStatePSDEFields().size()>1){
        item.getMainStatePSDEFields().eachWithIndex{field,index ->
            if(index > 0  && field.getPSCodeList() && field.getPSCodeList().getPSCodeItems()){
                colspan = colspan * field.getPSCodeList().getPSCodeItems().size()
            }
        }
    }
    item.getMainStatePSDEFields().get(0).getPSCodeList().getPSCodeItems().each{ codeitem->
        out << String.format('<th colspan="%s">%s</th>\n',colspan,codeitem.getText())
    }
%>\
  </tr>
<%if(item.getMainStatePSDEFields().size()>1){%>\
  <tr>
<%  colspan = 1
    if(item.getMainStatePSDEFields().size()>2){
        item.getMainStatePSDEFields().eachWithIndex{field,index ->
            if(index > 1  && field.getPSCodeList() && field.getPSCodeList().getPSCodeItems()){
                colspan = colspan * field.getPSCodeList().getPSCodeItems().size()
            }
        }
    }
    item.getMainStatePSDEFields().get(0).getPSCodeList().getPSCodeItems().each{ codeitem->
        item.getMainStatePSDEFields().get(1).getPSCodeList().getPSCodeItems().each{ seccodeitem->
            out << String.format('<th colspan="%s">%s</th>\n',colspan,seccodeitem.getText())
        }
    }
%>\
  </tr>
<%}%>\
<%if(item.getMainStatePSDEFields().size()>2){%>\
  <tr>
<%  colspan = 1
    item.getMainStatePSDEFields().get(0).getPSCodeList().getPSCodeItems().each{ codeitem->
        item.getMainStatePSDEFields().get(1).getPSCodeList().getPSCodeItems().each{ seccodeitem->
            item.getMainStatePSDEFields().get(2).getPSCodeList().getPSCodeItems().each{ thirdcodeitem->
                out << String.format('<th colspan="%s">%s</th>\n',colspan,thirdcodeitem.getText())
            }
        }
    }
%>\
  </tr>
<%}%>\
<% if (item.getAllPSDEOPPrivs()) {
  item.getAllPSDEOPPrivs().each{ oppriv -> %>\
  <tr>
    <td>${oppriv.getLogicName()}(${oppriv.getName()})<%if(oppriv.getMapPSDER()){%><br><a href ="/#/der/${oppriv.getMapPSDER().getName()}">${oppriv.getMapPSDER().getName()}</a><%}%></td>
<% item.getMainStatePSDEFields().get(0).getPSCodeList().getPSCodeItems().each{ codeitem->

    if(item.getMainStatePSDEFields().size() == 1)  {
        Object state = null
        for (int i = 0; i <= 1; i++) {
			String strTag = String.format("%s", ((i == 0) ? codeitem.getValue() : "*"));
		    if(item.getMainStateMap().containsKey(strTag)){
		        state = item.getMainStateMap().get(strTag)
		        break
		    }
		}
		if(state){
		    boolean allow = true
            Object op = state.getPSDEMainStateOPPrivs().find{stateoppriv -> stateoppriv.getPSDEOPPriv().getName().equals(oppriv.getName())}
            if(op){
                allow = state.isOPPrivAllowMode() ? true : false
            }else{
                allow = state.isOPPrivAllowMode() ? false : true
            }
            if(allow){
                out << String.format('<td align="center">%s</td>','<i class="fa fa-check"></i>')
            }else{
                out << String.format('<td align="center">%s</td>','')
            }
		}else{
            out << String.format('<td align="center">%s</td>','<i class="fa fa-check"></i>')
		}
    }
    if(item.getMainStatePSDEFields().size() == 2)  {
        boolean find = false
        for (int i = 0; i <= 1; i++) {
            String strTag = String.format("%s", ((i == 0) ? codeitem.getValue() : "*"));
            item.getMainStatePSDEFields().get(1).getPSCodeList().getPSCodeItems().each{ seccodeitem ->
                Object state = null
                for (int j = 0; j <= 1; j++) {
                    String strTag2 = String.format("%s__%s", strTag, ((j == 0) ? seccodeitem.getValue() : "*"));
                    if(item.getMainStateMap().containsKey(strTag2)){
                        state = item.getMainStateMap().get(strTag2)
                        break
                    }
                }
                if(state){
                    find = true
                    boolean allow = true
                    Object op = state.getPSDEMainStateOPPrivs().find{stateoppriv -> stateoppriv.getPSDEOPPriv().getName().equals(oppriv.getName())}
                    if(op){
                        allow = state.isOPPrivAllowMode() ? true : false
                    }else{
                        allow = state.isOPPrivAllowMode() ? false : true
                    }
                    if(allow){
                        out << String.format('<td align="center">%s</td>\n','<i class="fa fa-check"></i>')
                    }else{
                        out << String.format('<td align="center">%s</td>\n','')
                    }
                }else{
                    if(i == 1)
                        out << String.format('<td align="center">%s</td>\n','<i class="fa fa-check"></i>')
                }
            }
            if(find)
                break
        }
    }
    if(item.getMainStatePSDEFields().size() == 3)  {
        boolean find = false
        boolean find2 = false
        for (int i = 0; i <= 1; i++) {
            String strTag = String.format("%s", ((i == 0) ? codeitem.getValue() : "*"));
            item.getMainStatePSDEFields().get(1).getPSCodeList().getPSCodeItems().each{ seccodeitem ->
                find = false
                find2 = false
                for (int j = 0; j <= 1; j++) {
                    String strTag2 = String.format("%s__%s", strTag, ((j == 0) ? seccodeitem.getValue() : "*"));
                    item.getMainStatePSDEFields().get(2).getPSCodeList().getPSCodeItems().each{ thirdcodeitem ->
                        Object state = null
                        for (int k = 0; k <= 1; k++) {
                            String strTag3 = String.format("%s__%s", strTag2, ((k == 0) ? thirdcodeitem.getValue() : "*"));
                            if(item.getMainStateMap().containsKey(strTag3)){
                                state = item.getMainStateMap().get(strTag3)
                                break
                            }
                        }
                        if(state){
                            find = true
                            find2 = true
                            boolean allow = true
                            Object op = state.getPSDEMainStateOPPrivs().find{stateoppriv -> stateoppriv.getPSDEOPPriv().getName().equals(oppriv.getName())}
                            if(op){
                                allow = state.isOPPrivAllowMode() ? true : false
                            }else{
                                allow = state.isOPPrivAllowMode() ? false : true
                            }
                            if(allow){
                                out << String.format('<td align="center">%s</td>\n','<i class="fa fa-check"></i>')
                            }else{
                                out << String.format('<td align="center">%s</td>\n','')
                            }
                        }else{
                            if(i == 1 && j == 1)
                                out << String.format('<td align="center">%s</td>\n','<i class="fa fa-check"></i>')
                        }
                    }
                    if(find2)
                        break
                }
            }
            if(find)
                break
        }
    }
}%>\
  </tr>
<% }} %>\

</table>

<% } else { %>
> 控制属性未配置代码表，或者代码表未配置代码项
<% } %>
<% } %>
<% if (item.getAllPSDEDataQueries()) { %>\

## 数据查询
| 中文名    | 代码名    | 默认查询 | 是否权限使用 | 自定义SQL |  备注|
| --------  | --------   | :---:  | :---:  | :---:  |----- |
<%  item.getAllPSDEDataQueries().each{ query -> %>\
|[${query.getDisplayName()}](module/${item.getPSSystemModule().getCodeName()}/${item.getCodeName()}/query/${query.getCodeName()})|${query.getName()}|<%= (query.isDefaultMode()) ? "是" : "否" %>|<%= (query.isPrivQuery()) ? "是" : "否" %> |<%= (query.isCustomCode()) ? "是" : "否" %> |${ctx.tableString(query.getMemo())}|
<% }} %>
<% if (item.getAllPSDEDataSets()) { %>\

## 数据集合
| 中文名  | 代码名  | 类型 | 默认集合 |   插件|   备注|
| --------  | --------   | --------   | :---:   | ----- |----- |
<%   item.getAllPSDEDataSets().each{ dataset -> %>\
|[${dataset.getDisplayName()}](module/${item.getPSSystemModule().getCodeName()}/${item.getCodeName()}/dataset/${dataset.getCodeName()})|${dataset.getName()}|<%
    if(dataset.getDataSetType() == 'DELOGIC'){
        out << String.format('[%s](module/%s/%s/logic/%s)',ctx.text('DEDataSetType',dataset.getDataSetType()),item.getPSSystemModule().getCodeName(),item.getCodeName(),dataset.getPSDELogic().getCodeName())
    }else{
        out << ctx.text('DEDataSetType',dataset.getDataSetType())
    }
%>|<%= (dataset.isDefaultMode()) ? "是" : "否" %>|${dataset.getPluginLink()}|${ctx.tableString(dataset.getMemo())}|
<% }} %>
<% if (item.getAllPSDEUserRoles()) { %>\

## 数据权限

<%   item.getAllPSDEUserRoles().each{ derole -> %>\
##### ${derole.getName()} :id=${item.getCodeName()}-${derole.getRoleTag()}

<p class="panel-title"><b>数据范围</b></p>

<%if(derole.isAllData()){%>\
* `全部数据`
<% }else { %>\
<%if(derole.isEnableOrgDR()){%>\
* `组织范围` ：<%if((1&derole.getOrgDR())>0){%> <i class="fa fa-check-square"/></i> 当前组织<%}%><%if((2&derole.getOrgDR())>0){%> <i class="fa fa-check-square"/></i> 上级组织<%}%><%if((4&derole.getOrgDR())>0){%> <i class="fa fa-check-square"/></i> 下级组织<%}%>
<%}%>\
<%if(derole.isEnableSecDR()){%>\
* `部门范围` ：<%if((1&derole.getSecDR())>0){%> <i class="fa fa-check-square"/></i> 当前部门<%}%><%if((2&derole.getSecDR())>0){%> <i class="fa fa-check-square"/></i> 上级部门<%}%><%if((4&derole.getSecDR())>0){%> <i class="fa fa-check-square"/></i> 下级部门<%}%>
<%}%>\
<%if(derole.isEnableSecBC()){%>\
* `业务条线` ：`${derole.getSecBC()}`
<%}%>\
<%if(derole.getPSDEDataSet()){%>\
* `数据集合` ：[${derole.getPSDEDataSet().getDisplayName()}](module/${item.getPSSystemModule().getCodeName()}/${item.getCodeName()}#数据集合)
<%}%>\
<%if(derole.getCustomCond()){%>\
* `自定义条件` ：`${derole.getCustomCond()}`
<%}%>\
<% } %>\

<p class="panel-title"><b>数据能力</b></p>

<% if (derole.getPSDEUserRoleOPPrivs()) {
  derole.getPSDEUserRoleOPPrivs().each{ i -> %>\
* `${i.getPSDEOPPriv().getName()}<%if(i.getPSDEOPPriv().getMapPSDataEntity()){%>(${i.getPSDEOPPriv().getMapPSDataEntity().getLogicName()}(${i.getPSDEOPPriv().getMapPSDEOPPrivName()}))<%}%>`
<% }}%>


<% }} %>
<% if (item.getAllPSDENotifies()) {%>\
## 消息通知

|    名称   | 代码名       |  消息队列   |  消息模板 |  通知目标     |  备注  |
|------------| -----   |  -------- | -------- |-------- |-------- |
<%item.getAllPSDENotifies().each{ notify ->  %>\
|[${notify.getName()}](module/${item.getPSSystemModule().getCodeName()}/${item.getCodeName()}/notify/${notify.getCodeName()})|${notify.getCodeName()}|[${notify.getPSSysMsgQueue().getName()}](index/notify_index)|[${notify.getPSSysMsgTempl().getName()}](index/notify_index#${notify.getPSSysMsgTempl().getCodeName()})|<%notify.getPSDENotifyTargets()?.each {target ->%>${target.getName()} <%}%>|<%= ctx.tableString(notify.getMemo()) %>|
<%}}%>
<% if (item.getAllPSDEFields()) {%>\

## 搜索模式
|   搜索表达式   |    属性名    |    搜索模式        |
| -------- |------------|------------|
<%  item.getAllPSDEFields().each{ field ->
    field.getAllPSDEFSearchModes().each{ searchfield ->
  %>\
|${searchfield.getName()}|${field.getLogicName()}|${searchfield.getValueOP()}|
<% }}} %>
<% if (item.getAllPSDEDataImports()) {%>\

## 导入模式
<% item.getAllPSDEDataImports().each{ dataImport -> %>\
* ${dataImport.getName()}

|<% dataImport.getPSDEDataImportItems().each{ item ->   %><%= (item.getCaption()) ? item.getCaption() : item.getName() %>|<% } %>
|<% dataImport.getPSDEDataImportItems().each{ item ->   %> :------: |<% } %>
|<% dataImport.getPSDEDataImportItems().each{ item ->   %> - |<% } %>
<% }} %>
<% if (item.getAllPSDEDataExports()) {%>\

## 导出模式
<% item.getAllPSDEDataExports().each{ dataExport -> %>\
* ${dataExport.getName()}

|<% dataExport.getPSDEDataExportItems().each{ item ->   %><%= (item.getCaption()) ? item.getCaption() : item.getName() %>|<% } %>
|<% dataExport.getPSDEDataExportItems().each{ item ->   %> :------: |<% } %>
|<% dataExport.getPSDEDataExportItems().each{ item ->   %> - |<% } %>
<% }} %>
<% if (item.getAllPSAppDEUIActions()) {%>\

## 界面行为
|  中文名 |  代码名 |  标题   |     处理目标   |    处理类型        |  操作提示        |  刷新页面        |  附加操作       |
| --------| --------| -------- |------------|------------|------------|----------|----------|
<% item.getAllPSAppDEUIActions().each{ deUIAction -> %>\
| ${deUIAction.getName()} | ${deUIAction.getCodeName()} | ${deUIAction.getCaption()} |${ctx.text('UIActionTarget',deUIAction.getActionTarget())}|<%
if('BACKEND' == deUIAction.getUIActionMode()){
    if(deUIAction.getPSAppDEMethod()){
        out << '<details><summary>' << ctx.text('UIActionType',deUIAction.getUIActionMode()) << '</summary>'
        out << '[' << deUIAction.getPSAppDEMethod().getName() <<'](#行为)'
    } else {
        out << ctx.text('UIActionType',deUIAction.getUIActionMode())
    }
} else if('SYS' == deUIAction.getUIActionMode()){
        out << ctx.text('UIActionType',deUIAction.getUIActionMode())
} else if('FRONT' == deUIAction.getUIActionMode()){
    if('OTHER' == deUIAction.getFrontProcessType()){
        out << ctx.text('UIActionFrontType',deUIAction.getFrontProcessType())
    } else {
        out << '<details><summary>' << ctx.text('UIActionFrontType',deUIAction.getFrontProcessType()) << '</summary>'
    }
    if('WIZARD' == deUIAction.getFrontProcessType()||'TOP' == deUIAction.getFrontProcessType()){
        if(deUIAction.getFrontPSAppView()){
            out << '[' << deUIAction.getFrontPSAppView().getCaption() <<'](app/view/' << deUIAction.getFrontPSAppView().getCodeName() << ')'
        }
    }
    if('OPENHTMLPAGE' == deUIAction.getFrontProcessType()){
        if(deUIAction.getHtmlPageUrl()){
            out << '*' << deUIAction.getHtmlPageUrl() <<'*'
        }
    }
    if('PRINT' == deUIAction.getFrontProcessType() && deUIAction.getPSAppDEPrint()){
        out << '[' << deUIAction.getPSAppDEPrint().getName() <<']()'
    }
    if('DATAIMP' == deUIAction.getFrontProcessType() && deUIAction.getPSAppDEDataImport()){
        out << '[' << deUIAction.getPSAppDEDataImport().getName() <<']()'
    }
    if('DATAEXP' == deUIAction.getFrontProcessType() && deUIAction.getPSAppDEDataExport()){
        out << '[' << deUIAction.getPSAppDEDataExport().getName() <<']()'
    }
    if('OTHER' != deUIAction.getFrontProcessType()){
            out << '</details>'
    }
} else if('WFFRONT' == deUIAction.getUIActionMode()){
    out << ctx.text('UIActionType',deUIAction.getUIActionMode())
} else if('WFBACKEND' == deUIAction.getUIActionMode()){
    out << ctx.text('UIActionType',deUIAction.getUIActionMode())
} else if('CUSTOM' == deUIAction.getUIActionMode()){
    out << ctx.text('UIActionType',deUIAction.getUIActionMode())
}
%>|<%= (deUIAction.isEnableConfirm()) ? "是" : "否" %>|${ctx.text('UIActionReloadDataMode',deUIAction.getRefreshMode())}||
<% }} %>
<% if (item.getAllPSAppDEUILogics()) {%>\

## 界面逻辑
|  中文名 | 代码名 |
| --------|--------|
<% item.getAllPSAppDEUILogics().each{ uilogic -> %>\
|[${uilogic.getName()}](module/${item.getPSSystemModule().getCodeName()}/${item.getCodeName()}/uilogic/${uilogic.getCodeName()})|${uilogic.getCodeName()}|
<% }} %>