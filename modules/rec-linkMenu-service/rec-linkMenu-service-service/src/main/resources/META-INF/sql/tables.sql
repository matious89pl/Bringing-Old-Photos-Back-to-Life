create table LinkMenu_LinkMenu (
	uuid_ VARCHAR(75) null,
	linkId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	linkName VARCHAR(75) null,
	link VARCHAR(75) null,
	linkPosition INTEGER,
	iconName VARCHAR(75) null
);