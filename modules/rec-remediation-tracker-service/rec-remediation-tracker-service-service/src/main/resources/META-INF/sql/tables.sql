create table REC_RemediationTracker (
	uuid_ VARCHAR(75) null,
	remediationTrackerId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	orgSiteId LONG,
	specificParty VARCHAR(75) null,
	category VARCHAR(75) null,
	description VARCHAR(500) null,
	dueDate VARCHAR(75) null,
	dueDateFormated DATE null,
	status VARCHAR(75) null,
	calendarBookingId LONG
);