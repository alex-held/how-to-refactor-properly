import model.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Test
import kotlin.test.*

internal class MainKtTest {

	@Test
	fun createClientInternal() {
		val expected = Client("Alexander", "Held", Company("MegaCorp", "Cologne"), Twitter("0_alexheld"))

		val result = createClientInternal(
			expected.firstName,
			expected.lastName,
			expected.twitter.handle,
			expected.company.city,
			expected.company.name
		)

		assertEquals(expected.firstName, result.firstName)
		assertEquals(expected.lastName, result.lastName)
		assertEquals(expected.twitter.handle, result.twitter.handle)
		assertEquals(expected.company.name, result.company.name)
		assertEquals(expected.company.city, result.company.city)
	}
}
