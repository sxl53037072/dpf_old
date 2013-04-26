-----------------------------------------------------
-- Export file for user CRMII                      --
-- Created by Administrator on 2013/4/26, 16:45:54 --
-----------------------------------------------------

spool 自定义查询对象.log

prompt
prompt Creating table COMPONENT
prompt ========================
prompt
create table crmii.COMPONENT
(
  comp_id     NUMBER(9) not null,
  comp_name   VARCHAR2(50) not null,
  comp_label  VARCHAR2(250) not null,
  ds_function VARCHAR2(250)
)
tablespace CRMII
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table crmii.COMPONENT
  is '元件';
comment on column crmii.COMPONENT.comp_id
  is '元件标识';
comment on column crmii.COMPONENT.comp_name
  is '元件名称';
comment on column crmii.COMPONENT.comp_label
  is '元件标签';
comment on column crmii.COMPONENT.ds_function
  is '数据源接口';
alter table crmii.COMPONENT
  add constraint PK_COMPONENT primary key (COMP_ID)
  using index 
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table GET_VALUE_CFG
prompt ============================
prompt
create table crmii.GET_VALUE_CFG
(
  get_value_cfg_id NUMBER(9) not null,
  get_value_type   VARCHAR2(5) not null,
  get_value_id     NUMBER(9) not null,
  remark           VARCHAR2(250)
)
tablespace CRMII
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table crmii.GET_VALUE_CFG
  is '取值配置';
comment on column crmii.GET_VALUE_CFG.get_value_cfg_id
  is '取值配置标识';
comment on column crmii.GET_VALUE_CFG.get_value_type
  is '取值方式';
comment on column crmii.GET_VALUE_CFG.get_value_id
  is '取值标识';
comment on column crmii.GET_VALUE_CFG.remark
  is '配置备注';
alter table crmii.GET_VALUE_CFG
  add constraint PK_GET_VALUE_CFG primary key (GET_VALUE_CFG_ID)
  using index 
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table crmii.GET_VALUE_CFG
  add constraint CKC_GET_VALUE_TYPE_GET_VALU
  check (GET_VALUE_TYPE in ('JS','JAVA','SQL','TABLE','TEXT'));

prompt
prompt Creating table GET_VALUE_CFG_FIELD
prompt ==================================
prompt
create table crmii.GET_VALUE_CFG_FIELD
(
  get_value_cfg_id NUMBER(9) not null,
  name             VARCHAR2(50) not null,
  label            VARCHAR2(50) not null,
  width            VARCHAR2(50),
  sort_id          NUMBER(8),
  config_script    VARCHAR2(4000)
)
tablespace CRMII
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table crmii.GET_VALUE_CFG_FIELD
  is '取值显示字段配置';
comment on column crmii.GET_VALUE_CFG_FIELD.get_value_cfg_id
  is '取值配置标识';
comment on column crmii.GET_VALUE_CFG_FIELD.name
  is '字段名称';
comment on column crmii.GET_VALUE_CFG_FIELD.label
  is '字段标签';
comment on column crmii.GET_VALUE_CFG_FIELD.width
  is '字段宽度';
comment on column crmii.GET_VALUE_CFG_FIELD.sort_id
  is '排序';
comment on column crmii.GET_VALUE_CFG_FIELD.config_script
  is '自定义脚本配置';
alter table crmii.GET_VALUE_CFG_FIELD
  add constraint PK_GET_VALUE_CFG_FIELD primary key (NAME, GET_VALUE_CFG_ID)
  using index 
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table GET_VALUE_SHOW_CFG
prompt =================================
prompt
create table crmii.GET_VALUE_SHOW_CFG
(
  get_value_cfg_id NUMBER(9) not null,
  title            VARCHAR2(250),
  is_page          VARCHAR2(3) default '0BT' not null,
  page_size        VARCHAR2(254),
  is_forcefit      VARCHAR2(3) default '0BT' not null,
  toolbar_menu_id  NUMBER(5),
  right_menu_id    NUMBER(5),
  hidden_columns   VARCHAR2(4000),
  dblclick         VARCHAR2(250),
  config_script    VARCHAR2(4000),
  import_js        VARCHAR2(4000),
  import_css       VARCHAR2(4000),
  column_cfg_type  VARCHAR2(7),
  default_search   VARCHAR2(3),
  filed_search     VARCHAR2(3),
  column_num       NUMBER(3) default 3
)
tablespace CRMII
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table crmii.GET_VALUE_SHOW_CFG
  is '显示配置';
