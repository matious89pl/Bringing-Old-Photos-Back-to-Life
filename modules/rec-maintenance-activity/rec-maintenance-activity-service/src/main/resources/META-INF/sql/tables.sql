create table AMA_MaintenanceActivity (
	uuid_ VARCHAR(75) null,
	maintenanceactivityId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	activityTitle VARCHAR(500) null,
	dueDate VARCHAR(75) null,
	status VARCHAR(75) null,
	orgSiteId LONG,
	specificParty VARCHAR(75) null,
	dueDateFormated DATE null,
	calendarBookingId LONG
);