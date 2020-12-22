import model.*
import org.junit.jupiter.api.Test
import kotlin.test.*

internal class MainKtTest {

	private fun createSubject(
		fn: String? = null,
		ln: String? = null,
		twitter: String? = null,
		city: String? = null,
		company: String? = null,
	): Client = Client(fn ?: "Alexander", ln ?: "Held",
		Company(company ?: "MegaCorp", city ?: "Cologne"),
		Twitter(twitter ?: "0_alexheld"))


	@Test
	fun createClientInternal_should_return_client_with_same_values() {
		val expected = createSubject()

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

	@Test
	fun consoleString_should_return_correct_description(){
		val expected = "0_alexheld MegaCorp"
		val subject = createSubject()

		val actual = subject.consoleString

		assertEquals(expected, actual)
	}
}
