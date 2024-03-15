create index IX_223E752F on RECsr_supportR (groupId, createDate);
create index IX_493BD2DF on RECsr_supportR (groupId, dueDate, createDate);
create index IX_A427F49F on RECsr_supportR (groupId, link[$COLUMN_LENGTH:1000$]);
create index IX_417D45D7 on RECsr_supportR (groupId, status[$COLUMN_LENGTH:75$]);
create index IX_885E59F7 on RECsr_supportR (groupId, title[$COLUMN_LENGTH:250$]);
create index IX_A37040C4 on RECsr_supportR (groupId, type_[$COLUMN_LENGTH:75$]);
create index IX_8E9726BF on RECsr_supportR (status[$COLUMN_LENGTH:75$]);
create index IX_8BFA2E8D on RECsr_supportR (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_366F44F on RECsr_supportR (uuid_[$COLUMN_LENGTH:75$], groupId);