comment on column crmii.GET_VALUE_SHOW_CFG.get_value_cfg_id
  is '取值配置标识';
comment on column crmii.GET_VALUE_SHOW_CFG.title
  is '标题';
comment on column crmii.GET_VALUE_SHOW_CFG.is_page
  is '是否分页显示';
comment on column crmii.GET_VALUE_SHOW_CFG.page_size
  is '分页显示记录数';
comment on column crmii.GET_VALUE_SHOW_CFG.is_forcefit
  is '是否强制自动分配显示列';
comment on column crmii.GET_VALUE_SHOW_CFG.toolbar_menu_id
  is '工具条菜单标识';
comment on column crmii.GET_VALUE_SHOW_CFG.right_menu_id
  is '右键菜单标识';
comment on column crmii.GET_VALUE_SHOW_CFG.hidden_columns
  is '隐藏字段';
comment on column crmii.GET_VALUE_SHOW_CFG.dblclick
  is '双击事件';
comment on column crmii.GET_VALUE_SHOW_CFG.config_script
  is '自定义脚本配置';
comment on column crmii.GET_VALUE_SHOW_CFG.import_js
  is '引入JS路径';
comment on column crmii.GET_VALUE_SHOW_CFG.import_css
  is '引入CSS路径';
comment on column crmii.GET_VALUE_SHOW_CFG.column_cfg_type
  is '字段配置模式';
comment on column crmii.GET_VALUE_SHOW_CFG.default_search
  is '默认是否显示查询';
comment on column crmii.GET_VALUE_SHOW_CFG.filed_search
  is '表头列查询';
comment on column crmii.GET_VALUE_SHOW_CFG.column_num
  is '每行列数';
alter table crmii.GET_VALUE_SHOW_CFG
  add constraint PK_GET_VALUE_SHOW_CFG primary key (GET_VALUE_CFG_ID)
  using index 
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table crmii.GET_VALUE_SHOW_CFG
  add constraint CKC_COLUMN_CFG_TYPE_GET_VALU
  check (COLUMN_CFG_TYPE is null or (COLUMN_CFG_TYPE in ('modify','replace')));
alter table crmii.GET_VALUE_SHOW_CFG
  add constraint CKC_DEFAULTSEARC_GETVALUESWCFG
  check (DEFAULT_SEARCH is null or (DEFAULT_SEARCH in ('0BT','0BF')));
alter table crmii.GET_VALUE_SHOW_CFG
  add constraint CKC_FILED_SEARCH_GETVALUESWCFG
  check (FILED_SEARCH is null or (FILED_SEARCH in ('0BT','0BF')));
alter table crmii.GET_VALUE_SHOW_CFG
  add constraint CKC_IS_FORCEFIT_GET_VALU
  check (IS_FORCEFIT in ('0BT','0BF'));
alter table crmii.GET_VALUE_SHOW_CFG
  add constraint CKC_IS_PAGE_GET_VALU
  check (IS_PAGE in ('0BT','0BF'));

prompt
prompt Creating table SQL_CFG
prompt ======================
prompt
create table crmii.SQL_CFG
(
  sql_id       NUMBER(9) not null,
  sql_text     LONG not null,
  sql_type     VARCHAR2(4) not null,
  cursor_index NUMBER(8),
  id_col       VARCHAR2(50),
  remark       VARCHAR2(250)
)
tablespace CRMII
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table crmii.SQL_CFG
  is 'SQL文本配置';
