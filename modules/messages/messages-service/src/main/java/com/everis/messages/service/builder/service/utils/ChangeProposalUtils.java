package com.everis.messages.service.builder.service.utils;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.journal.constants.JournalArticleConstants;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;

//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = ChangeProposalUtils.class)
public class ChangeProposalUtils {
	
	private final Log logger = LogFactoryUtil.getLog(ChangeProposalUtils.class);
	
	public static final String		TEMPLATE_XML							= "/templates/change_proposal_template.xml";
	
	private static final String		STRUCTURE_KEY							= "Change Proposal Page";
	private static final String		TEMPLATE_KEY							= "Change Proposal Page Display Template";
	
	private static final String     CHANGE_PROPOSAL_REFERENCE				= "@CHANGE_PROPOSAL_REFERENCE";
	private static final String     CHANGE_PROPOSAL_TITLE					= "@CHANGE_PROPOSAL_TITLE";
	private static final String     CHANGE_PROPOSAL_STATUS					= "@CHANGE_PROPOSAL_STATUS";
	private static final String     CHANGE_PROPOSAL_PROPOSER_ID				= "@CHANGE_PROPOSAL_PROPOSER_ID";
	private static final String     CHANGE_PROPOSAL_LINK_TO_CP_PAGE			= "@CHANGE_PROPOSAL_LINK_TO_CP_PAGE";
	private static final String     CHANGE_PROPOSAL_PROBLEM_STATEMENT		= "@CHANGE_PROPOSAL_PROBLEM_STATEMENT";
	private static final String     CHANGE_PROPOSAL_SOLUTION_REQUIREMENTS	= "@CHANGE_PROPOSAL_SOLUTION_REQUIREMENTS";
	private static final String     CHANGE_PROPOSAL_CHANGE_PATH				= "@CHANGE_PROPOSAL_CHANGE_PATH";
	private static final String     CHANGE_PROPOSAL_RESPONSIBLE_COMMITTEE	= "@CHANGE_PROPOSAL_RESPONSIBLE_COMMITTEE";
	private static final String     CHANGE_PROPOSAL_PRIORITY_STATUS			= "@CHANGE_PROPOSAL_PRIORITY_STATUS";
	private static final String     CHANGE_PROPOSAL_URGENT_CHANGE			= "@CHANGE_PROPOSAL_URGENT_CHANGE";
	private static final String     CHANGE_PROPOSAL_DOCUMENT_TITLE			= "@CHANGE_PROPOSAL_DOCUMENT_TITLE";
	private static final String     CHANGE_PROPOSAL_ATTACH_DOCUMENT			= "@CHANGE_PROPOSAL_ATTACH_DOCUMENT";
	private static final String     CHANGE_PROPOSAL_DEADLINE				= "@CHANGE_PROPOSAL_DEADLINE";
	
	private static String standardContent;
	
	public void createCProposal(Map<String, Object> parameters) {
		
		File file = FileUtil.createTempFile();

        try (OutputStream outputStream = new FileOutputStream(file)) {
            InputStream inStream = new java.io.BufferedInputStream(
                    this.getClass().getClassLoader().getResourceAsStream(TEMPLATE_XML));

//            IOUtils.copy(inStream, outputStream);

//            standardContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException ioExce) {
            logger.error(
                    "Unable to get Resource file STANDARD_CONTENT_TEMPLATE " + ioExce.getLocalizedMessage(),
                    ioExce);
        }

        FileUtil.delete(file);
        
        /* create Article */

        User user;
		try {
			user = UserLocalServiceUtil.getUser(44774);
			parameters.put("user", user); //Harcoded
	        parameters.put("groupId", 20121); //Harcoded
	        parameters.put("title", "Test new JA"); //Harcoded
	        parameters.put("folderId", 0); //Harcoded
	        parameters.put("entryId", 45908); //Harcoded
	        
//	      parameters.get("CPRef");
//			parameters.get("CPTitle");
//			parameters.get("CPStatus");
//			parameters.get("CPProposerId");
//			parameters.get("CPLinkToCP");
//			parameters.get("CPProblemStatement");
//			parameters.get("CPSolutionReq");
//			parameters.get("CPChangePath");
//			parameters.get("CPResponsibleComittee");
//			parameters.get("CPPriorityStatus");
//			parameters.get("CPUrgentChange");
//			parameters.get("CPDocumentTitle");
//			parameters.get("CPDeadline");
	        
	        parameters.put("standardContent", standardContent);

	        if (logger.isDebugEnabled()) {
	            parameters.forEach((k, v) -> logger.debug(String.format("%-20s= %s", k, v)));
	        }

	        JournalArticle article = createArticle(parameters);
		} catch (PortalException e) {
			logger.error("Error creating journalArticle", e);
		}
		
	}

