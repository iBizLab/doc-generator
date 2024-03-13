<% if (item.getDstPSAppDataEntity() && item.getDstPSAppDELogic() ){ %>\
调用实体 [${item.getDstPSAppDataEntity().getDisplayName()}](module/${item.getDstPSAppDataEntity().getPSDataEntity().getPSSystemModule().getCodeName()}/${item.getDstPSAppDataEntity().getCodeName()}.md) 处理逻辑 [${item.getDstPSAppDELogic().getDisplayName()}]((module/${item.getDstPSAppDataEntity().getPSDataEntity().getPSSystemModule().getCodeName()}/${item.getDstPSAppDataEntity().getCodeName()}/logic/${item.getDstPSDELogic().getCodeName()}.md)) <%if(item.getDstPSDEUILogicParam()){%>，行为参数为`${item.getDstPSDEUILogicParam().getInfo()}`<%}%>
<% if (item.getRetPSDEUILogicParam()){ %>\
将执行结果返回给参数`${item.getRetPSDEUILogicParam().getInfo()}`
<% } %>\
<% } else { %>\
调用处理逻辑异常，请检查配置平台相关配置
<% } %>