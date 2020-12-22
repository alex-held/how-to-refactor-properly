import model.*
import java.util.function.*

fun main(args: Array<String>) {
	val client = createClientInternal("Alexander", "Held", "0_alexheld", "Cologne", "MegaCorp")
	println("Created client is: " + client.consoleString)
}

val Client.consoleString: String
	get() = "${twitter.handle} ${company.name}"


fun createClientInternal(fn: String, ln: String, twitter: String, city: String, company: String): Client {
	val builder = ClientBuilder()
	builder.firstName = fn
	builder.lastName = ln

	val twitterBuilder = TwitterBuilder()
	twitterBuilder.handle = twitter
	builder.twitter = twitterBuilder.build()

	val companyBuilder = CompanyBuilder()
	companyBuilder.city = city
	companyBuilder.name = company
	builder.company = companyBuilder.build()

	return builder.build()
}


fun createClient(c: ClientBuilder.() -> Unit): Client {
	val builder = ClientBuilder()
	c(builder)
	return builder.build()
}

fun createClient(): Client {
	return createClient {
		firstName = "Alexander"
		lastName = "Held"

		val twitterBuilder = TwitterBuilder()
		twitterBuilder.handle = "0_alexheld"
		twitter = twitterBuilder.build()

		val companyBuilder = CompanyBuilder()
		companyBuilder.name = "MegaCorp"
		companyBuilder.city = "Cologne"
		company = companyBuilder.build()
	}
}
