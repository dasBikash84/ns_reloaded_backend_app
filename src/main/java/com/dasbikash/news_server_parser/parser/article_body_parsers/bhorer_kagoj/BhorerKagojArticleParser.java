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

package com.dasbikash.news_server_parser.parser.article_body_parsers.bhorer_kagoj;

import com.dasbikash.news_server_parser.parser.article_body_parsers.ArticleBodyParser;
import com.dasbikash.news_server_parser.utils.DisplayUtils;
import org.jsoup.select.Elements;

public class BhorerKagojArticleParser extends ArticleBodyParser {
    private static final String TAG = "BBArticleLoader";

    private final String mSiteBaseAddress = "http://www.bhorerkagoj.com";

    @Override
    protected String getSiteBaseAddress() {
        return mSiteBaseAddress;
    }

    @Override
    protected Elements getArticleFragmentBlocks() {
        return mDocument.select(BhorerKagojArticleParserInfo.ARTICLE_FRAGMENT_BLOCK_SELECTOR);
    }

    @Override
    protected String getParagraphImageCaptionSelector() {
        return null;
    }

    @Override
    protected String getArticleModificationDateString() {

        Elements dateStringElements =
                mDocument.select(BhorerKagojArticleParserInfo.ARTICLE_MODIFICATION_DATE_STRING_SELECTOR);
        //Log.d(TAG, "parseArticle: dateStringElements.size(): "+dateStringElements.size());
        if (dateStringElements != null && dateStringElements.size()==1) {
            //Log.d(TAG, "parseArticle: dateText: "+document.select(".detail-poauthor li").get(0).text().trim());
            String dateString = dateStringElements.get(0).text().trim();
            //Log.d(TAG, "parseArticle: init dateString:"+dateString);
            dateString = DisplayUtils.banglaToEnglishDateString(dateString);
            //Log.d(TAG, "parseArticle: after conversion:"+dateString);

            dateString = (dateString.split(BhorerKagojArticleParserInfo.DATE_STRING_SPLITTER_REGEX))[0].
                    replace(BhorerKagojArticleParserInfo.ARTICLE_MODIFICATION_DATE_CLEANER_SELECTOR,
                            BhorerKagojArticleParserInfo.ARTICLE_MODIFICATION_DATE_CLEANER_REPLACEMENT).
                    trim();
            return dateString;
        }
        return null;
    }

    @Override
    protected String[] getArticleModificationDateStringFormats() {
        return new String[]{BhorerKagojArticleParserInfo.ARTICLE_MODIFICATION_DATE_STRING_FORMATS};
    }

    @Override
    protected String getFeaturedImageCaptionSelector() {
        return BhorerKagojArticleParserInfo.FEATURED_IMAGE_CAPTION_SELECTOR;
    }

    @Override
    protected String getFeaturedImageLinkSelectorAttr() {
        return BhorerKagojArticleParserInfo.FEATURED_IMAGE_LINK_SELECTOR;
    }

    @Override
    protected String getFeaturedImageSelector() {
        return BhorerKagojArticleParserInfo.FEATURED_IMAGE_SELECTOR;
    }

    @Override
    protected String getParagraphImageSelector() {
        return BhorerKagojArticleParserInfo.PARAGRAPH_IMAGE_SELECTOR;
    }

    @Override
    protected String getFeaturedImageCaptionSelectorAttr() {
        return null;
    }

    @Override
    protected String getParagraphImageLinkSelectorAttr() {
        return BhorerKagojArticleParserInfo.PARAGRAPH_IMAGE_LINK_SELECTOR_ATTR;
    }

    @Override
    protected String getParagraphImageCaptionSelectorAttr() {
        return BhorerKagojArticleParserInfo.PARAGRAPH_IMAGE_CAPTION_SELECTOR_ATTR;
    }
}
