<#--
Web content templates are used to lay out the fields defined in a web
content structure.

Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->

<h1>CATEGORY 3 CHANGE LONG TEMPLATE</h1>
<label>Change Proposal ID :</label>
${ChangeIdCatetory3.getData()} <br>
<label >Forname :</label>
${FornameCategory3.getData()} <br>
<label >Surname :</label>
${SurnameCategory3.getData()} <br>
<label >Email Address :</label>
${EmailAddressCategory3.getData()} <br>
<label >Telephone Number :</label>
${TelephoneNumberCategory3.getData()}<br>
<label >Category 3 Document :</label>
${Category3DocumentCategory3.getData()}<br>
<label >Description And Reason For Change :</label>
${DescriptionAndReasonForChangeCategory3.getData()}<br>
<label >Organisation :</label>
${OrganisationCategory3.getData()}<br>
<label >Status :</label>
${StatusCategory3.getData()} <br>
<label>Proposed Implementation Date :</label>
<#assign ProposedImplementationDateCategory3_Data = getterUtil.getString(ProposedImplementationDateCategory3.getData())>

<#if validator.isNotNull(ProposedImplementationDateCategory3_Data)>
	<#assign ProposedImplementationDateCategory3_DateObj = dateUtil.parseDate("yyyy-MM-dd", ProposedImplementationDateCategory3_Data, locale)>

	${dateUtil.getDate(ProposedImplementationDateCategory3_DateObj, "dd MMM yyyy - HH:mm:ss", locale)}<br>
</#if>
    <label>Attach Supporting Document :</label>
<a href="${AttachSupportingDocumentCategory3.getData()}"> <br>
	${languageUtil.format(locale, "download-x", "Attach Supporting Document", false)}
</a>
<label >Consultation Responses :</label>
<a href="${ConsultationResponsesCategory3.getData()}"> <br>
	${languageUtil.format(locale, "download-x", "Consultation Responses", false)}
</a>
<label>Associated Category 1 or 2 Changes :</label>
${AssociatedCategory1or2ChangesCategory3.getData()} <br>