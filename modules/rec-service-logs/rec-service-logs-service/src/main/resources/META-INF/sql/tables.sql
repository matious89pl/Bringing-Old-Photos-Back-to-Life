create table ACTIVITY_RecLog (
	uuid_ VARCHAR(75) null,
	activityLogId LONG not null primary key,
	groupId LONG,
	createDate DATE null,
	logMessage VARCHAR(75) null,
	className VARCHAR(75) null,
	classPK LONG,
	comments VARCHAR(75) null,
	type_ VARCHAR(75) null
);