package com.everis.rec.listeners.journal.article;

import com.everis.cproposal.service.recFormArticleLocalService;
import com.everis.rec.listeners.constants.RecListenersPortletKeys;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ModelListener.class)
public class JournalArticleListener extends BaseModelListener<JournalArticle> {

	private static final Log logger = LogFactoryUtil.getLog(JournalArticleListener.class);

	@Override
	public void onBeforeUpdate(JournalArticle model) throws ModelListenerException {

		// assign UUID for a pretty URL
		logger.info("onBeforeUpdate... " + model.getArticleId());
		String structureName = StringPool.BLANK;
		DDMStructure str = model.getDDMStructure();
		String strName = str.getName("en_GB");
		if (Validator.isNotNull(strName) && strName.equals("NOMINATIONS-STR")) {
			structureName = checkStructureByGroupId(model);
		} else if (Validator.isNotNull(strName) && strName.equals("VOTING")) {
			structureName = checkStructureByGroupId(model);
		} else {
			structureName = checkStructure(model);
		}
		List<PortletPreferences> portPref = null;
		String layaoutUuid = StringPool.BLANK, friendlyURL = StringPool.BLANK;
		if (model.getLayoutUuid().isEmpty() && Validator.isNotNull(structureName)) {
			if (RecListenersPortletKeys.NEWS.equals(structureName)
					|| RecListenersPortletKeys.NOMINATIONS.equals(structureName)) {
				portPref = PortletPreferencesLocalServiceUtil.getPortletPreferences(model.getCompanyId(),
						model.getGroupId(), PortletKeys.PREFS_OWNER_ID_DEFAULT, PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
						"com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet", false);

			} /*
				 * else if (RecListenersPortletKeys.CATEGORY_3.equals(structureName)) { portPref
				 * =
				 * PortletPreferencesLocalServiceUtil.getPortletPreferences(model.getCompanyId()
				 * , model.getGroupId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				 * PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
				 * "com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet", false);
				 * 
				 * }
				 */

			else {
				portPref = PortletPreferencesLocalServiceUtil.getPortletPreferences(model.getCompanyId(),
						model.getGroupId(), PortletKeys.PREFS_OWNER_ID_DEFAULT, PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
						"com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet", true);

			}
			logger.debug("portPref.size()... " + portPref.size());

			switch (structureName) {
			case RecListenersPortletKeys.CHANGE_PROPOSAL_PAGE:
				friendlyURL = "/change-register";
				break;
			case RecListenersPortletKeys.IMPACT_ASSESSMENT:
				friendlyURL = "/impact-assessment";
				break;
			case RecListenersPortletKeys.CONSULTATION:
				friendlyURL = "/consultation-register";
				break;
			case RecListenersPortletKeys.PARTY_MANAGEMENT:
				friendlyURL = "/my-applications";
				break;
			case RecListenersPortletKeys.NEWS:
				friendlyURL = "/recportal";
				break;
			case RecListenersPortletKeys.NOMINATIONS:
				friendlyURL = "/nomination-register";
				break;
			case RecListenersPortletKeys.CATEGORY_3:
				friendlyURL = "/category-3";
				break;
			case RecListenersPortletKeys.COMMITTEE_ACTION_LOG:
				friendlyURL = "/action-log";
				break;
			case RecListenersPortletKeys.VOTING:
				friendlyURL = "/voting";
				break;

			}
			try {
				for (PortletPreferences portPrefSingle : portPref) {
					logger.debug(
							"LayoutLocalServiceUtil " + LayoutLocalServiceUtil.getLayout(portPrefSingle.getPlid()));
					Layout layout = LayoutLocalServiceUtil.getLayout(portPrefSingle.getPlid());
					if (layout.getFriendlyURL().equalsIgnoreCase(friendlyURL)) {
						layaoutUuid = layout.getUuid();
						logger.debug("In uuid... " + layaoutUuid);
						break;
					}
				}
			} catch (PortalException e) {
				logger.error("There is no asset publisher to get an uuid. " + e);
			}

			logger.debug("OutLayoutUuid... " + layaoutUuid);

			logger.debug("modelUuidbefore... " + model.getLayoutUuid());
			model.setLayoutUuid(layaoutUuid);
			logger.info("modelUuidafter... " + model.getLayoutUuid());
		}
		logger.info("beforeUpdate: model: - " + model.getLayoutUuid());
		super.onBeforeUpdate(model);
	}

