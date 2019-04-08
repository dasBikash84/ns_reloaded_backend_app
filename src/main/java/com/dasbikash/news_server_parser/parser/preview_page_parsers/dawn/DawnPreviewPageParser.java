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

package com.dasbikash.news_server_parser.parser.preview_page_parsers.dawn;

import com.dasbikash.news_server_parser.parser.preview_page_parsers.PreviewPageParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class DawnPreviewPageParser extends PreviewPageParser {

    //*private static final String TAG = "StackTrace";
    private static final String TAG = "DawnEdLoader";

    private final String mSiteBaseAddress = "https://www.dawn.com";

    @Override
    protected String getSiteBaseAddress() {
        return mSiteBaseAddress;
    }

    @Override
    protected String getArticlePublicationDatetimeFormat() {
        return mCurrentPage.getLinkVariablePartFormat();
    }

    @Override
    protected Elements getPreviewBlocks() {
        return mDocument.select(DawnPreviewPageParserInfo.ARTICLE_PREVIEW_BLOCK_SELECTOR);
    }

    @Override
    protected String getArticleLink(Element previewBlock) {
        return previewBlock.select(DawnPreviewPageParserInfo.ARTICLE_LINK_ELEMENT_SELECTOR).get(0).
                                    attr(DawnPreviewPageParserInfo.ARTICLE_LINK_TEXT_SELECTOR_TAG);
    }

    @Override
    protected String getArticlePreviewImageLink(Element previewBlock) {
        return null;
    }

    @Override
    protected String getArticleTitle(Element previewBlock) {
        return previewBlock.select(DawnPreviewPageParserInfo.ARTICLE_TITLE_ELEMENT_SELECTOR).
                                    get(0).text();
    }

    @Override
    protected String getArticlePublicationDateString(Element previewBlock) {
        return mPageLink.substring(mPageLink.length()-mCurrentPage.getLinkVariablePartFormat().length());
    }

}