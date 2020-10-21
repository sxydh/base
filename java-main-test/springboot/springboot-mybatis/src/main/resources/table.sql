DROP TABLE "BHE"."T_TEST";
CREATE TABLE "BHE"."T_TEST" (
  "ID" NUMBER NOT NULL,
  "NAME" VARCHAR2(255 BYTE),
  "VALUE" NUMBER DEFAULT 0,
  "CDATE" DATE DEFAULT sysdate
)
TABLESPACE "BHE";
ALTER TABLE "BHE"."T_TEST" ADD CONSTRAINT pk_test PRIMARY KEY (ID);

DROP TABLE "BHE"."T_LOGBACK";
CREATE TABLE "BHE"."T_LOGBACK" (
  "LOGDATE" VARCHAR2(200 BYTE),
  "PRIORITY" VARCHAR2(100 BYTE),
  "POS" VARCHAR2(1000 BYTE),
  "THROWABLE" CLOB,
  "CALLER" VARCHAR2(3000 BYTE),
  "MESSAGE" CLOB,
  "THREAD" VARCHAR2(200 BYTE)
)
TABLESPACE "BHE";
-- Add comments to the columns 
comment on column BHE.T_LOGBACK.logdate
  is '时间';
comment on column BHE.T_LOGBACK.priority
  is '级别';
comment on column BHE.T_LOGBACK.pos
  is '路径';
comment on column BHE.T_LOGBACK.throwable
  is '异常stack trace';
comment on column BHE.T_LOGBACK.caller
  is '调用stack trace';
comment on column BHE.T_LOGBACK.message
  is '内容message';
comment on column BHE.T_LOGBACK.thread
  is '线程';