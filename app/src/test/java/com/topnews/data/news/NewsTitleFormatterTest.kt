package com.topnews.data.news

import org.hamcrest.Matchers.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsTitleFormatterTest {

    @Test
    fun testFormatTitle() {
        // usual case
        val title1 = "News from the world - BBC.com"
        val titleFormatted1 = NewsTitleFormatter.formatTitle(title1)
        assertThat(titleFormatted1, `is`(not(title1)))
        assertThat(titleFormatted1, not(containsString(" - BBC.com")))

        // make sure it doesn't remove ordinal dashes
        val title2 = "self-motivating dog .asd masom daps m"
        val titleFormatted2 = NewsTitleFormatter.formatTitle(title2)
        assertThat(titleFormatted2, `is`(title2))

        // make sure second-type dash is also removed
        val title3 = "News from the world — BBC.com"
        val titleFormatted3 = NewsTitleFormatter.formatTitle(title3)
        assertThat(titleFormatted3, `is`(not(title3)))
        assertThat(titleFormatted3, not(containsString(" — BBC.com")))
    }
}