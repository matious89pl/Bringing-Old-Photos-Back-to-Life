create table validation_log (
	validationLogId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	createDate DATE null,
	fileName VARCHAR(75) null,
	uploadedBy VARCHAR(75) null,
	uploadedFrom VARCHAR(75) null,
	folderId LONG,
	logReason VARCHAR(75) null
);