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

package com.dasbikash.news_server_parser.utils

import com.dasbikash.news_server_parser.database.DatabaseUtils
import com.dasbikash.news_server_parser.model.Page
import org.hibernate.Session
import java.io.File
import java.util.*

object ReportGenerationUtils {

    fun getTableHeader():String{
        return "Sl,Page Name,Page Id,Parent Page Id,Newspaper,Article Download Count"
    }

    fun prepareDailyReport(reportFilePath:String, today: Date, session: Session){
        if (File(reportFilePath).exists()){
            File(reportFilePath).delete()
        }
        val reportFile = File(reportFilePath)

        val yesterDay = Calendar.getInstance()
        yesterDay.time = today
        yesterDay.add(Calendar.DAY_OF_YEAR,-1)


        reportFile.appendText("Date: ${DateUtils.getDateStringForDb(yesterDay.time)}\n\n")

        reportFile.appendText("Of Today:\n\n")
        reportFile.appendText("${getTableHeader()}\n")

        val pages = DatabaseUtils.getAllPages(session)

        var sln = 0
        var totalArticleCountOfYesterday = 0
        pages.asSequence().filter { it.hasData() }.sortedBy { it.newspaper!!.name!! }.forEach {
            val articleCountOfYesterday = DatabaseUtils.getArticleCountForPageOfYesterday(session,it,today)
            totalArticleCountOfYesterday += articleCountOfYesterday
            reportFile.appendText("${++sln},${it.name},${it.id},${it.parentPageId},${it.newspaper!!.name},${articleCountOfYesterday}\n")
        }
        reportFile.appendText(",,,,Total,${totalArticleCountOfYesterday}\n\n\n")

        addFromBeginningReport(reportFile, pages, session)
    }

    fun prepareWeeklyReport(reportFilePath:String, today: Date, session: Session){
        if (File(reportFilePath).exists()){
            File(reportFilePath).delete()
        }
        val reportFile = File(reportFilePath)

        val lastWeekFirstDay = Calendar.getInstance()
        lastWeekFirstDay.time = today
        lastWeekFirstDay.add(Calendar.DAY_OF_YEAR,-7)

        val lastWeekLastDay = Calendar.getInstance()
        lastWeekLastDay.time = today
        lastWeekLastDay.add(Calendar.DAY_OF_YEAR,-1)


        reportFile.appendText("Week: ${DateUtils.getDateStringForDb(lastWeekFirstDay.time)} to " +
                                    "${DateUtils.getDateStringForDb(lastWeekLastDay.time)}\n\n")

        reportFile.appendText("Last week's Report:\n\n")
        reportFile.appendText("${getTableHeader()}\n")

        val pages = DatabaseUtils.getAllPages(session)

        var sln = 0
        var totalArticleCountOfLastWeek = 0
        pages.asSequence().filter { it.hasData() }.sortedBy { it.newspaper!!.name!! }.forEach {
            val articleCountOfLastWeek = DatabaseUtils.getArticleCountForPageOfLastWeek(session,it,today)
            totalArticleCountOfLastWeek += articleCountOfLastWeek
            reportFile.appendText("${++sln},${it.name},${it.id},${it.parentPageId},${it.newspaper!!.name},${articleCountOfLastWeek}\n")
        }
        reportFile.appendText(",,,,Total,${totalArticleCountOfLastWeek}\n\n\n")

        addFromBeginningReport(reportFile, pages, session)
    }

    fun prepareMonthlyReport(reportFilePath:String, today: Date, session: Session){
        if (File(reportFilePath).exists()){
            File(reportFilePath).delete()
        }
        val reportFile = File(reportFilePath)

        val firstDayOfLastMonth = DateUtils.getFirstDayOfLastMonth(today)

        reportFile.appendText("Month: ${DateUtils.getYearMonthStr(firstDayOfLastMonth)}\n\n")

        reportFile.appendText("Last month’s Report:\n\n")
        reportFile.appendText("${getTableHeader()}\n")

        val pages = DatabaseUtils.getAllPages(session)

        var sln = 0
        var totalArticleCountOfLastMonth = 0
        pages.asSequence().filter { it.hasData() }.sortedBy { it.newspaper!!.name!! }.forEach {
            val articleCountOfLastMonth = DatabaseUtils.getArticleCountForPageOfLastMonth(session,it,today)
            totalArticleCountOfLastMonth += articleCountOfLastMonth
            reportFile.appendText("${++sln},${it.name},${it.id},${it.parentPageId},${it.newspaper!!.name},${articleCountOfLastMonth}\n")
        }
        reportFile.appendText(",,,,Total,${totalArticleCountOfLastMonth}\n\n\n")

        addFromBeginningReport(reportFile, pages, session)
    }

    private fun addFromBeginningReport(reportFile: File, pages: List<Page>, session: Session) {
        var sln = 0
        reportFile.appendText("From Beginning:\n\n")
        reportFile.appendText("${getTableHeader()}\n")

        sln = 0
        var totalArticleCountFromBeginning = 0
        pages.asSequence().filter { it.hasData() }.sortedBy { it.newspaper!!.name!! }.forEach {
            val articleCountFromBeginning = DatabaseUtils.getArticleCountForPage(session, it.id)
            totalArticleCountFromBeginning += articleCountFromBeginning
            reportFile.appendText("${++sln},${it.name},${it.id},${it.parentPageId},${it.newspaper!!.name},${articleCountFromBeginning}\n")
        }
        reportFile.appendText(",,,,Total,${totalArticleCountFromBeginning}")
    }
}