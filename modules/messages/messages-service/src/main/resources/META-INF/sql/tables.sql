create table rec_messages (
	uuid_ VARCHAR(75) null,
	notificationEngineId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	subject VARCHAR(75) null,
	body VARCHAR(75) null,
	status BOOLEAN,
	tags VARCHAR(75) null
);