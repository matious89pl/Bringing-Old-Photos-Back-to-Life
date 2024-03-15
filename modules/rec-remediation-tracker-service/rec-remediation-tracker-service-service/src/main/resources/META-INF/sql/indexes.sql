create index IX_3389CE on REC_RemediationTracker (status[$COLUMN_LENGTH:75$]);
create index IX_D51D4ADC on REC_RemediationTracker (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_3FF2D85E on REC_RemediationTracker (uuid_[$COLUMN_LENGTH:75$], groupId);