package com.fisk.twig.config

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

import java.util.ArrayList
import java.util.Arrays

@RunWith(value = Parameterized::class)
class PropertyNameTest(val propertyTestDefinition: PropertyTestDefinition) {
    /**
     * Associates a [Property] with its expected attributes to ensure stability and backwards compatibility
     */
    class PropertyTestDefinition(val property: Property, val expectedPropertyName: String)

    @Test
    fun testPropertyNameBackwardsCompatibility() {
        Assert.assertEquals("Error in " +
                propertyTestDefinition.property.name +
                ".\n\tPersisted property name changed.  This will mess up user preferences without some sort of migration strategy.\n\n",
                propertyTestDefinition.expectedPropertyName,
                propertyTestDefinition.property.stringName)
    }

    companion object {

        internal val PROPERTY_TEST_DEFINITIONS = Arrays.asList(
                PropertyTestDefinition(Property.AUTO_GENERATE_CLOSE_TAG, "TwigEnableAutoGenerateCloseTag"),
                PropertyTestDefinition(Property.FORMATTER, "TwigFormatter"),
                PropertyTestDefinition(Property.AUTO_COLLAPSE_BLOCKS, "TwigAutoCollapseBlocks"),
                PropertyTestDefinition(Property.COMMENTER_LANGUAGE_ID, "TwigCommenterLanguageId"),
                PropertyTestDefinition(Property.AUTOCOMPLETE_STATEMENTS, "TwigAutocompleteStatements"),
                PropertyTestDefinition(Property.SHOULD_OPEN_HTML, "TwigShouldOpenHtmlAsTwig"))

        @Parameterized.Parameters
        fun parameters(): List<Array<Any>> {
            val testParameters = ArrayList<Array<Any>>()
            for (propertyTestDefinition in PROPERTY_TEST_DEFINITIONS) {
                testParameters.add(arrayOf(propertyTestDefinition))
            }

            return testParameters
        }
    }
}