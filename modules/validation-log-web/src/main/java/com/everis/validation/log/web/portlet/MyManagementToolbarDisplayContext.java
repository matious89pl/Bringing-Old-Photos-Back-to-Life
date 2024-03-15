package com.everis.validation.log.web.portlet;

import com.everis.rec.validation.log.service.ValidationLogLocalServiceUtil;
import com.everis.validation.log.web.constants.ValidationLogWebPortletKeys;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

/**
 * Assigments management toolbar display context.
 *
 * This class passes contextual information to the user interface for the Clay
 * management toolbar.
 *
 * @author liferay
 */
public class MyManagementToolbarDisplayContext extends SearchContainerManagementToolbarDisplayContext {

	public MyManagementToolbarDisplayContext(HttpServletRequest httpServletRequest,
			LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse,
			SearchContainer<?> searchContainer) {
		super(httpServletRequest, liferayPortletRequest, liferayPortletResponse, searchContainer);
		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(liferayPortletRequest);
		_themeDisplay = (ThemeDisplay) httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);
	}

	@Override
	public String getClearResultsURL() {
		return getSearchActionURL();
	}

	/**
	 * Returns the action URL for the search.
	 *
	 * @return search action URL
	 */
	@Override
	public String getSearchActionURL() {

		PortletURL searchURL = liferayPortletResponse.createRenderURL();

		searchURL.setProperty("mvcRenderCommandName", ValidationLogWebPortletKeys.VIEW_LANDING);
		String navigation = ParamUtil.getString(httpServletRequest, "navigation", "entries");
		searchURL.setParameter("navigation", navigation);

		searchURL.setParameter("orderByCol", getOrderByCol());
		searchURL.setParameter("orderByType", getOrderByType());
		searchURL.setParameter("filterFileName", ParamUtil.getString(httpServletRequest, "filterFileName"));
		searchURL.setParameter("filterUploadedBy", ParamUtil.getString(httpServletRequest, "filterUploadedBy"));
		searchURL.setParameter("filterUploadedFrom", ParamUtil.getString(httpServletRequest, "filterUploadedFrom"));
		searchURL.setParameter("keywords", "");

		searchURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM,
				ParamUtil.getString(httpServletRequest, SearchContainer.DEFAULT_CUR_PARAM));

		return searchURL.toString();
	}

	/**
	 * Returns the assignment list display style.
	 * 
	 * Current selection is stored in portal preferences.
	 * 
	 * @return current display style
	 */
	public String getDisplayStyle() {
		String displayStyle = ParamUtil.getString(httpServletRequest, "displayStyle");
		if (Validator.isNull(displayStyle)) {
			displayStyle = "list";// _portalPreferences.getValue(
									// RecActivityAggregateWebPortletKeys.RECACTIVITYAGGREGATEWEB,
									// "assignments-display-style","descriptive");
		} else {
			_portalPreferences.setValue(ValidationLogWebPortletKeys.VALIDATIONLOGWEB, "assignments-display-style",
					displayStyle);
			httpServletRequest.setAttribute(WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
		}
		return displayStyle;
	}

	/**
	 * Returns the sort order column.
	 * 
	 * @return sort column
	 */
	public String getOrderByCol() {
		return ParamUtil.getString(httpServletRequest, "orderByCol", "uploadedBy");
	}

	/**
	 * Returns the sort type (ascending / descending).
	 * 
	 * @return sort type
	 */
	public String getOrderByType() {
		return ParamUtil.getString(httpServletRequest, "orderByType", "asc");
	}

	/**
	 * Returns the current sorting URL.
	 *
	 * @return current sorting portlet URL
	 *
	 * @throws PortletException
	 */
	private PortletURL _getCurrentSortingURL() throws PortletException {
		PortletURL sortingURL = PortletURLUtil.clone(currentURLObj, liferayPortletResponse);

		sortingURL.setParameter("mvcRenderCommandName", ValidationLogWebPortletKeys.VIEW_LANDING);
		// Reset current page.
		sortingURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM, "0");
		String keywords = ParamUtil.getString(httpServletRequest, "keywords");
		if (Validator.isNotNull(keywords)) {
			sortingURL.setParameter("keywords", keywords);
		}
		return sortingURL;
	}

	@Override
	public String getSearchContainerId() {
		return "validationLogs";
	}

	/**
	 * Return the option items for the sort column menu.
	 *
	 * @return options list for the sort column menu
	 */
	@Override
	protected List<DropdownItem> getOrderByDropdownItems() {
		return new DropdownItemList() {
			{
				add(dropdownItem -> {
					dropdownItem.setActive("fileName".equals(getOrderByCol()));
					dropdownItem.setHref(_getCurrentSortingURL(), "orderByCol", "fileName");
					dropdownItem.setLabel("FileName");
				});
				add(dropdownItem -> {
					dropdownItem.setActive("uploadedBy".equals(getOrderByCol()));
					dropdownItem.setHref(_getCurrentSortingURL(), "orderByCol", "uploadedBy");
					dropdownItem.setLabel("UploadedBy");
				});
				add(dropdownItem -> {
					dropdownItem.setActive("uploadedFrom".equals(getOrderByCol()));
					dropdownItem.setHref(_getCurrentSortingURL(), "orderByCol", "uploadedFrom");
					dropdownItem.setLabel("UploadedFrom");
				});
			}
		};
	}

	public Boolean isShowCreationMenu() {
		return false;
	}

	@Override
	public Boolean isShowAdvancedSearch() {
		return false;
	}

	@Override
	public Boolean isShowFiltersDoneButton() {
		return false;
	}

	@Override
	public Boolean isShowInfoButton() {
		return false;
	}

	@Override
	public Boolean isShowSearch() {
		return true;
	}

	/**
	 * Returns the view type options (card, list, table).
	 *
	 * @return list of view types
	 */
	@Override
	public List<ViewTypeItem> getViewTypeItems() {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", ValidationLogWebPortletKeys.VIEW_LANDING);
		int delta = ParamUtil.getInteger(httpServletRequest, SearchContainer.DEFAULT_DELTA_PARAM);

		if (delta > 0) {
			portletURL.setParameter("delta", String.valueOf(delta));
		}

		String orderByCol = ParamUtil.getString(httpServletRequest, "orderByCol", "uploadedBy");
		String orderByType = ParamUtil.getString(httpServletRequest, "orderByType", "asc");

		portletURL.setParameter("orderByCol", orderByCol);
		portletURL.setParameter("orderByType", orderByType);

		int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);
		if (cur > 0) {
			portletURL.setParameter("cur", String.valueOf(cur));
		}

		return new ViewTypeItemList(portletURL, getDisplayStyle()) {
			{

				addTableViewTypeItem();
			}
		};
	}

	public List<DropdownItem> getFilterDropdownItems() {
		List<String> orderByList = new ArrayList<String>();
		orderByList.add("FileName");
		orderByList.add("UploadedBy");
		orderByList.add("UploadedFrom");

		List<String> filterByName = new ArrayList<String>();
		List<String> filterUploadedBy = new ArrayList<String>();
		List<String> filterUploadedFrom = new ArrayList<String>();

		try {
			DynamicQuery dynamicQueryName = ValidationLogLocalServiceUtil.dynamicQuery();
			List<String> validationLogNameList = new ArrayList<String>();
			dynamicQueryName.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("fileName")));
			validationLogNameList = ValidationLogLocalServiceUtil.dynamicQuery(dynamicQueryName, -1, -1);
			if (Validator.isNotNull(validationLogNameList)) {
				for (int i = 0; i < validationLogNameList.size(); i++) {
					if (Validator.isNotNull(validationLogNameList.get(i))) {
						filterByName.add(validationLogNameList.get(i));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error at search menu name:" + e.getMessage());
		}

		try {
			DynamicQuery dynamicQueryUploadedBy = ValidationLogLocalServiceUtil.dynamicQuery();
			List<String> validationLogUpByList = new ArrayList<String>();
			dynamicQueryUploadedBy
					.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("uploadedBy")));
			validationLogUpByList = ValidationLogLocalServiceUtil.dynamicQuery(dynamicQueryUploadedBy, -1, -1);
			if (Validator.isNotNull(validationLogUpByList)) {
				for (int i = 0; i < validationLogUpByList.size(); i++) {
					if (Validator.isNotNull(validationLogUpByList.get(i))) {
						filterUploadedBy.add(validationLogUpByList.get(i));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error at search menu uploaded by:" + e.getMessage());
		}

		try {
			DynamicQuery dynamicQueryUploadedFrom = ValidationLogLocalServiceUtil.dynamicQuery();
			List<String> validationLogUpFromList = new ArrayList<String>();
			dynamicQueryUploadedFrom
					.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("uploadedFrom")));
			validationLogUpFromList = ValidationLogLocalServiceUtil.dynamicQuery(dynamicQueryUploadedFrom, -1, -1);
			if (Validator.isNotNull(validationLogUpFromList)) {
				for (int i = 0; i < validationLogUpFromList.size(); i++) {
					if (Validator.isNotNull(validationLogUpFromList.get(i))) {
						filterUploadedFrom.add(validationLogUpFromList.get(i));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error at search menu uploaded from:" + e.getMessage());
		}

		try {
			return getFilterDropdownItems(filterByName, filterUploadedBy, filterUploadedFrom, orderByList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("serial")
	public List<DropdownItem> getFilterDropdownItems(List<String> filterByName, List<String> filterUploadedBy,
			List<String> filterUploadedFrom, List<String> orderByList) {
		return new DropdownItemList() {
			{
				if (filterByName.size() > 0) {
					addGroup(dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(addDropdowFilterNameMenu(filterByName));
						dropdownGroupItem.setLabel("Filter by name");
						dropdownGroupItem.setActive(false);
					});

				}

				if (filterUploadedBy.size() > 0) {
					addGroup(dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(addDropdowFilterUploadedByMenu(filterUploadedBy));
						dropdownGroupItem.setLabel("Filter by uploaded by");
						dropdownGroupItem.setActive(false);
					});

				}

				if (filterUploadedFrom.size() > 0) {
					addGroup(dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(addDropdowFilterUploadedFromMenu(filterUploadedFrom));
						dropdownGroupItem.setLabel("Filter by uploaded from");
						dropdownGroupItem.setActive(false);
					});

				}

				if (orderByList.size() > 0) {
					addGroup(dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(addDropdowOrderByMenu(orderByList));
						dropdownGroupItem.setLabel("Order by name");
						dropdownGroupItem.setActive(false);
					});
				}
			}
		};
	}

	public DropdownItemList addDropdowFilterNameMenu(List<String> filterList) throws PortletException {
		DropdownItemList list = new DropdownItemList();
		for (int i = 0; i < filterList.size(); i++) {
			String filter = filterList.get(i);
			DropdownItem dropdownItem = new DropdownItem();
			dropdownItem.setLabel(filter);
			dropdownItem.setActive(false);
			if (StringUtil.equalsIgnoreCase(filter, ParamUtil.getString(httpServletRequest, "filterFileName"))) {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterFileName", "none", "redirect",
						currentURLObj.toString());
			} else {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterFileName", filter, "redirect",
						currentURLObj.toString());
			}
			dropdownItem.setActive(
					StringUtil.equalsIgnoreCase(filter, ParamUtil.getString(httpServletRequest, "filterFileName")));
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}
		return list;
	}

	public DropdownItemList addDropdowFilterUploadedByMenu(List<String> filterList) throws PortletException {
		DropdownItemList list = new DropdownItemList();
		for (int i = 0; i < filterList.size(); i++) {
			String filter = filterList.get(i);
			DropdownItem dropdownItem = new DropdownItem();
			dropdownItem.setLabel(filter);
			dropdownItem.setActive(false);
			if (StringUtil.equalsIgnoreCase(filter, ParamUtil.getString(httpServletRequest, "filterUploadedBy"))) {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterUploadedBy", "none", "redirect",
						currentURLObj.toString());
			} else {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterUploadedBy", filter, "redirect",
						currentURLObj.toString());
			}
			dropdownItem.setActive(
					StringUtil.equalsIgnoreCase(filter, ParamUtil.getString(httpServletRequest, "filterUploadedBy")));
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}
		return list;
	}

	public DropdownItemList addDropdowFilterUploadedFromMenu(List<String> filterList) throws PortletException {
		DropdownItemList list = new DropdownItemList();
		for (int i = 0; i < filterList.size(); i++) {
			String filter = filterList.get(i);
			DropdownItem dropdownItem = new DropdownItem();
			dropdownItem.setLabel(filter);
			dropdownItem.setActive(false);
			if (StringUtil.equalsIgnoreCase(filter, ParamUtil.getString(httpServletRequest, "filterUploadedFrom"))) {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterUploadedFrom", "none", "redirect",
						currentURLObj.toString());
			} else {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterUploadedFrom", filter, "redirect",
						currentURLObj.toString());
			}
			dropdownItem.setActive(
					StringUtil.equalsIgnoreCase(filter, ParamUtil.getString(httpServletRequest, "filterUploadedFrom")));
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}
		return list;
	}

	public DropdownItemList addDropdowOrderByMenu(List<String> validationLogOrderList) throws PortletException {
		DropdownItemList list = new DropdownItemList();
		for (int i = 0; i < validationLogOrderList.size(); i++) {
			String name = validationLogOrderList.get(i);
			DropdownItem dropdownItem = new DropdownItem();
			if (StringUtil.equalsIgnoreCase(name, ParamUtil.getString(httpServletRequest, "orderByCol"))) {
				dropdownItem.setHref(_getCurrentSortingURL(), "orderByCol", "none", "redirect",
						currentURLObj.toString());
			} else {
				dropdownItem.setHref(_getCurrentSortingURL(), "orderByCol", name, "redirect", currentURLObj.toString());
			}
			dropdownItem.setActive(
					StringUtil.equalsIgnoreCase(name, ParamUtil.getString(httpServletRequest, "orderByCol")));
			dropdownItem.setLabel(name);
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}
		return list;
	}

	private final PortalPreferences _portalPreferences;
	private final ThemeDisplay _themeDisplay;

}