comment on column crmii.SQL_CFG.sql_id
  is 'SQL标识';
comment on column crmii.SQL_CFG.sql_text
  is 'SQL文本';
comment on column crmii.SQL_CFG.sql_type
  is 'SQL类型';
comment on column crmii.SQL_CFG.cursor_index
  is '游标参数';
comment on column crmii.SQL_CFG.id_col
  is '主键字段的列名';
comment on column crmii.SQL_CFG.remark
  is '注释';
alter table crmii.SQL_CFG
  add constraint PK_SQL_CFG primary key (SQL_ID)
  using index 
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table crmii.SQL_CFG
  add constraint CKC_SQL_TYPE_SQL_CFG
  check (SQL_TYPE in ('SQL','PROC'));

prompt
prompt Creating table SQL_PARAM_CFG
prompt ============================
prompt
create table crmii.SQL_PARAM_CFG
(
  sql_id      NUMBER(9) not null,
  param_name  VARCHAR2(50) not null,
  comp_id     NUMBER(9),
  param_type  VARCHAR2(3) default 'IN' not null,
  data_type   VARCHAR2(8) default 'STRING' not null,
  is_multiple VARCHAR2(3) default '0BF' not null,
  comp_ds     VARCHAR2(4000),
  comp_cfg    VARCHAR2(4000),
  param_label VARCHAR2(250),
  sort_id     NUMBER(8)
)
tablespace CRMII
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table crmii.SQL_PARAM_CFG
  is '查询参数表';
comment on column crmii.SQL_PARAM_CFG.sql_id
  is 'SQL标识';
comment on column crmii.SQL_PARAM_CFG.param_name
  is '参数名';
comment on column crmii.SQL_PARAM_CFG.comp_id
  is '元件标识';
comment on column crmii.SQL_PARAM_CFG.param_type
  is '参数类型';
comment on column crmii.SQL_PARAM_CFG.data_type
  is '数据类型';
comment on column crmii.SQL_PARAM_CFG.is_multiple
  is '是否多选';
comment on column crmii.SQL_PARAM_CFG.comp_ds
  is '元件数据源';
comment on column crmii.SQL_PARAM_CFG.comp_cfg
  is '元件配置';
comment on column crmii.SQL_PARAM_CFG.param_label
  is '参数标签';
comment on column crmii.SQL_PARAM_CFG.sort_id
  is '排序字段';
alter table crmii.SQL_PARAM_CFG
  add constraint PK_SQL_PARAM_CFG primary key (PARAM_NAME, SQL_ID)
  using index 
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table crmii.SQL_PARAM_CFG
  add constraint FK_SQL_PARA_REFERENCE_COMPONEN foreign key (COMP_ID)
  references crmii.COMPONENT (COMP_ID);
alter table crmii.SQL_PARAM_CFG
  add constraint CKC_DATA_TYPE_SQL_PARA
  check (DATA_TYPE in ('STRING','INTEGER','FLOAT'));
alter table crmii.SQL_PARAM_CFG
  add constraint CKC_IS_MULTIPLE_SQL_PARA
  check (IS_MULTIPLE in ('0BT','0BF'));
alter table crmii.SQL_PARAM_CFG
  add constraint CKC_PARAM_TYPE_SQL_PARA
  check (PARAM_TYPE in ('IN','OUT'));

prompt
prompt Creating table SYS_FUNC_MENU_GROUP
prompt ==================================
prompt
create table crmii.SYS_FUNC_MENU_GROUP
(
  sys_func_menu_group_id NUMBER(5) not null,
  remark                 VARCHAR2(250),
  import_js              VARCHAR2(4000)
)
tablespace CRMII
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table crmii.SYS_FUNC_MENU_GROUP
  is '系统菜单分组';
comment on column crmii.SYS_FUNC_MENU_GROUP.sys_func_menu_group_id
  is '功能菜单分组标识';
