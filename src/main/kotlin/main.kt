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

		twitter {
			handle = "0_alexheld"
		}

		val companyBuilder = CompanyBuilder()
		companyBuilder.name = "MegaCorp"
		companyBuilder.city = "Cologne"
		company = companyBuilder.build()
	}
}

fun ClientBuilder.twitter(c: TwitterBuilder.() -> Unit)  {
	twitter = TwitterBuilder().apply(c).build()

	/* Let's break the following statement down
	[1] TwitterBuilder().apply(c).build()
	-> fun buildTwitter(lambdaWithTwitterBuilderReceiver: TwitterBuilder.() -> Unit): Twitter {
			var builder = TwitterBuilder()
			lambdaWithTwitterBuilderReceiver(builder)
			return builder.build()
		}

	[2] twitter = TwitterBuilder().apply(c).build()
	We are currently in the context of ClientBuilder, that's why we can access getters and setters without using 'this'.
	We want to assigning the return value of [1] to ClientBuilder.setTwitter()

	It all translates into: (at compile time!!)
	this.setTwitter(buildTwitter(c))

	No magic
	*/
}
