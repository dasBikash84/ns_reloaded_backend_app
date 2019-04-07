package com.dasbikash.news_server_parser.parser.preview_page_parsers.the_daily_star

import com.dasbikash.news_server_parser.model.EntityClassNames
import com.dasbikash.news_server_parser.model.Newspaper
import com.dasbikash.news_server_parser.parser.NEWS_PAPER_ID
import com.dasbikash.news_server_parser.parser.preview_page_parsers.PreviewPageParser
import com.dasbikash.news_server_parser.utils.DbSessionManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TheDailyStarPreviewPageParserTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }


    @Test
    fun readFirstPageArticles() {

        val hql = "FROM ${EntityClassNames.NEWSPAPER} where active=true"
        val query = DbSessionManager.getNewSession().createQuery(hql)
        val newsPapers = query.list() as List<Newspaper>

        newsPapers.filter {
            it.id == NEWS_PAPER_ID.THE_DAILY_STAR.id
        }.map {
            println("Np: ${it?.name}")
            println("Page: ${it.pageList?.get(0)}")
            it.pageList?.filter {
                it.linkFormat != null
            }?.first()
        }.map {
            it?.let {
                PreviewPageParser.parsePreviewPageForArticles(it, 1)
                        ?.forEach {
                            println("Article  ${it}")
                        }
            }
        }

    }
}