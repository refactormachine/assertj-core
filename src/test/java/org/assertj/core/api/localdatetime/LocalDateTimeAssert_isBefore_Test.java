/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2018 the original author or authors.
 */
package org.assertj.core.api.localdatetime;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * @author Paweł Stawicki
 * @author Joel Costigliola
 * @author Marcin Zajączkowski
 */
public class LocalDateTimeAssert_isBefore_Test extends LocalDateTimeAssertBaseTest {

  @Test
  public void test_isBefore_assertion() {
    // WHEN
    assertThat(BEFORE).isBefore(REFERENCE);
    // THEN
    verify_that_isBefore_assertion_fails_and_throws_AssertionError(REFERENCE, REFERENCE);
    verify_that_isBefore_assertion_fails_and_throws_AssertionError(AFTER, REFERENCE);
  }

  @Test
  public void test_isBefore_assertion_error_message() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> assertThat(LocalDateTime.of(2000, 1, 5, 3, 0, 5)).isBefore(LocalDateTime.of(1998, 1, 1, 3, 3, 3)))
                                                   .withMessage(format("%nExpecting:%n  <2000-01-05T03:00:05>%nto be strictly before:%n  <1998-01-01T03:03:03>"));
  }

  @Test
  public void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      LocalDateTime actual = null;
      assertThat(actual).isBefore(LocalDateTime.now());
    }).withMessage(actualIsNull());
  }

  @Test
  public void should_fail_if_dateTime_parameter_is_null() {
    assertThatIllegalArgumentException().isThrownBy(() -> assertThat(LocalDateTime.now()).isBefore((LocalDateTime) null))
                                        .withMessage("The LocalDateTime to compare actual with should not be null");
  }

  @Test
  public void should_fail_if_dateTime_as_string_parameter_is_null() {
    assertThatIllegalArgumentException().isThrownBy(() -> assertThat(LocalDateTime.now()).isBefore((String) null))
                                        .withMessage("The String representing the LocalDateTime to compare actual with should not be null");
  }

  private static void verify_that_isBefore_assertion_fails_and_throws_AssertionError(LocalDateTime dateToTest,
                                                                                     LocalDateTime reference) {
    assertThatThrownBy(() -> assertThat(dateToTest).isBefore(reference)).isInstanceOf(AssertionError.class);
    assertThatThrownBy(() -> assertThat(dateToTest).isBefore(reference.toString())).isInstanceOf(AssertionError.class);
  }

}
