package pl.kwolska.playground;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FailingTest {

  @Test
  public void failTest() {
    assertThat(1).isEqualTo(0);
  }

}