	private JournalArticle createArticle(Map<String, Object> parameters) throws PortalException {
		
		User user = (User) parameters.get("user");
		long groupId = (long) parameters.get("groupId");
		String title = (String) parameters.get("title");
		long folderId = (long) parameters.get("folderId");
		long entryId = (long) parameters.get("entryId");
		
		FileEntry fileAttach = DLAppLocalServiceUtil.getFileEntry(entryId);
		
		JournalArticle article = null;
		Locale locale = LocaleUtil.getDefault();
		
		Map<Locale, String> titleMap = new HashMap<>();
		String nameContent = validTitleDescription(title);
		
		titleMap.put(locale, nameContent);
	
//		String content = replaceContentWithFields(parameters);
		String content = getContent();
		
		logger.debug("content replaced!!! " + content);
		logger.debug("Replacing content with fields!!");
		/* create service context */
		ServiceContext serviceContext = new ServiceContext();
		try {
			serviceContext.setScopeGroupId(groupId);
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setWorkflowAction(1);
			
			/* create article ID */
			Date date = new Date();
			
			long artIDL = date.getTime() + user.getUserId() + 1;
			String artID = String.valueOf(artIDL);
			/* get current Date */
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(date);
			/* create web content with image */

			article = JournalArticleLocalServiceUtil.addArticle(user.getUserId(), groupId, folderId, 0L, 0L, artID, false,
					JournalArticleConstants.VERSION_DEFAULT, titleMap, null, content, STRUCTURE_KEY, TEMPLATE_KEY,
					fileAttach.getUuid(), cal.get(Calendar.MONTH), cal.get(Calendar.DATE),
					cal.get(Calendar.YEAR), cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), 0, 0, 0, 0, 0, true, 0, 0, 0,
					0, 0, true, true, false, StringPool.BLANK, null, null, StringPool.BLANK, serviceContext);
				
			logger.debug("Journal Article created!");
		} catch (PortalException e1) {
			logger.error("Error creating journalArticle", e1);
		}

