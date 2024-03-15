create table confidential_messages (
	uuid_ VARCHAR(75) null,
	messageId LONG not null primary key,
	companyId LONG,
	createDate DATE null
);