拷贝参数<%if(item.getSrcPSDELogicParam()){%>`${item.getSrcPSDELogicParam().getInfo()}`<%}%> 到 <%if(item.getDstPSDELogicParam()){%>`${item.getDstPSDELogicParam().getInfo()}`<%}%>
<% if (item.getCopyFields()) { %>\

拷贝属性为：<% item.getCopyFields().each{ field -> out.print(" `"+field+"`") } %>
<% } %>\
<% if (item.isCopyIfNotExists()) { %>\

且仅拷贝不存在属性
<% } %>