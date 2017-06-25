package test.categorytest;

import org.junit.Test;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import test.utils.MatchParams;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
@Category(test.categorytest.categorytest.class)
@IncludeCategory(test.categorytest.categorytest.class)
public class ParameterizedTestIT {

	private String stringA;
	private String stringB;
	private String expected;

	// controleer bestaande waarden zoek op stringA, stringB.
	// expected is uitgelezen naam.
	public ParameterizedTestIT(String stringA, String stringB, String expected) {
		this.stringA = stringA;
		this.stringB = stringB;
		this.expected = expected;
	}

	@Parameters(name = "{index}: testValidate({0} {1}) == {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "Belgium", "januari", "Belgium" },
				{ "Afganistan", "maart", "Afganistan" }, { "China", "mei", "China" },
				{ "Duitsland", "juni", "Duitsland" }, { "Griekenland", "juli", "Griekenland" } });
	}

	@Test
	@IncludeCategory(test.categorytest.categorytest.class)
	public void test_validate() {
		assertThat(MatchParams.matchParams(stringA, stringB), is(true));
	}

}