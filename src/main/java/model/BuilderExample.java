package model;

public class BuilderExample {

	public Client foo() {

		var builder = new ClientBuilder();
		builder.setFirstName("Alexander");
		builder.setLastName("Held");

		var twitterBuilder = new TwitterBuilder();
		twitterBuilder.setHandle("0_alexheld");
		var twitter = twitterBuilder.build();
		builder.setTwitter(twitter);

		var companyBuilder = new CompanyBuilder();
		companyBuilder.setName("MegaCorp");
		companyBuilder.setCity("Cologne");
		var company = companyBuilder.build();
		builder.setCompany(company);

		return builder.build();
	}
}
