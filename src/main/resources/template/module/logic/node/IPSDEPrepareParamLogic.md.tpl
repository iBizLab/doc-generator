<% if (item.getPSDELogicNodeParams()) {
  item.getPSDELogicNodeParams().eachWithIndex{ param,index -> %>\
${index+1}. <%= param.dstInfo ? "将": ""%>`${param.srcInfo}` <%= param.actionInfo ? param.actionInfo : ""%> <%= param.dstInfo ? " `" + param.dstInfo + "`" : ""%>
<% }} else { %>
    无
<% } %>