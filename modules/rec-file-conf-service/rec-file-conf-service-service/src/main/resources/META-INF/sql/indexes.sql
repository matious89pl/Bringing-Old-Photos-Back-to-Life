create index IX_D1E76F99 on REC_File_Conf (docConfName[$COLUMN_LENGTH:75$]);
create index IX_3C1138E0 on REC_File_Conf (docFileType[$COLUMN_LENGTH:75$]);
create index IX_C946B332 on REC_File_Conf (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_22E65E34 on REC_File_Conf (uuid_[$COLUMN_LENGTH:75$], groupId);