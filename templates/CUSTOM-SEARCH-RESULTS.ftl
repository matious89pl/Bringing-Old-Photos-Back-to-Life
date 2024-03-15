<#assign dlFileService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService") />
<#assign dlFolderService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFolderLocalService") />
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />

<div class="search-total-label">
    ${languageUtil.format(locale, "x-results-for-x", [searchContainer.getTotal(), "<strong>" + htmlUtil.escape(searchResultsPortletDisplayContext.getKeywords()) + "</strong>"], false)}
</div>
<div class="display-list">
    <ul class="list-group" id="search-results-display-list">
        <#if entries?has_content>
            <#list entries as entry>
                <#assign contentVisible = true />
                <#attempt>
                <#if entry.getClassName() == "com.liferay.document.library.kernel.model.DLFileEntry">
                    <#assign dlFile = dlFileService.getDLFileEntry(entry.getClassPK()) />
                    <#assign dlFolder = dlFile.getFolderId() />
                    <#assign operationalFolder = dlFolderService.fetchFolder(themeDisplay.getLayout().getGroupId(), 0, "Operational Contacts") />
                    <#if operationalFolder.getFolderId() == dlFolder>
                        <#assign contentVisible = false />
                        <#assign user = userService.getUser(themeDisplay.getUserId())>		
                        <#assign orgList = user.getOrganizations() />
                        <#list orgList as organization>
                             <#assign expando = organization.getExpandoBridge() />
                             <#assign expandoList = expando.getAttributeNames() />
                             <#assign name = "Rec User Type" />
                             <#if expando.getAttribute(name)?? >
                                <#assign userTypes = expando.getAttribute(name) />
                                <#list userTypes as typeString>
                                    <#if typeString == "REC Party" || typeString == "RECCo" || typeString == "REC Code Manager">
                                        <#assign contentVisible = true />
                                    </#if>
                                </#list>
                            </#if>
                        </#list>
                    </#if>
                 </#if>
                <#recover>
                </#attempt>
                <#if contentVisible>
                <li class="list-group-item list-group-item-flex">
                    <#if !entry.isTemporarilyUnavailable()>
                        <div class="autofit-col">
                            <#if entry.isThumbnailVisible()>
                                <span class="sticker">
                                    <span class="sticker-overlay">
                                        <img
                                            alt="${languageUtil.get(locale, "thumbnail")}"
                                            class="sticker-img"
                                            src="${entry.getThumbnailURLString()}"
                                        />
                                    </span>
                                </span>
                            <#elseif entry.isUserPortraitVisible() && stringUtil.equals(entry.getClassName(), userClassName)>
                                <@liferay_ui["user-portrait"] userId=entry.getAssetEntryUserId() />
                            <#elseif entry.isIconVisible()>
                                <span class="sticker sticker-rounded sticker-secondary sticker-static">
                                    <@clay.icon symbol="${entry.getIconId()}" />
                                </span>
                            </#if>
                        </div>
                        <div class="autofit-col autofit-col-expand">
                            <section class="autofit-section">
                                <div class="list-group-title">
                                    <#if entry.getClassName() == "com.liferay.document.library.kernel.model.DLFileEntry">
                                        <#assign dlFile = dlFileService.getDLFileEntry(entry.getClassPK()) />
                                        <#assign filePreviewURL = themeDisplay.getPortalURL() + "/c/document_library/get_file?uuid=" + dlFile.getUuid() + "&groupId=" + themeDisplay.getScopeGroupId() />
                                        <#assign fileDownloadURL = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + dlFile.getGroupId() + "/" + dlFile.getFolderId() + "/"
                                        + dlFile.getTitle() + "/" + dlFile.getUuid() + "?download=true" />
                                        <a href="${filePreviewURL}" target="_blank">
                                            ${entry.getHighlightedTitle()}
                                        </a>
                                        <a href="${fileDownloadURL}" style="outline: none;">
                                            <svg class="lexicon-icon lexicon-icon-download inline-item" role="presentation" viewBox="0 0 512 512">
                                                <use xlink:href="${themeDisplay.getPortalURL()}/o/admin-theme/images/clay/icons.svg#download">
                                                </use>
                                            </svg>
                                        </a>

                                    <#elseif entry.getClassName() == "com.liferay.knowledge.base.model.KBArticle"> <#--Fix to build KB link URL with code-->

                                        <#assign authTokenUtil = staticUtil["com.liferay.portal.kernel.security.auth.AuthTokenUtil"] />
                                        <#assign authToken = authTokenUtil.getToken(request) />
										<#assign KBArticleSearchUrl = themeDisplay.getPortalURL() + "/search/-/knowledge_base_article/0000/"+ entry.getClassPK() + "/maximized?p_p_auth=" + authToken />

                                        <a href="${KBArticleSearchUrl}">
                                            ${entry.getHighlightedTitle()}
                                        </a>
                                    <#else>
                                        <a href="${entry.getViewURL()}">
                                            ${entry.getHighlightedTitle()}
                                        </a>
                                    </#if>
                                </div>
                                <div class="search-results-metadata">
                                    <p class="list-group-subtext">
                                        <#if entry.isModelResourceVisible()>
                                            <span class="subtext-item">
                                                <strong>${entry.getModelResource()}</strong>
                                            </span>
                                        </#if>
                                        <#if entry.isLocaleReminderVisible()>
                                            <@liferay_ui["icon"]
                                                icon="../language/${entry.getLocaleLanguageId()}"
                                                message=entry.getLocaleReminder()
                                            />
                                        </#if>
                                        <#if entry.isCreatorVisible()>
                                            <span class="subtext-item">
                                                &#183;
                                                Created
                                                <@liferay.language key="written-by" />
                                                <strong>${htmlUtil.escape(entry.getCreatorUserName())}</strong>
                                            </span>
                                        </#if>
                                        <#if entry.isCreationDateVisible()>
                                            <span class="subtext-item">
                                                <@liferay.language key="on-date" />
                                                ${entry.getCreationDateString()}
                                            </span>
                                        </#if>
                                    </p>
                                    <#if entry.isContentVisible()>
                                        <p class="list-group-subtext">
                                            <span class="subtext-item">
                                                ${entry.getContent()}
                                            </span>
                                        </p>
                                    </#if>
                                    <#if entry.isFieldsVisible()>
                                        <p class="list-group-subtext">
                                            <#assign separate = false />
                                            <#list entry.getFieldDisplayContexts() as fieldDisplayContext>
                                                <#if separate>
                                                    &#183;
                                                </#if>
                                                <span class="badge">${fieldDisplayContext.getName()}</span>
                                                <span>${fieldDisplayContext.getValuesToString()}</span>
                                                <#assign separate = true />
                                            </#list>
                                        </p>
                                    </#if>
                                    <#if entry.isAssetCategoriesOrTagsVisible()>
                                        <div class="h6 search-document-tags text-default">
                                            <@liferay_asset["asset-tags-summary"]
                                                className=entry.getClassName()
                                                classPK=entry.getClassPK()
                                                paramName=entry.getFieldAssetTagNames()
                                                portletURL=entry.getPortletURL()
                                            />
                                            <@liferay_asset["asset-categories-summary"]
                                                className=entry.getClassName()
                                                classPK=entry.getClassPK()
                                                paramName=entry.getFieldAssetCategoryIds()
                                                portletURL=entry.getPortletURL()
                                            />
                                        </div>
                                    </#if>
                                    <#if entry.isDocumentFormVisible()>
                                        <div class="expand-details h6 text-default">
                                            <span class="list-group-text" style="">
                                                <a href="javascript:;">
                                                    <@liferay.language key="details" />...
                                                </a>
                                            </span>
                                        </div>
                                        <div class="hide search-results-list table-details table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th class="key-column">
                                                            <@liferay.language key="key" />
                                                        </th>
                                                        <th>
                                                            <@liferay.language key="value" />
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <#list entry.getDocumentFormFieldDisplayContexts() as fieldDisplayContext>
                                                        <tr>
                                                            <td class="key-column table-details-content">
                                                                <strong>${htmlUtil.escape(fieldDisplayContext.getName())}</strong>
                                                            </td>
                                                            <td class="table-details-content">
                                                                <code>
                                                                    ${fieldDisplayContext.getValuesToString()}
                                                                </code>
                                                            </td>
                                                        </tr>
                                                    </#list>
                                                </tbody>
                                            </table>
                                        </div>
                                    </#if>
                                </div>
                            </section>
                        </div>
                    <#else>
                        <div class="autofit-col">
                            <div class="alert alert-danger">
                                <@liferay.language_format
                                    arguments="result"
                                    key="is-temporarily-unavailable"
                                />
                            </div>
                        </div>
                    </#if>
                </li>
                </#if>
            </#list>
        </#if>
    </ul>
</div>
<@liferay_aui.script use="aui-base">
    A.one('#search-results-display-list').delegate(
        'click',
        function(event) {
            var currentTarget = event.currentTarget;
            currentTarget.siblings('.search-results-list').toggleClass('hide');
        },
        '.expand-details'
    );
</@liferay_aui.script>
