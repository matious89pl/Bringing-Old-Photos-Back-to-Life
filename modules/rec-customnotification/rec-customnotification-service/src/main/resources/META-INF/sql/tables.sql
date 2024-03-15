create table REC_NotificationRpa (
	uuid_ VARCHAR(75) null,
	customNotificationId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	plidPage LONG,
	targetName VARCHAR(75) null,
	notificationTitle VARCHAR(250) null,
	notificationBody VARCHAR(500) null,
	url VARCHAR(75) null,
	deliveryMethod VARCHAR(75) null
);