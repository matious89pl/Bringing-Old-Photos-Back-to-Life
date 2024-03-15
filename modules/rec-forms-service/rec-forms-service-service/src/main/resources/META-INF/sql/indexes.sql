create index IX_3BD49B38 on AMAF_MaintenanceActivityForms (formInstanceRecordVersionId);
create index IX_D8ABB69 on AMAF_MaintenanceActivityForms (groupId);
create index IX_82CF6215 on AMAF_MaintenanceActivityForms (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8F8369D7 on AMAF_MaintenanceActivityForms (uuid_[$COLUMN_LENGTH:75$], groupId);