		return article;
	}

	private String getContent() {
		String str = "<?xml version='1.0'?><root available-locales='en_GB' default-locale='en_GB'>	<dynamic-element name='Separator5gcf' type='selection_break' index-type='keyword'>		<dynamic-element name='ChangeProposalReference' type='text' index-type='keyword'>			<dynamic-content language-id='en_GB'><![CDATA[Test Retail Plan AB001]]></dynamic-content>		</dynamic-element>		<dynamic-element name='ChangeProposalTitle' type='text' index-type='keyword'>			<dynamic-content language-id='en_GB'><![CDATA[Test New Retail Plan AB001]]></dynamic-content>		</dynamic-element>		<dynamic-element name='ChangeProposalStatus' type='list' index-type='keyword'>			<dynamic-content language-id='en_GB'><![CDATA[Test Service Provider Impact Assessment]]></dynamic-content>		</dynamic-element>		<dynamic-element name='ChangeProposerId' type='ddm-number' index-type='keyword'>			<dynamic-content language-id='en_GB'><![CDATA[44774]]></dynamic-content>		</dynamic-element>	</dynamic-element>	<dynamic-element name='LinkToCPPage' type='text' index-type='keyword'>		<dynamic-content language-id='en_GB'><![CDATA[https://poc-rec-portal.co.uk/group/guest/change-proposal-page-retail-plan]]></dynamic-content>	</dynamic-element>	<dynamic-element name='InitialAssessment' type='selection_break' index-type='keyword'>		<dynamic-element name='ChangeRequirements' type='selection_break' index-type='keyword'>			<dynamic-element name='ProblemStatement' type='text_box' index-type='text'>				<dynamic-content language-id='en_GB'><![CDATA[Test Retail Plan Problem]]></dynamic-content>			</dynamic-element>			<dynamic-element name='SolutionRequirements' type='text_box' index-type='text'>				<dynamic-content language-id='en_GB'><![CDATA[Test Retail Plan Solution]]></dynamic-content>			</dynamic-element>		</dynamic-element>		<dynamic-element name='ChangeProgresion' type='selection_break' index-type='keyword'>			<dynamic-element name='ChangePath' type='list' index-type='keyword'>				<dynamic-content language-id='en_GB'><![CDATA[Self-Governance]]></dynamic-content>			</dynamic-element>			<dynamic-element name='ResponsibleCommittee' type='text' index-type='keyword'>				<dynamic-content language-id='en_GB'><![CDATA[Retail Committee]]></dynamic-content>			</dynamic-element>			<dynamic-element name='PriorityStatus' type='text' index-type='keyword'>				<dynamic-content language-id='en_GB'><![CDATA[High]]></dynamic-content>			</dynamic-element>			<dynamic-element name='UrgentChange' type='list' index-type='keyword'>				<dynamic-content language-id='en_GB'><![CDATA[Yes]]></dynamic-content>			</dynamic-element>		</dynamic-element>		<dynamic-element name='AttachDocuments' type='document_library' index-type='keyword'>			<dynamic-element name='DocumentTitle' type='text' index-type='keyword'>				<dynamic-content language-id='en_GB'><![CDATA[Test constants]]></dynamic-content>			</dynamic-element>			<dynamic-content language-id='en_GB'><![CDATA[{'classPK':45908,'groupId':'20121','title':'constans.txt','type':'document','uuid':'ed59b165-518d-8e5c-e18f-ae9655afd1d8'}]]></dynamic-content>		</dynamic-element>	</dynamic-element>	<dynamic-element name='Comments' type='selection_break' index-type='keyword'>		<dynamic-element name='Deadline' type='ddm-date' index-type='keyword'>			<dynamic-content language-id='en_GB'><![CDATA[2021-02-15]]></dynamic-content>		</dynamic-element>	</dynamic-element></root>";
		return str;
	}

	private String validTitleDescription(String description) {
		if (Validator.isNotNull(description)) {
			description = description.replaceAll("\\$", Matcher.quoteReplacement("\\$"));
			description = description.replaceAll("&#039;", "'");
			description = description.replaceAll("&#034;", "\"");
			description = description.replaceAll("&amp;", "&");
		}
		return description;
	}

	private String replaceContentWithFields(Map<String, Object> parameters) throws PortalException {
		
		String CPRef = (String) parameters.get("CPRef");
		String CPTitle = (String) parameters.get("CPTitle");
		String CPStatus = (String) parameters.get("CPStatus");
		long CPProposerId = (long) parameters.get("CPProposerId");
		String CPLinkToCP = (String) parameters.get("CPLinkToCP");
		String CPProblemStatement = (String) parameters.get("CPProblemStatement");
		String CPSolutionReq = (String) parameters.get("CPSolutionReq");
		String CPChangePath = (String) parameters.get("CPChangePath");
		String CPResponsibleComittee = (String) parameters.get("CPResponsibleComittee");
		String CPPriorityStatus = (String) parameters.get("CPPriorityStatus");
		String CPUrgentChange = (String) parameters.get("CPUrgentChange");
		String CPDocumentTitle = (String) parameters.get("CPDocumentTitle");
		FileEntry CPAttachDoc = DLAppLocalServiceUtil.getFileEntry((long) parameters.get("entryId"));
		Date CPDeadline = (Date) parameters.get("CPDeadline");
		
		String finalContent = standardContent;
		
		logger.debug("Standard content read from template: " + finalContent);
		
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_REFERENCE, CPRef);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_TITLE, CPTitle);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_STATUS, CPStatus);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_PROPOSER_ID, String.valueOf(CPProposerId));
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_LINK_TO_CP_PAGE, CPLinkToCP);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_PROBLEM_STATEMENT, CPProblemStatement);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_SOLUTION_REQUIREMENTS, CPSolutionReq);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_CHANGE_PATH, CPChangePath);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_RESPONSIBLE_COMMITTEE, CPResponsibleComittee);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_PRIORITY_STATUS, CPPriorityStatus);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_URGENT_CHANGE, CPUrgentChange);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_DOCUMENT_TITLE, CPDocumentTitle);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_ATTACH_DOCUMENT, CPAttachDoc.toString());
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_DEADLINE, CPDeadline.toString());
		
		return finalContent;
	}
	
}