	@Override
	public void onAfterUpdate(JournalArticle model) throws ModelListenerException {
		logger.debug("onAfterUpdate: model: - " + model.getLayoutUuid());
		logger.info("onAfterUpdate... Journal Article");
		if (model.getStatus() == 0) {

			if (isChangeProposalPageStructure(model)) {
				logger.info("JournalArticle " + model.getArticleId() + " is CHANGE PROPOSAL PAGE");
				ChangeProposalUtil cpUtil = new ChangeProposalUtil();
				cpUtil.checkUpdatesAndApplyLogic(model);
			} else if (isConsultationStructure(model)) {
				logger.info("JournalArticle " + model.getArticleId() + " is CONSULTATION PAGE");
				ConsultationUtil conUtil = new ConsultationUtil();
				conUtil.checkUpdatesAndApplyLogic(model);
			} else if (isImpactAssessmentStructure(model)) {
				logger.info("JournalArticle " + model.getArticleId() + " is IMPACT ASSESSMENT PAGE");
				ImpactAssessmentUtil conUtil = new ImpactAssessmentUtil();
				conUtil.checkUpdatesAndApplyLogic(model);
			} else if (isPartyManagementStructure(model)) {
				logger.info("JournalArticle " + model.getArticleId() + "is PARTY MANAGEMENT");
				PartyManagementUtil pmUtil = new PartyManagementUtil();
				pmUtil.checkUpdatesAndApplyLogic(model);
			} else if (isNominationStructure(model)) {
				logger.info("JournalArticle " + model.getArticleId() + "is NOMINATION");
				NominationUtil noUtil = new NominationUtil();
				noUtil.checkUpdatesAndApplyLogic(model);
			} else if (isActionLogStructure(model)) {
				logger.info("JournalArticle " + model.getArticleId() + "is COMMITTE-ACTION LOG");
				ActionLogUtil alUtil = new ActionLogUtil();
				alUtil.checkUpdatesAndApplyLogic(model);
			} else if (isVotingStructure(model)) {
				logger.info("JournalArticle " + model.getArticleId() + "is VOTING");
				VotingUtil votingUtil = new VotingUtil();
				votingUtil.checkUpdatesAndApplyLogic(model);
			}
		}
	}

	private boolean isChangeProposalPageStructure(JournalArticle model) {
		logger.debug("isChangeProposalPageStructure... starting method... ");
		List<DDMStructure> ddmStructureList = _recFormArticleLocalService.getStructuresByName(model.getCompanyId(),
				RecListenersPortletKeys.CHANGE_PROPOSAL_PAGE);
		if (ddmStructureList.size() > 0) {
			DDMStructure ddmStructure = ddmStructureList.get(0);
			logger.debug("Checking structure - ddmStructure: " + ddmStructure.getStructureKey() + " - model: "
					+ model.getDDMStructureKey());
			return ddmStructure.getStructureKey().equalsIgnoreCase(model.getDDMStructureKey()) && true;
		}
		return false;
	}

	private boolean isConsultationStructure(JournalArticle model) {
		logger.debug("isConsultation... starting method... ");
		List<DDMStructure> ddmStructureList = _recFormArticleLocalService.getStructuresByName(model.getCompanyId(),
				RecListenersPortletKeys.CONSULTATION);
		if (ddmStructureList.size() > 0) {
			DDMStructure ddmStructure = ddmStructureList.get(0);
			logger.debug("Checking structure - ddmStructure: " + ddmStructure.getStructureKey() + " - model: "
					+ model.getDDMStructureKey());
			return ddmStructure.getStructureKey().equalsIgnoreCase(model.getDDMStructureKey()) && true;
		}
		return false;
	}

	private String checkStructure(JournalArticle model) {
		String result = null;
		logger.debug("checkStructure method");
		for (String structureName : RecListenersPortletKeys.STRUCTURE_LIST) {
			List<DDMStructure> ddmStructureList = _recFormArticleLocalService.getStructuresByName(model.getCompanyId(),
					structureName);
			if (ddmStructureList.size() > 0) {
				DDMStructure ddmStructure = ddmStructureList.get(0);
				logger.debug("Checking structure - ddmStructure: " + ddmStructure.getStructureKey() + " - model: "
						+ model.getDDMStructureKey());
				if (ddmStructure.getStructureKey().equalsIgnoreCase(model.getDDMStructureKey())) {
					result = structureName;
					break;
				}
			}
		}
		return result;
	}

	private String checkStructureByGroupId(JournalArticle model) {
		String result = null;
		logger.debug("checkStructure method");
		for (String structureName : RecListenersPortletKeys.STRUCTURE_LIST) {
			List<DDMStructure> ddmStructureList = _recFormArticleLocalService
					.getStructuresByNameAndByGroupId(model.getCompanyId(), model.getGroupId(), structureName);
			if (ddmStructureList.size() > 0) {
				DDMStructure ddmStructure = ddmStructureList.get(0);
				logger.debug("Checking structure - ddmStructure: " + ddmStructure.getStructureKey() + " - model: "
						+ model.getDDMStructureKey());
				if (ddmStructure.getStructureKey().equalsIgnoreCase(model.getDDMStructureKey())) {
					result = structureName;
					break;
				}
			}
		}
		return result;
	}

