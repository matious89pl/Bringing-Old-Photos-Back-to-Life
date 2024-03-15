create table CPROPOSAL_recFormArticle (
	formId LONG not null primary key,
	articleId LONG,
	articleStatus VARCHAR(75) null,
	articleReleaseSchedule VARCHAR(75) null,
	alternativeFormIds VARCHAR(75) null,
	articleImplementationDate DATE null,
	articleChangePath VARCHAR(75) null,
	articleResponsibleCommittee VARCHAR(75) null,
	articleImpactedParties VARCHAR(75) null,
	articleChangeCategory VARCHAR(75) null,
	articleImpacts VARCHAR(75) null
);