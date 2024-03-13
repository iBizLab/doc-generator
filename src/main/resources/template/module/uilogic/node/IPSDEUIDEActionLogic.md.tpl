<% if (item.getDstPSAppDataEntity() && item.getDstPSAppDEAction() ){ %>\
调用实体 [${item.getDstPSAppDataEntity().getDisplayName()}](module/${item.getDstPSAppDataEntity().getPSDataEntity().getPSSystemModule().getCodeName()}/${item.getDstPSAppDataEntity().getCodeName()}.md) 行为 [${item.getDstPSAppDEAction().getPSDEAction().getDisplayName()}](module/${item.getDstPSAppDataEntity().getPSDataEntity().getPSSystemModule().getCodeName()}/${item.getDstPSAppDataEntity().getCodeName()}#行为) <%if(item.getDstPSDEUILogicParam()){%>，行为参数为`${item.getDstPSDEUILogicParam().getInfo()}`<%}%>
<% if (item.getRetPSDEUILogicParam()){ %>
将执行结果返回给参数`${item.getRetPSDEUILogicParam().getInfo()}`
<% }} else  {%>
调用行为异常，请检查配置平台相关配置
<% } %>