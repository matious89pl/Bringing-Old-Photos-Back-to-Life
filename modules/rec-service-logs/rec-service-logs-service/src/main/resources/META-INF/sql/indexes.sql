create index IX_12E86879 on ACTIVITY_RecLog (classPK);
create unique index IX_6B552C20 on ACTIVITY_RecLog (uuid_[$COLUMN_LENGTH:75$], groupId);