comment on column crmii.SYS_FUNC_MENU_GROUP.remark
  is '分组描述';
comment on column crmii.SYS_FUNC_MENU_GROUP.import_js
  is '引入JS路径';
alter table crmii.SYS_FUNC_MENU_GROUP
  add constraint PK_SYS_FUNC_MENU_GROUP primary key (SYS_FUNC_MENU_GROUP_ID)
  using index 
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SYS_FUNC_MENU
prompt ============================
prompt
create table crmii.SYS_FUNC_MENU
(
  func_menu_id       NUMBER(5) not null,
  func_menu_group_id NUMBER(5),
  menu_name          VARCHAR2(50) not null,
  menu_name_cn       VARCHAR2(250) not null,
  dhtml_id           VARCHAR2(50),
  event              VARCHAR2(250),
  width              VARCHAR2(15),
  selecttype         VARCHAR2(50),
  menutype           VARCHAR2(10) default 'rightMenu' not null,
  menu_property_id   VARCHAR2(30),
  other_attrs        VARCHAR2(250),
  remark             VARCHAR2(250),
  display_menu_item  VARCHAR2(250),
  hidden_menu_item   VARCHAR2(250)
)
tablespace CRMII
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table crmii.SYS_FUNC_MENU
  is '系统模块功能菜单';
comment on column crmii.SYS_FUNC_MENU.func_menu_id
  is '功能菜单标识';
comment on column crmii.SYS_FUNC_MENU.func_menu_group_id
  is '功能菜单分组标识';
comment on column crmii.SYS_FUNC_MENU.menu_name
  is '菜单名称';
comment on column crmii.SYS_FUNC_MENU.menu_name_cn
  is '菜单名称中文';
comment on column crmii.SYS_FUNC_MENU.dhtml_id
  is 'DHTML唯一标识';
comment on column crmii.SYS_FUNC_MENU.event
  is '菜单事件';
comment on column crmii.SYS_FUNC_MENU.width
  is '菜单宽度';
comment on column crmii.SYS_FUNC_MENU.selecttype
  is '选择子菜单的类型';
comment on column crmii.SYS_FUNC_MENU.menutype
  is '菜单类型';
comment on column crmii.SYS_FUNC_MENU.menu_property_id
  is '菜单对应归属值';
comment on column crmii.SYS_FUNC_MENU.other_attrs
  is '其他属性';
comment on column crmii.SYS_FUNC_MENU.remark
  is '备注';
comment on column crmii.SYS_FUNC_MENU.display_menu_item
  is '显示的菜单项';
comment on column crmii.SYS_FUNC_MENU.hidden_menu_item
  is '隐藏的菜单项';
alter table crmii.SYS_FUNC_MENU
  add constraint PK_SYS_FUNC_MENU primary key (FUNC_MENU_ID)
  using index 
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table crmii.SYS_FUNC_MENU
  add constraint FK_SYS_FUNC_REF_SYS_FUNC_GRP foreign key (FUNC_MENU_GROUP_ID)
  references crmii.SYS_FUNC_MENU_GROUP (SYS_FUNC_MENU_GROUP_ID);
alter table crmii.SYS_FUNC_MENU
  add constraint CKC_MENUTYPE_SYS_FUNC
  check (MENUTYPE in ('rightMenu','barMenu'));
create index crmii.IDX_SYS_FUNC_MENU_NAME on crmii.SYS_FUNC_MENU (MENU_NAME)
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SYS_FUNC_MENU_ITEM
prompt =================================
prompt
create table crmii.SYS_FUNC_MENU_ITEM
(
  func_menu_item_id  NUMBER(9) not null,
  func_menu_group_id NUMBER(5),
  parent_item_id     NUMBER(9),
  item_label         VARCHAR2(250),
  item_name          VARCHAR2(50),
  event              VARCHAR2(250),
  ico                VARCHAR2(250),
  other_attrs        VARCHAR2(250),
  is_line            VARCHAR2(3),
  sort_id            NUMBER(5),
  disabled           VARCHAR2(3) default '0BF',
  dynamic_load_event VARCHAR2(250),
  remark             VARCHAR2(250),
  state              VARCHAR2(3) default '0SA' not null,
  display            NUMBER(1) default 1
)
tablespace CRMII
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table crmii.SYS_FUNC_MENU_ITEM
  is '功能菜单子项';
