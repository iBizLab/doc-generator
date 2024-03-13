## ${item.getLogicName()}(${item.getCodeName()}) <!-- {docsify-ignore-all} -->

<%= (item.getMemo()) ? item.getMemo() : "" %>

<br>
<p class="panel-title"><b>是否默认查询</b></p>

* `<%= (item.isDefaultMode()) ? "是" : "否" %>`

<p class="panel-title"><b>是否权限使用</b></p>

* `<%= (item.isPrivQuery()) ? "是" : "否" %>`

<p class="panel-title"><b>是否自定义SQL</b></p>

* `<%= (item.isCustomCode()) ? "是" : "否" %>`

<p class="panel-title"><b>查询列级别</b></p>

* `${ctx.text('DEDataQueryViewLevel',item.getViewLevel())}`
<%if(100 == item.getViewLevel() && item.getPSDEFGroup()){ %>\
*  **属性组：**[${item.getPSDEFGroup().getName()}](#)
<%item.getPSDEFGroup().getPSDEFGroupDetails().each{ detail ->
    if (detail.getPSDEField()){%>\
  * `${detail.getPSDEField().getName()}(${detail.getPSDEField().getLogicName()})`
<%}}}%>\
<% if (item.getTextFields()) {%>\

> [!ATTENTION|label:存在长文本属性]
<%item.getTextFields().each {field -> %>\
>
> `${field.getName()}(${field.getLogicName()})`
<%}}%>

<%
def  recursionConditions
recursionConditions = {condition ->
    if(condition){
        if(condition instanceof net.ibizsys.model.dataentity.ds.IPSDEDQGroupCondition){
            if(condition.getPSDEDQConditions()){
                out << '('
                condition.getPSDEDQConditions().eachWithIndex { subcond,index ->
                    if(index>0)
                        out << ' ' << condition.getCondOp() << ' '
                    recursionConditions(subcond)
                }
                out << ')'
            }
        }
        if(condition instanceof net.ibizsys.model.dataentity.ds.IPSDEDQFieldCondition){
            out << '`' << condition.getFieldName()
            if(condition.getPSDEField())
                out << '(' << condition.getPSDEField().getLogicName() << ')'
            out << '` ' << condition.getCondOp()
            if(condition.getCondOp()!='ISNULL' && condition.getCondOp()!='ISNOTNULL'){
                out << ' `'
                if(condition.getPSVARTypeId()){
                    out << ctx.text('DEDQVarType',condition.getPSVARTypeId()) << '.' << condition.getCondValue()
                }else{
                    out<< '\'' << condition.getCondValue() << '\''
                }
                out << '`'
            }
        }
        if(condition instanceof net.ibizsys.model.dataentity.ds.IPSDEDQCustomCondition){
            out << '`' << condition.getCondition() << '`'
        }
    }
    return ''
}

def  recursionJoins
recursionJoins = {int index,List joins ->
    if(joins){
        joins.each {join->
            if(index > 0){
                for (i in 0..index){
                    out << '  '
                }
            }
            out << '* '
            out << '**' << join.getName() << '**<br>\n'
            out << '连接关系：' << String.format('[%s](der/%s)',join.getJoinPSDER().getName(),join.getJoinPSDER().getName()) << '<br>\n'
            out << '连接实体：'
            if(join.getJoinType() == ''){
                out << String.format('[%s](module/%s/%s)',join.getJoinPSDER().getMajorPSDataEntity().getLogicName(),join.getJoinPSDER().getMajorPSDataEntity().getPSSystemModule().getCodeName(),join.getJoinPSDER().getMajorPSDataEntity().getCodeName())
            }else {
                out << String.format('[%s](module/%s/%s)',join.getJoinPSDER().getMajorPSDataEntity().getLogicName(),join.getJoinPSDER().getMajorPSDataEntity().getPSSystemModule().getCodeName(),join.getJoinPSDER().getMajorPSDataEntity().getCodeName())
            }
            out << '<br>\n'
            if(join.getPSDEDQGroupCondition())
                out << '连接条件：' << recursionConditions(join.getPSDEDQGroupCondition()) << '<br>\n'
            if(join.getChildPSDEDQJoins()){
                recursionJoins(index+1,join.getChildPSDEDQJoins())
            }
        }
    }
}
%>\
<%  if (item.getPSDEDQMain() && item.getPSDEDQMain().getPSDEDQGroupCondition()) {%>\

### 查询条件

<%recursionConditions(item.getPSDEDQMain().getPSDEDQGroupCondition())%>

<%}%>
<%  if (item.getPSDEDQMain() && item.getPSDEDQMain().getChildPSDEDQJoins()) {%>\

### 查询连接
<% recursionJoins(0,item.getPSDEDQMain().getChildPSDEDQJoins()) %>
<%}%>\
<%  if (item.getAllPSDEDataQueryCodes()) {%>\

### 数据库SQL语句
<%  item.getAllPSDEDataQueryCodes().each{ queryCode -> %>\

#### ${queryCode.getDBType()}

```sql
${queryCode.getQueryCode()}
<%if(queryCode.getPSDEDataQueryCodeConds()){%>\
WHERE <%queryCode.getPSDEDataQueryCodeConds().eachWithIndex{cond,index ->
if(index>0){
out << ' AND '
}
out << cond.getCustomCond()
}}%>
```
<%}}%>