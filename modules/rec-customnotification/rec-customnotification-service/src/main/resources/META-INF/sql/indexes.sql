create index IX_E399F333 on REC_NotificationRpa (groupId, plidPage, createDate);
create index IX_AF7F2396 on REC_NotificationRpa (groupId, plidPage, notificationBody[$COLUMN_LENGTH:500$]);
create index IX_EF34D488 on REC_NotificationRpa (groupId, plidPage, notificationTitle[$COLUMN_LENGTH:250$]);
create index IX_78E487A5 on REC_NotificationRpa (groupId, plidPage, targetName[$COLUMN_LENGTH:75$]);
create index IX_3D3978C3 on REC_NotificationRpa (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C93F5405 on REC_NotificationRpa (uuid_[$COLUMN_LENGTH:75$], groupId);