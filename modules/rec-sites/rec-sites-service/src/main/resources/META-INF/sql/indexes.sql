create index IX_C8041FF1 on RECSITES_Recsites (field2);
create index IX_BF36BBB9 on RECSITES_Recsites (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_247CDC7B on RECSITES_Recsites (uuid_[$COLUMN_LENGTH:75$], groupId);