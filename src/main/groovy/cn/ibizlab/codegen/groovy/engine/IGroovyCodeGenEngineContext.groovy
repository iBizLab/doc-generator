package cn.ibizlab.codegen.groovy.engine

import net.ibizsys.codegen.core.engine.ICodeGenEngineContext
import net.ibizsys.model.IPSObject

interface IGroovyCodeGenEngineContext extends ICodeGenEngineContext {

    /**
     *
     * @param iPSObject
     * @param strTemplateId
     * @param parent
     * @return
     * @throws Exception
     */
    String outputWithParent(IPSObject iPSObject, String strTemplateId, IPSObject parent) throws Exception;

    /**
     * 忽略null
     * @param str
     * @return
     */
    public String ignoreNullString(String str)

    /**
     * 忽略null
     * @param str
     * @return
     */
    public String ignoreNullString(String str,String strDefault)

    /**
     * md表格中字符包含 |、换行 时转义处理
     * @param str
     * @return
     */
    public String tableString(String str)

    /**
     * 代码表
     *
     * @param codelist
     * @param value
     * @return
     */
    public String text(String codelist, Object value)

}
