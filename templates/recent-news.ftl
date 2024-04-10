<#if entries?has_content>
    <div class='mx-auto grid max-w-7xl grid-cols-1 gap-x-8 gap-y-6 px-6 lg:grid-cols-2 lg:px-8'>
        <#list entries as entry>
            <#assign
                assetRenderer=entry.getAssetRenderer()
                journalArticle=assetRenderer.getAssetObject() />
            <#assign document=saxReaderUtil.read(journalArticle.getContent()) />
            <#assign urlTitle=journalArticle.getUrlTitle() />
            <#assign assetURL="/web/guest/-/" + urlTitle />
            <#assign newsTitle=journalArticle.getTitle() />
            <#assign publishDate=journalArticle.getDisplayDate() />
            <#assign formattedDate=publishDate?string("MMM dd, YYYY") />
            <#assign newsSummary=document.valueOf("//dynamic-element
                [@name='SummaryFeedsLandingPage' ]
                /dynamic-content/text()") />
            <#assign newsImage=document.valueOf("//dynamic-element
                [@name='ImageFeedsLandingPage' ]
                /dynamic-content/text()") />
            <#assign picture=jsonFactoryUtil.createJSONObject(newsImage)>
                <#if entry?index==0>
                    <article
                        class='relative isolate flex flex-col justify-end overflow-hidden rounded-2xl  bg-gray-900 px-8 pb-8 pt-80 sm:pt-48 lg:pt-80'>
                        <img
                            src='/documents/${picture.getString("groupId")}/${picture.getString("uuid")}'
                            alt=''
                            class='absolute inset-0 -z-10 h-full w-full object-cover' />
                        <div class='absolute inset-0 -z-10 bg-gradient-to-t from-gray-900 via-gray-900/40' />
                        <div class='absolute inset-0 -z-10 rounded-2xl ring-1 ring-inset ring-gray-900/10' />
                        <div class='flex flex-wrap items-center gap-y-1 overflow-hidden text-sm leading-6 text-gray-300'>
                            <time dateTime=${formattedDate} class='mr-8'>
                                ${formattedDate}
                            </time>
                            <div class='-ml-4 flex items-center gap-x-4'>
                                <svg
                                    viewBox='0 0 2 2'
                                    class='-ml-0.5 h-0.5 w-0.5 flex-none fill-white/50'>
                                    <circle cx=1 cy=1 r=1 />
                                </svg>
                                <div class='flex gap-x-2.5'>
                                    ${entry.getUserName()}
                                </div>
                            </div>
                        </div>
                        <h2 class='mt-3 text-lg font-semibold leading-6 text-white'>
                            <a href=${assetURL}>
                                <span class='absolute inset-0' />
                                ${newsTitle}
                            </a>
                        </h2>
                    </article>
                </#if>
        </#list>
        <div class='mx-auto w-full border-t border-gray-900/10 pt-4 lg:mx-0 lg:max-w-none lg:border-t-0 lg:pt-0'>
            <div class='-mt-6 grid grid-cols-1 divide-y divide-gray-900/10 md:grid-cols-2 md:gap-x-6 md:divide-y-0 lg:grid-cols-1 lg:divide-y'>
                <#list entries as entry>
                    <#assign
                        assetRenderer=entry.getAssetRenderer()
                        journalArticle=assetRenderer.getAssetObject() />
                    <#assign document=saxReaderUtil.read(journalArticle.getContent()) />
                    <#assign urlTitle=journalArticle.getUrlTitle() />
                    <#assign assetURL="/web/guest/-/" + urlTitle />
                    <#assign newsTitle=journalArticle.getTitle() />
                    <#assign publishDate=journalArticle.getDisplayDate() />
                    <#assign formattedDate=publishDate?string("MMM dd, YYYY") />
                    <#assign newsSummary=document.valueOf("//dynamic-element
                        [@name='SummaryFeedsLandingPage' ]
                        /dynamic-content/text()") />
                    <#assign newsImage=document.valueOf("//dynamic-element
                        [@name='ImageFeedsLandingPage' ]
                        /dynamic-content/text()") />
                    <#assign picture=jsonFactoryUtil.createJSONObject(newsImage)>
                        <#if entry?index !=0>
                            <article class='py-6'>
                                <div class='group relative'>
                                    <time
                                        dateTime=${formattedDate}
                                        class='block text-sm leading-6 text-gray-600'>
                                        ${formattedDate}
                                    </time>
                                    <h2 class='mt-2 text-lg font-semibold text-gray-900 group-hover:text-gray-600'>
                                        <a href=${assetURL}>
                                            <span class='absolute inset-0' />
                                            ${newsTitle}
                                        </a>
                                    </h2>
                                    <p class='mt-4 line-clamp-3 text-sm leading-6 text-gray-600 lg:line-clamp-2'>
                                        ${newsSummary}
                                    </p>
                                </div>
                                <div class='mt-4 flex gap-x-2.5'>
                                    <p
                                        class='relative flex gap-x-2.5 text-sm font-semibold leading-6 text-gray-900'>
                                        ${entry.getUserName()}
                                    </p>
                                </div>
                            </article>
                        </#if>
                </#list>
            </div>
        </div>
    </div>
</#if>