create table RFI_RfiLogs (
	uuid_ VARCHAR(75) null,
	rfilogId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	reqId LONG,
	specificParty VARCHAR(75) null,
	orgSiteId LONG,
	title VARCHAR(500) null,
	dueDate VARCHAR(75) null,
	status VARCHAR(75) null,
	reqDesc VARCHAR(500) null,
	rationale VARCHAR(500) null,
	dueDateFormated DATE null,
	calendarBookingId LONG
);