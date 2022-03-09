package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {
    @Test
    public void whenOneRightInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"10"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Some question....");
        assertThat(selected, is(10));
    }

    @Test
    public void whenOneStrRightInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"10"}
        );
        ValidateInput input = new ValidateInput(out, in);
        String selected = input.askStr("Some question....");
        assertThat(selected, is("10"));
    }

    @Test
    public void whenWrongInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"a", "b"}
        );
        ValidateInput input = new ValidateInput(out, in);
        String selected = input.askStr("Some question....");
        System.out.println(out);
        assertThat(selected, is("a"));
    }

    @Test
    public void whenOneInvalidInputOneRightInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
        System.out.println("out " + out);
    }

    @Test
    public void whenMultiRightInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"1", "2", "4"}
        );
        ValidateInput input = new ValidateInput(out, in);
        String[] expected = new String[]{"1", "2", "4"};
        String[] result = new String[]{input.askStr("Enter menu:"),
                input.askStr("Enter menu:"),
                input.askStr("Enter menu:")};

        assertThat(result, is(expected));
    }
}