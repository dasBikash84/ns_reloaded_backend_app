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

package com.dasbikash.news_server_parser.parser.preview_page_parsers.prothom_alo

internal object ProthomaloPreviewPageParserInfo {

    @JvmField val ARTICLE_PREVIEW_BLOCK_CONTAINER_SELECTOR = ".row"

    @JvmField val ARTICLE_PREVIEW_BLOCK_SELECTOR = ".each.col_in"

    @JvmField val ARTICLE_LINK_ELEMENT_SELECTOR = ".link_overlay"
    @JvmField val ARTICLE_LINK_TEXT_SELECTOR_TAG = "href"

    @JvmField val ARTICLE_PREVIEW_IMAGE_LINK_ELEMENT_SELECTOR = ".image img"
    @JvmField val ARTICLE_PREVIEW_IMAGE_LINK_TEXT_SELECTOR_TAG = "src"

    @JvmField val ARTICLE_TITLE_ELEMENT_SELECTOR = ".title"

    @JvmField val ARTICLE_PUBLICATION_DATE_ELEMENT_SELECTOR = ".time.aitm"
    @JvmField val ARTICLE_PUBLICATION_DATE_TEXT_SELECTOR_TAG = "data-published"

    @JvmField val ARTICLE_PUBLICATION_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZ"
}