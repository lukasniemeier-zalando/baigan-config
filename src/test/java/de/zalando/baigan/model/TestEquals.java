package de.zalando.baigan.model;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestEquals {

    @Test
    public void testEval() {
        final String email = "foo@bar.com";
        final String otherCase = "FOo@bar.com";
        final String other = "baz@bar.com";
        final ConditionType conditionType = new Equals(email);

        assertThat(conditionType.eval(other), Matchers.equalTo(false));
        assertThat(conditionType.eval(email), Matchers.equalTo(true));

        assertThat(conditionType.eval(otherCase), Matchers.equalTo(true));

    }
}
