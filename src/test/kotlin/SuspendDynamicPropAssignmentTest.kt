@file:OptIn(ExperimentalCoroutinesApi::class)

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SuspendDynamicPropAssignmentTest {
    suspend fun getId(): Int {
        delay(100)
        return 42
    }

    suspend fun setId(obj: dynamic) {
        obj.id = getId()

        // This also doesn't work:
        // obj["id"] = getId()

        // This is fine:
        // val id = getId()
        // obj.id = id
    }

    @Test
    fun testSetId() = runTest {
        val obj = js("{}")
        setId(obj)
        assertEquals(42, obj.id)
    }
}
