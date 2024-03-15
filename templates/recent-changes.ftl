<#if entries?has_content>
    <div class='space-y-5'>
        <#list entries as curEntry>
            <#assign
                assetRenderer=curEntry.getAssetRenderer()
                journalArticle=assetRenderer.getAssetObject() />
            <#assign document=saxReaderUtil.read(journalArticle.getContent()) />
            <#assign urlTitle=journalArticle.getUrlTitle() />
            <#assign assetURL="/group/guest/-/" + urlTitle />
            <#assign valueCPUrgent=document.valueOf("//dynamic-element
                [@name='UrgentChange' ]
                /dynamic-content/text()") />
            <#assign valueCPTitle=journalArticle.getTitle() />
            <#assign valueCPReference=document.valueOf("//dynamic-element
                [@name='ChangeProposalReference' ]
                /dynamic-content/text()") />
            <#assign valueCPStatus=document.valueOf("//dynamic-element
                [@name='ChangeProposalStatus' ]
                /dynamic-content/text()") />
            <#assign valueCPSummary=document.valueOf("//dynamic-element
                [@name='ChangeSummary' ]
                /dynamic-content/text()") />
            <#assign lastModifiedDate=journalArticle.getModifiedDate() />
            <#-- Format the date. Adjust the date format string as needed -->
                <#assign formattedDate=lastModifiedDate?string("yyyy-MM-dd") />
                <#-- Get the display date of the article -->
                    <#assign publishDate=journalArticle.getDisplayDate() />
                    <#-- Extract the day and format the month -->
                        <#assign day=publishDate?string("d") />
                        <#assign month=publishDate?string("MMM")?upper_case />
                        <div class="flex items-center justify-between">
                            <div class="flex items-center">
                                <div class="flex h-20 w-20 flex-shrink-0 flex-col items-center justify-center rounded-lg border border-gray-300/40 bg-gray-100"><span class="text-4xl font-semibold">
                                        ${day}
                                    </span><span class="border-teal-500 mt-1 w-full border-t pt-1 text-center text-sm">
                                        ${month}
                                    </span></div>
                                <div class="ml-4"><a href="${assetURL}" class="font-bold hover:underline">
                                        ${valueCPReference}
                                        <!-- --> -
                                        <!-- -->
                                        ${valueCPTitle}
                                    </a>
                                    <p class="line-clamp-3">
                                        ${valueCPSummary}
                                    </p>
                                    <div class="mt-2 flex items-center justify-between">
                                        <p class="flex h-6 items-center rounded-full bg-teal-100 px-3 text-[0.5rem]
 font-semibold text-teal-700"></p>
                                        <p class="mt-0.5 rounded-md bg-teal-50 px-1.5 py-0.5 text-xs font-medium text-teal-700 ring-1 ring-inset ring-teal-600/20">
                                            ${valueCPStatus}
                                        </p>
                                        <div class="flex items-center space-x-1"><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-4 w-4 text-gray-500">
                                                <path stroke-linecap="round" stroke-linejoin="round" d="M16.023 9.348h4.992v-.001M2.985 19.644v-4.992m0 0h4.992m-4.993 0l3.181 3.183a8.25 8.25 0 0013.803-3.7M4.031 9.865a8.25 8.25 0 0113.803-3.7l3.181 3.182m0-4.991v4.99"></path>
                                            </svg>
                                            <p class="text-xs">
                                                ${formattedDate}
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div><a href="${assetURL}" class="flex items-center hover:text-teal-600"><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-5 w-5">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 4.5l7.5 7.5-7.5 7.5"></path>
                                </svg></a>
                        </div>
        </#list>
    </div>
</#if>