comment on column crmii.SYS_FUNC_MENU_ITEM.func_menu_item_id
  is '菜单子项标识';
comment on column crmii.SYS_FUNC_MENU_ITEM.func_menu_group_id
  is '功能菜单分组标识';
comment on column crmii.SYS_FUNC_MENU_ITEM.parent_item_id
  is '父菜单子项标识';
comment on column crmii.SYS_FUNC_MENU_ITEM.item_label
  is '菜单显示名称';
comment on column crmii.SYS_FUNC_MENU_ITEM.item_name
  is '菜单名';
comment on column crmii.SYS_FUNC_MENU_ITEM.event
  is '菜单事件';
comment on column crmii.SYS_FUNC_MENU_ITEM.ico
  is '菜单图标';
comment on column crmii.SYS_FUNC_MENU_ITEM.other_attrs
  is '菜单其他属性';
comment on column crmii.SYS_FUNC_MENU_ITEM.is_line
  is '是否菜单分割线';
comment on column crmii.SYS_FUNC_MENU_ITEM.sort_id
  is '同级排序标识';
comment on column crmii.SYS_FUNC_MENU_ITEM.disabled
  is '是否有效';
comment on column crmii.SYS_FUNC_MENU_ITEM.dynamic_load_event
  is '动态载入方法';
comment on column crmii.SYS_FUNC_MENU_ITEM.remark
  is '备注';
comment on column crmii.SYS_FUNC_MENU_ITEM.state
  is '状态';
comment on column crmii.SYS_FUNC_MENU_ITEM.display
  is '是否显示';
alter table crmii.SYS_FUNC_MENU_ITEM
  add constraint PK_SYS_FUNC_MENU_ITEM primary key (FUNC_MENU_ITEM_ID)
  using index 
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table crmii.SYS_FUNC_MENU_ITEM
  add constraint FK_FUNC_ITEM_REF_FUNC_GROUP foreign key (FUNC_MENU_GROUP_ID)
  references crmii.SYS_FUNC_MENU_GROUP (SYS_FUNC_MENU_GROUP_ID);
alter table crmii.SYS_FUNC_MENU_ITEM
  add constraint FK_SYS_FUNC_REFERENCE_SYS_FUNC foreign key (PARENT_ITEM_ID)
  references crmii.SYS_FUNC_MENU_ITEM (FUNC_MENU_ITEM_ID);
alter table crmii.SYS_FUNC_MENU_ITEM
  add constraint CKC_DISABLED_SYS_FUNC
  check (DISABLED is null or (DISABLED in ('0BT','0BF')));
alter table crmii.SYS_FUNC_MENU_ITEM
  add constraint CKC_DISPLAY_SYS_FUNC
  check (DISPLAY is null or (DISPLAY in (0,1)));
alter table crmii.SYS_FUNC_MENU_ITEM
  add constraint CKC_IS_LINE_SYS_FUNC
  check (IS_LINE is null or (IS_LINE in ('0BT','0BF')));
alter table crmii.SYS_FUNC_MENU_ITEM
  add constraint CKC_STATE_SYS_MENU_ITEM
  check (STATE in ('0SA','0SX'));
create index crmii.IDX_ITEM_FUNC_MENU_GROUP_ID on crmii.SYS_FUNC_MENU_ITEM (FUNC_MENU_GROUP_ID)
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index crmii.IDX_PARENT_ITEM_ID on crmii.SYS_FUNC_MENU_ITEM (PARENT_ITEM_ID)
  tablespace CRMII
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );


spool off
