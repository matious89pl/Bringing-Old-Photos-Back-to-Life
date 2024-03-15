create index IX_7A0AB067 on rec_messages (companyId);
create index IX_A93AF808 on rec_messages (name[$COLUMN_LENGTH:75$], companyId);
create index IX_FC7A60D5 on rec_messages (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_115F1897 on rec_messages (uuid_[$COLUMN_LENGTH:75$], groupId);