/*
 * Copyright 2019 www.dasbikash.org. All rights reserved.
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

package com.dasbikash.news_server_parser.model

import javax.persistence.*

@Entity
@Table(name = "pages")
data class Page(
        @Id
        var id: String="",

        @ManyToOne(targetEntity = Newspaper::class,fetch = FetchType.EAGER)
        @JoinColumn(name="newsPaperId")
        var newspaper: Newspaper?=null,

        var parentPageId: String?=null,
        var name: String?=null,

        @Column(name="linkFormat", columnDefinition="text")
        var linkFormat:String? = null,

        var linkVariablePartFormat:String? = "page_num",
        var firstEditionDateString:String? = null,
        var weekly:Boolean = false,
        var weeklyPublicationDay:Int? = 0,
        var active: Boolean = true,

        @OneToMany(fetch = FetchType.EAGER,mappedBy = "page",targetEntity = Article::class)
        var articleList: List<Article>?=null

){
    companion object {
        @JvmField
        val TOP_LEVEL_PAGE_PARENT_ID = "PAGE_ID_0"
    }
}

//is_weekly,weekly_pub_day,link_format,link_variable_part_format,first_edition_date_string