	private boolean isImpactAssessmentStructure(JournalArticle model) {
		logger.debug("isImpactAssessment... starting method... ");
		List<DDMStructure> ddmStructureList = _recFormArticleLocalService.getStructuresByName(model.getCompanyId(),
				RecListenersPortletKeys.IMPACT_ASSESSMENT);
		if (ddmStructureList.size() > 0) {
			DDMStructure ddmStructure = ddmStructureList.get(0);
			logger.debug("Checking structure - ddmStructure: " + ddmStructure.getStructureKey() + " - model: "
					+ model.getDDMStructureKey());
			return ddmStructure.getStructureKey().equalsIgnoreCase(model.getDDMStructureKey()) && true;
		}
		return false;
	}

	private boolean isPartyManagementStructure(JournalArticle model) {
		logger.debug("isPartyManagementStructure... starting method... ");
		List<DDMStructure> ddmStructureList = _recFormArticleLocalService.getStructuresByName(model.getCompanyId(),
				RecListenersPortletKeys.PARTY_MANAGEMENT);
		if (ddmStructureList.size() > 0) {
			DDMStructure ddmStructure = ddmStructureList.get(0);
			logger.debug("Checking structure - ddmStructure: " + ddmStructure.getStructureKey() + " - model: "
					+ model.getDDMStructureKey());
			return ddmStructure.getStructureKey().equalsIgnoreCase(model.getDDMStructureKey()) && true;
		}
		return false;
	}

	private boolean isNominationStructure(JournalArticle model) {
		logger.debug("isNominationStructure... starting method... ");
		List<DDMStructure> ddmStructureList = _recFormArticleLocalService.getStructuresByNameAndByGroupId(
				model.getCompanyId(), model.getGroupId(), RecListenersPortletKeys.NOMINATIONS);
		if (ddmStructureList.size() > 0) {
			DDMStructure ddmStructure = ddmStructureList.get(0);
			logger.debug("Checking structure - ddmStructure: " + ddmStructure.getStructureKey() + " - model: "
					+ model.getDDMStructureKey());
			return ddmStructure.getStructureKey().equalsIgnoreCase(model.getDDMStructureKey()) && true;
		}
		return false;
	}

	private boolean isNewsStructure(JournalArticle model) {
		logger.debug("isNewsStructure... starting method... ");
		List<DDMStructure> ddmStructureList = _recFormArticleLocalService.getStructuresByName(model.getCompanyId(),
				RecListenersPortletKeys.NEWS);
		if (ddmStructureList.size() > 0) {
			DDMStructure ddmStructure = ddmStructureList.get(0);
			logger.debug("Checking structure - ddmStructure: " + ddmStructure.getStructureKey() + " - model: "
					+ model.getDDMStructureKey());
			return ddmStructure.getStructureKey().equalsIgnoreCase(model.getDDMStructureKey()) && true;
		}
		return false;
	}

	private boolean isCategory3Structure(JournalArticle model) {
		logger.debug("isCategory3Structure... starting method... ");
		List<DDMStructure> ddmStructureList = _recFormArticleLocalService.getStructuresByName(model.getCompanyId(),
				RecListenersPortletKeys.CATEGORY_3);
		if (ddmStructureList.size() > 0) {
			DDMStructure ddmStructure = ddmStructureList.get(0);
			logger.debug("Checking structure - ddmStructure: " + ddmStructure.getStructureKey() + " - model: "
					+ model.getDDMStructureKey());
			return ddmStructure.getStructureKey().equalsIgnoreCase(model.getDDMStructureKey()) && true;
		}
		return false;
	}

	private boolean isActionLogStructure(JournalArticle model) {
		logger.debug("isActionLogStructure... starting method... ");
		logger.info("model: " + model);
		List<DDMStructure> ddmStructureList = _recFormArticleLocalService.getStructuresByNameAndByGroupId(
				model.getCompanyId(), model.getGroupId(), RecListenersPortletKeys.COMMITTEE_ACTION_LOG);
		if (ddmStructureList.size() > 0) {
			DDMStructure ddmStructure = ddmStructureList.get(0);
			logger.info("Checking structure - ddmStructure: " + ddmStructure.getStructureKey() + " - model: "
					+ model.getDDMStructureKey());
			return ddmStructure.getStructureKey().equalsIgnoreCase(model.getDDMStructureKey()) && true;
		}
		return false;
	}

	private boolean isVotingStructure(JournalArticle model) {
		logger.debug("isVotingStructure... starting method... ");
		List<DDMStructure> ddmStructureList = _recFormArticleLocalService.getStructuresByNameAndByGroupId(
				model.getCompanyId(), model.getGroupId(), RecListenersPortletKeys.VOTING);
		if (ddmStructureList.size() > 0) {
			DDMStructure ddmStructure = ddmStructureList.get(0);
			logger.debug("Checking structure - ddmStructure: " + ddmStructure.getStructureKey() + " - model: "
					+ model.getDDMStructureKey());
			return ddmStructure.getStructureKey().equalsIgnoreCase(model.getDDMStructureKey()) && true;
		}
		return false;
	}

	private recFormArticleLocalService _recFormArticleLocalService;

	@Reference(unbind = "-")
	protected void setRecFormArticleLocalService(recFormArticleLocalService service) {
		_recFormArticleLocalService = service;
	}

}
