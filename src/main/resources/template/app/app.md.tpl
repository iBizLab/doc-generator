# ${item.getName()}(${item.getCodeName()})  <!-- {docsify-ignore-all} -->

${ctx.ignoreNullString(item.getMemo())}
<%
def indexView = item.getDefaultPSAppIndexView()
def menu
if(indexView){
    menu = indexView.getPSControl("appmenu",true)
}
if(menu){
//递归菜单方法
def  recursionMenuItems
recursionMenuItems = { items ->
  if(items) {
    items.each { item ->
      if(item instanceof net.ibizsys.model.control.menu.PSAppMenuItemImpl){
        if(item.getPSAppMenuItems()) {%>\
    <el-sub-menu index="${item.getName()}">
      <template #title>${item.getCaption()}</template>
<%recursionMenuItems(item.getPSAppMenuItems())%>\
    </el-sub-menu>
<%        } else {%>\
    <el-menu-item index="${item.getName()}"<%=(item.getPSAppFunc())?"":" disabled"%><%if(item.getPSAppFunc() && item.getPSAppFunc().getPSAppView()){%> @click="itemClick('#/app/view/${item.getPSAppFunc().getPSAppView().getCodeName()}')"<%}%>>\
<%if(item.getPSSysImage() && item.getPSSysImage().getCssClass()){%>\
<i class="${item.getPSSysImage().getCssClass()}"></i>\
<%}%>\
${item.getCaption()}</el-menu-item>
<%        }%>\
<%}}}}%>\
## 菜单

<el-row>
  <el-menu :ellipsis="false" class="el-menu-demo" mode="horizontal" @select="handleSelect">
<%recursionMenuItems(menu.getPSAppMenuItems())%>\
  </el-menu>
</el-row>
<%}%>

## 视图清单

<% if (item.getAllPSAppViews()) {
  List sort = item.getAllPSAppViews().sort {it.getCodeName()}
  sort.each{ view -> %>\
  * [<%=(view.getCaption())?view.getCaption():view.getTitle()%>(${view.getCodeName()})](app/view/${view.getCodeName()})
<% }} %>

<script>
 const { createApp } = Vue
  createApp({
    data() {
      return {
        message: '!'
      }
    },
    methods: {
      itemClick(url) {
        location.href = url
      }
    }
  }).use(ElementPlus).mount('#app')
</script>