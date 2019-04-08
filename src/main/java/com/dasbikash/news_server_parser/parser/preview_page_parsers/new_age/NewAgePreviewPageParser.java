/*
 * Copyright 2019 das.bikash.dev@gmail.com. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dasbikash.news_server_parser.parser.preview_page_parsers.new_age;


import com.dasbikash.news_server_parser.parser.preview_page_parsers.PreviewPageParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class NewAgePreviewPageParser extends PreviewPageParser {

    private static final String TAG = "StackTrace";
    //private static final String TAG = "NewAgePreviewPageParser";

    private final String mSiteBaseAddress = "http://www.newagebd.net";

    @Override
    protected String getPageLink() {
        mCurrentPageNumber = (mCurrentPageNumber-1)* NewAgePreviewPageParserInfo.ARTICLE_PREVIEW_COUNT_PER_PAGE;
        return super.getPageLink();
    }

    @Override
    protected String getSiteBaseAddress() {
        return mSiteBaseAddress;
    }

    @Override
    protected String getArticlePublicationDatetimeFormat() {
        return NewAgePreviewPageParserInfo.ARTICLE_PUBLICATION_DATE_TIME_FORMAT;
    }

    @Override
    protected Elements getPreviewBlocks() {
        //Log.d(TAG, "getPreviewBlocks: Page: "+mPageLink);
        Elements articleBlockContainers =
                mDocument.select(NewAgePreviewPageParserInfo.ARTICLE_PREVIEW_BLOCK_CONTAINER);
        if (articleBlockContainers.size()>0){
            Elements previewBlocks = articleBlockContainers.get(0).select(
                    NewAgePreviewPageParserInfo.ARTICLE_PREVIEW_BLOCK_SELECTOR
            );
            return previewBlocks;
        }
        return null;
    }

    @Override
    protected String getArticleLink(Element previewBlock) {
        return previewBlock.select(NewAgePreviewPageParserInfo.ARTICLE_LINK_ELEMENT_SELECTOR).get(0).
                                    attr(NewAgePreviewPageParserInfo.ARTICLE_LINK_TEXT_SELECTOR_TAG);
    }

    @Override
    protected String getArticlePreviewImageLink(Element previewBlock) {
        return previewBlock.select(NewAgePreviewPageParserInfo.ARTICLE_PREVIEW_IMAGE_LINK_ELEMENT_SELECTOR).get(0).
                                    attr(NewAgePreviewPageParserInfo.ARTICLE_PREVIEW_IMAGE_LINK_TEXT_SELECTOR_TAG);
    }

    @Override
    protected String getArticleTitle(Element previewBlock) {
        String articleTitle = previewBlock.select(NewAgePreviewPageParserInfo.ARTICLE_TITLE_ELEMENT_SELECTOR).
                                    get(0).text();
        return articleTitle;
    }

    @Override
    protected String getArticlePublicationDateString(Element previewBlock) {

        String fullDateString = previewBlock.select(
                NewAgePreviewPageParserInfo.ARTICLE_PUBLICATION_DATE_ELEMENT_SELECTOR).
                get(0).text();

        String dateString =
                fullDateString.replaceAll(
                        NewAgePreviewPageParserInfo.ARTICLE_PUBLICATION_DATE_TIME_CLEANER_REGEX,
                        NewAgePreviewPageParserInfo.ARTICLE_PUBLICATION_DATE_TIME_REPLACER
                );
        return dateString;
    }
}