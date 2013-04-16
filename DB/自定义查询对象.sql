-----------------------------------------------------
-- Export file for user CRMII                      --
-- Created by Administrator on 2013/4/16, 16:39:03 --
-----------------------------------------------------

spool �Զ����ѯ����.log

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
  is 'Ԫ��';
comment on column crmii.COMPONENT.comp_id
  is 'Ԫ����ʶ';
comment on column crmii.COMPONENT.comp_name
  is 'Ԫ������';
comment on column crmii.COMPONENT.comp_label
  is 'Ԫ����ǩ';
comment on column crmii.COMPONENT.ds_function
  is '����Դ�ӿ�';
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
  is 'ȡֵ����';
comment on column crmii.GET_VALUE_CFG.get_value_cfg_id
  is 'ȡֵ���ñ�ʶ';
comment on column crmii.GET_VALUE_CFG.get_value_type
  is 'ȡֵ��ʽ';
comment on column crmii.GET_VALUE_CFG.get_value_id
  is 'ȡֵ��ʶ';
comment on column crmii.GET_VALUE_CFG.remark
  is '���ñ�ע';
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
  is 'ȡֵ��ʾ�ֶ�����';
comment on column crmii.GET_VALUE_CFG_FIELD.get_value_cfg_id
  is 'ȡֵ���ñ�ʶ';
comment on column crmii.GET_VALUE_CFG_FIELD.name
  is '�ֶ�����';
comment on column crmii.GET_VALUE_CFG_FIELD.label
  is '�ֶα�ǩ';
comment on column crmii.GET_VALUE_CFG_FIELD.width
  is '�ֶο��';
comment on column crmii.GET_VALUE_CFG_FIELD.sort_id
  is '����';
comment on column crmii.GET_VALUE_CFG_FIELD.config_script
  is '�Զ���ű�����';
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
  filed_search     VARCHAR2(3)
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
  is '��ʾ����';
comment on column crmii.GET_VALUE_SHOW_CFG.get_value_cfg_id
  is 'ȡֵ���ñ�ʶ';
comment on column crmii.GET_VALUE_SHOW_CFG.title
  is '����';
comment on column crmii.GET_VALUE_SHOW_CFG.is_page
  is '�Ƿ��ҳ��ʾ';
comment on column crmii.GET_VALUE_SHOW_CFG.page_size
  is '��ҳ��ʾ��¼��';
comment on column crmii.GET_VALUE_SHOW_CFG.is_forcefit
  is '�Ƿ�ǿ���Զ�������ʾ��';
comment on column crmii.GET_VALUE_SHOW_CFG.toolbar_menu_id
  is '�������˵���ʶ';
comment on column crmii.GET_VALUE_SHOW_CFG.right_menu_id
  is '�Ҽ��˵���ʶ';
comment on column crmii.GET_VALUE_SHOW_CFG.hidden_columns
  is '�����ֶ�';
comment on column crmii.GET_VALUE_SHOW_CFG.dblclick
  is '˫���¼�';
comment on column crmii.GET_VALUE_SHOW_CFG.config_script
  is '�Զ���ű�����';
comment on column crmii.GET_VALUE_SHOW_CFG.import_js
  is '����JS·��';
comment on column crmii.GET_VALUE_SHOW_CFG.import_css
  is '����CSS·��';
comment on column crmii.GET_VALUE_SHOW_CFG.column_cfg_type
  is '�ֶ�����ģʽ';
comment on column crmii.GET_VALUE_SHOW_CFG.default_search
  is 'Ĭ���Ƿ���ʾ��ѯ';
comment on column crmii.GET_VALUE_SHOW_CFG.filed_search
  is '��ͷ�в�ѯ';
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
  is 'SQL�ı�����';
comment on column crmii.SQL_CFG.sql_id
  is 'SQL��ʶ';
comment on column crmii.SQL_CFG.sql_text
  is 'SQL�ı�';
comment on column crmii.SQL_CFG.sql_type
  is 'SQL����';
comment on column crmii.SQL_CFG.cursor_index
  is '�α����';
comment on column crmii.SQL_CFG.id_col
  is '�����ֶε�����';
comment on column crmii.SQL_CFG.remark
  is 'ע��';
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
  is '��ѯ������';
comment on column crmii.SQL_PARAM_CFG.sql_id
  is 'SQL��ʶ';
comment on column crmii.SQL_PARAM_CFG.param_name
  is '������';
comment on column crmii.SQL_PARAM_CFG.comp_id
  is 'Ԫ����ʶ';
comment on column crmii.SQL_PARAM_CFG.param_type
  is '��������';
comment on column crmii.SQL_PARAM_CFG.data_type
  is '��������';
comment on column crmii.SQL_PARAM_CFG.is_multiple
  is '�Ƿ��ѡ';
comment on column crmii.SQL_PARAM_CFG.comp_ds
  is 'Ԫ������Դ';
comment on column crmii.SQL_PARAM_CFG.comp_cfg
  is 'Ԫ������';
comment on column crmii.SQL_PARAM_CFG.param_label
  is '������ǩ';
comment on column crmii.SQL_PARAM_CFG.sort_id
  is '�����ֶ�';
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


spool off
