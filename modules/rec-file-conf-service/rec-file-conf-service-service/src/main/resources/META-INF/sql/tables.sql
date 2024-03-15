create table REC_File_Conf (
	uuid_ VARCHAR(75) null,
	docConfigId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	docConfName VARCHAR(75) null,
	docFileType VARCHAR(75) null,
	docConfigJSON STRING null
);