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


fun createClient(c: Consumer<ClientBuilder>): Client {
	val builder = ClientBuilder()
	c.accept(builder)
	return builder.build()
}

fun createClientOriginal(): Client {

	val builder = ClientBuilder()
	builder.firstName = "Alexander"
	builder.lastName = "Held"

	val twitterBuilder = TwitterBuilder()
	twitterBuilder.handle = "0_alexheld"
	builder.twitter = twitterBuilder.build()

	val companyBuilder = CompanyBuilder()
	companyBuilder.city = "Cologne"
	companyBuilder.name = "MegaCorp"
	builder.company = companyBuilder.build()

	return builder.build()
}
