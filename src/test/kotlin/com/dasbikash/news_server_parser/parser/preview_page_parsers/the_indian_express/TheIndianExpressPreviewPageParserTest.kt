package com.dasbikash.news_server_parser.parser.preview_page_parsers.the_indian_express

import com.dasbikash.news_server_parser.database.DbSessionManager
import com.dasbikash.news_server_parser.model.EntityClassNames
import com.dasbikash.news_server_parser.model.Newspaper
import com.dasbikash.news_server_parser.parser.NEWS_PAPER_ID
import com.dasbikash.news_server_parser.parser.preview_page_parsers.PreviewPageParser
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TheIndianExpressPreviewPageParserTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun readFirstPageArticles() {

        val hql = "FROM ${EntityClassNames.NEWSPAPER}"
        val query = DbSessionManager.getNewSession().createQuery(hql,Newspaper::class.java)
        val newsPapers = query.list() as List<Newspaper>

        newsPapers.filter {
            it.id == NEWS_PAPER_ID.THE_INDIAN_EXPRESS.id
        }.map {
            println("Np: ${it.name}")
            println("Page: ${it.pageList?.get(0)?.name}")
            it.pageList?.filter {
                it.hasData()
            }?.get(0)
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