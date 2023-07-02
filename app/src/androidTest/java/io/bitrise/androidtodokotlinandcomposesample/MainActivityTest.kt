// Compose UI Test tutorial: https://developer.android.com/jetpack/compose/testing
package io.bitrise.androidtodokotlinandcomposesample

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import io.bitrise.androidtodokotlinandcomposesample.ui.theme.AndroidToDoKotlinAndComposeSampleTheme
import org.junit.Test
import org.junit.Rule

class MainActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    val addTaskButton = hasText("Add Task") and hasClickAction()
    val tasksList = hasTestTag("TasksList")

    @Test
    fun addingAnItem() {
        // Start the app
        composeTestRule.setContent {
            AndroidToDoKotlinAndComposeSampleTheme {
                TodoApp()
            }
        }

        // given: we type in a new task
        composeTestRule.onNodeWithText("New Task")
            .performTextInput("Add a basic UI test to the app")
        // and click the "Add Task" button
        composeTestRule.onNode(addTaskButton).performClick()

        // then the task is added to the "Not Done Yet" list
        composeTestRule.onNode(
            hasText("Add a basic UI test to the app") and hasAnyAncestor(tasksList)
        ).assertExists()
    }

//    @Test
//    fun exampleFailingTest_addingAnItem() {
//        // Start the app
//        composeTestRule.setContent {
//            AndroidToDoKotlinAndComposeSampleTheme {
//                TodoApp()
//            }
//        }
//
//        // given: we type in a new task
//        composeTestRule.onNodeWithText("New Task")
//            .performTextInput("Add a basic UI test to the app")
//        // and click the "Add Task" button
//        composeTestRule.onNode(addTaskButton).performClick()
//
//        // then the task is added to the "Not Done Yet" list
//        composeTestRule.onNode(
//            hasText("Add a basic UI test to the app") and hasAnyAncestor(tasksList)
//        ).assertDoesNotExist()
//    }

    @Test
    fun completingAnItem() {
        // Start the app
        composeTestRule.setContent {
            AndroidToDoKotlinAndComposeSampleTheme {
                TodoApp()
            }
        }

        // given: we type in a new task
        composeTestRule.onNodeWithText("New Task")
            .performTextInput("Add a basic UI test to the app")
        // and click the "Add Task" button
        composeTestRule.onNode(addTaskButton).performClick()
        // and we click the checkbox on the item to mark it complete
        composeTestRule.onNode(
            hasTestTag("checkbox-Add a basic UI test to the app")
        ).performClick()

        // then the task should no longer be listed on the "Not done yet" tab
        composeTestRule.onNode(
            hasText("Add a basic UI test to the app") and hasAnyAncestor(tasksList)
        ).assertDoesNotExist()

        // instead it should be on the "Done" tab
        // switch over to the "Done" tab and check it again
        composeTestRule.onNodeWithText("Done").performClick()
        composeTestRule.onNode(
            hasText("Add a basic UI test to the app") and hasAnyAncestor(tasksList)
        ).assertExists()
    }
}