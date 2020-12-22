import model.*
import org.junit.jupiter.api.Test
import java.util.function.*
import kotlin.test.*

internal class MainKtTest {

	private val defaultClient = Client("Alexander", "Held", Company("MegaCorp", "Cologne"), Twitter("0_alexheld"))

	private fun createConsumer(): Consumer<ClientBuilder> {
		return Consumer<ClientBuilder> {
			it.firstName = "Alexander"
			it.lastName = "Held"
			it.twitter = Twitter("0_alexheld")
			it.company = Company("MegaCorp", "Cologne")
		}
	}

	@Test
	fun createClient_should_return_client_with_same_values_when_providing_consumer() {
		val expected = defaultClient

		val actual = createClient {
			createConsumer().accept(this)
		}

		assertEquals(expected.firstName, actual.firstName)
		assertEquals(expected.lastName, actual.lastName)
		assertEquals(expected.twitter.handle, actual.twitter.handle)
		assertEquals(expected.company.name, actual.company.name)
		assertEquals(expected.company.city, actual.company.city)
	}

	@Test
	fun createClient_should_return_client_with_same_values() {
		val expected = defaultClient
		val actual = createClient()

		assertEquals(expected.firstName, actual.firstName)
		assertEquals(expected.lastName, actual.lastName)
		assertEquals(expected.twitter.handle, actual.twitter.handle)
		assertEquals(expected.company.name, actual.company.name)
		assertEquals(expected.company.city, actual.company.city)
	}

	@Test
	fun consoleString_should_return_correct_description() {
		val expected = "0_alexheld MegaCorp"
		val subject = defaultClient
		val actual = subject.consoleString

		assertEquals(expected, actual)
	}
}
