package com.everis.rec.rfi.logs.asset;

import com.everis.rec.rfilogs.model.RfiLogs;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.asset.util.AssetUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

public class RFILogsAssetRenderer extends BaseJSPAssetRenderer<RfiLogs> implements TrashRenderer {

	private final RfiLogs _entry;

	public RFILogsAssetRenderer(RfiLogs entry) {
		_entry = entry;
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return RfiLogs.class.getName();
	}

	@Override
	public long getClassPK() {
		// TODO Auto-generated method stub
		return _entry.getRfilogId();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		int abstractLength = AssetUtil.ASSET_ENTRY_ABSTRACT_LENGTH;

		if (portletRequest != null) {
			abstractLength = GetterUtil.getInteger(portletRequest.getAttribute(WebKeys.ASSET_ENTRY_ABSTRACT_LENGTH),
					AssetUtil.ASSET_ENTRY_ABSTRACT_LENGTH);
		}

		String summary = _entry.getTitle();

		if (Validator.isNull(summary)) {
			summary = HtmlUtil.stripHtml(StringUtil.shorten(_entry.getTitle(), abstractLength));
		}

		return summary;
	}

	@Override
	public String getTitle(Locale locale) {
		return _entry.getTitle();
	}

	/*
	 * @Override public String getTitle(Locale locale) {
	 * 
	 * 
	 * String key = LanguageUtil.getLanguageId(locale); Locale localbundle = new
	 * Locale.Builder().setLanguageTag(key).build();
	 * 
	 * ResourceBundle resourceBundle =
	 * _resourceBundleLoader.loadResourceBundle(locale);
	 * 
	 * ResourceBundle resourceBundle =
	 * _resourceBundleLoader.loadResourceBundle(localbundle);
	 * 
	 * return getDisplayTitle(resourceBundle, _entry); }
	 */

	public static String getDisplayTitle(ResourceBundle resourceBundle, RfiLogs entry) {

		if (Validator.isNull(entry.getTitle())) {
			return LanguageUtil.get(resourceBundle, "untitled-entry");
		}

		return entry.getTitle();
	}

	@Override
	public RfiLogs getAssetObject() {
		// TODO Auto-generated method stub
		return _entry;
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		return _entry.getOrgSiteId();
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return _entry.getUserName();
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return _entry.getUserUuid();
	}

	@Override
	public String getPortletId() {
		AssetRendererFactory<RfiLogs> assetRendererFactory = getAssetRendererFactory();

		return assetRendererFactory.getPortletId();
	}

	@Override
	public String getDiscussionPath() {
		if (PropsValues.BLOGS_ENTRY_COMMENTS_ENABLED) {
			return "edit_entry_discussion";
		} else {
			return null;
		}
	}

	@Override
	public String getType() {
		return RFILogsAssetRendererFactory.TYPE;
	}

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest, String template) {
		// TODO Auto-generated method stub
		return null;
	}

}
