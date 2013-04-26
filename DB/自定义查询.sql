--基本配置
SELECT T.*,T.ROWID FROM GET_VALUE_CFG T;
SELECT T.*,T.ROWID FROM SQL_CFG T;
--配置字段及显示
SELECT T.*,T.ROWID FROM GET_VALUE_SHOW_CFG T where t.get_value_cfg_id = 7;
SELECT T.*,T.ROWID FROM GET_VALUE_CFG_FIELD T where t.get_value_cfg_id = 7;
--搜索栏
SELECT T.*,T.ROWID FROM COMPONENT T;
SELECT T.*,T.ROWID FROM SQL_PARAM_CFG T where t.sql_id = 13 order by sort_id;
--工具栏
SELECT T.*,T.ROWID FROM SYS_FUNC_MENU_GROUP T;
SELECT T.*,T.ROWID FROM SYS_FUNC_MENU T where t.func_menu_group_id = 2015;
SELECT T.*,T.ROWID FROM SYS_FUNC_MENU_ITEM T WHERE T.FUNC_MENU_GROUP_ID = 2015 ORDER BY T.SORT_ID;



SELECT T.*,T.ROWID FROM GET_VALUE_CFG_FIELD T WHERE T.CONFIG_SCRIPT IS NOT NULL;
   
SELECT upper(NAME) name,LABEL,WIDTH FROM GET_VALUE_CFG_FIELD WHERE GET_VALUE_CFG_ID = ? ORDER BY SORT_ID;

--GET_VALUE_SHOW_
SELECT T.GET_VALUE_CFG_ID,
       T.TITLE,
       T.IS_PAGE,
       T.PAGE_SIZE,
       T.IS_FORCEFIT,
       T.TOOLBAR_MENU_ID,
       T.RIGHT_MENU_ID,
       T.HIDDEN_COLUMNS,
       T.DBLCLICK,
       T.CONFIG_SCRIPT,
       T.IMPORT_JS,
       T.IMPORT_CSS,
       T.COLUMN_CFG_TYPE,
       T.DEFAULT_SEARCH,
       T.FILED_SEARCH,
       T.rowid       
  FROM GET_VALUE_SHOW_CFG T
 WHERE T.GET_VALUE_CFG_ID = 1;
 
 CRMII.TEST_PRO_SELECT_PRODUCTSELL ;
--获取搜索条件配置
SELECT T.SQL_ID,
       T.PARAM_NAME,
       T.COMP_ID,
       T.PARAM_TYPE,
       T.DATA_TYPE,
       T.IS_MULTIPLE,
       T.COMP_DS,
       T.COMP_CFG,
       T.PARAM_LABEL,
       T.SORT_ID,
       A.COMP_NAME,
       A.COMP_LABEL,
       A.DS_FUNCTION
  FROM SQL_PARAM_CFG T, COMPONENT A
 WHERE T.COMP_ID = A.COMP_ID
 and SQL_ID = (SELECT GET_VALUE_ID FROM GET_VALUE_CFG WHERE GET_VALUE_CFG_ID = ?);
 AND ((0=nvl(length(?),0) and 1=1) or (0<nvl(length(?),0) and sql_id = ?));

--获取工具栏按钮
SELECT T.Func_Menu_Item_Id,
       T.FUNC_MENU_GROUP_ID,
       T.PARENT_ITEM_ID,
       T.ITEM_LABEL,
       T.ITEM_NAME,
       T.EVENT,
       T.ICO,
       T.OTHER_ATTRS,
       T.IS_LINE,
       T.DISABLED,
       T.DYNAMIC_LOAD_EVENT,
       T.REMARK,
       T.STATE,
       T.DISPLAY,
       A.IMPORT_JS
  FROM SYS_FUNC_MENU_ITEM T, SYS_FUNC_MENU_GROUP A
 WHERE T.FUNC_MENU_GROUP_ID = A.SYS_FUNC_MENU_GROUP_ID
 AND A.SYS_FUNC_MENU_GROUP_ID = 2015
 AND T.STATE = '0SA'
 ORDER BY T.SORT_ID
 for update;
 
--get sqlInfo
SELECT B.SQL_ID          as "sqlId",
       B.SQL_TEXT        as "sqlText",
       B.SQL_TYPE        as "sqlType",
       B.CURSOR_INDEX    as "cursorIndex",
       B.REMARK          as "remark",
       c.toolbar_menu_id as "toolbarMenuId"
  FROM GET_VALUE_CFG A, SQL_CFG B, GET_VALUE_SHOW_CFG C
 WHERE A.GET_VALUE_ID = B.SQL_ID
   and a.get_value_id = c.get_value_cfg_id(+)
 order by sql_id;
 
-- GET_VALUE_CFG
INSERT INTO GET_VALUE_CFG(GET_VALUE_CFG_ID,GET_VALUE_TYPE,GET_VALUE_ID,REMARK)VALUES(?,?,?,?);

UPDATE GET_VALUE_CFG
SET GET_VALUE_CFG_ID = ?,GET_VALUE_TYPE = ?,GET_VALUE_ID = ?,REMARK = ?
WHERE GET_VALUE_CFG_ID = ?;
--SQL_CFG
INSERT INTO SQL_CFG(SQL_ID,SQL_TEXT,SQL_TYPE,CURSOR_INDEX,ID_COL,REMARK)VALUES(?,?,?,?,?,?);
 
UPDATE SQL_CFG
SET SQL_ID = ?,SQL_TEXT = ?,SQL_TYPE = ?,CURSOR_INDEX = ?,ID_COL = ?,REMARK = ?
WHERE SQL_ID = ?;
 
--获取工具栏配置
SELECT A.SYS_FUNC_MENU_GROUP_ID AS "groupId",
       C.ITEM_LABEL AS "itemLabel",
       C.EVENT AS "event",
       C.Ico as "ico",
       decode(c.is_line, '0BT', '是', '否') as "isLineName",
       c.is_line as "isLine",
       c.sort_id as "sortId",
       c.remark as "remark",
       a.import_js as "importJs",
       b.menu_name_cn as "menuNameCn"
  FROM SYS_FUNC_MENU_GROUP A, SYS_FUNC_MENU B, SYS_FUNC_MENU_ITEM C
 WHERE A.SYS_FUNC_MENU_GROUP_ID = B.FUNC_MENU_GROUP_ID
   AND B.FUNC_MENU_GROUP_ID = C.FUNC_MENU_GROUP_ID
   AND A.SYS_FUNC_MENU_GROUP_ID = 2015

