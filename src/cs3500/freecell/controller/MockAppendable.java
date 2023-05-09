package cs3500.freecell.controller;

import java.io.IOException;

/**
 * Fake Appendable class that always throws IOException.
 * Used for testing controller.
 */
public class MockAppendable implements Appendable {

  /**
   * Fake append method that throws an IOException.
   * @param csq irrelevant parameter.
   * @return Appendable never returns a value.
   * @throws IOException Appendable always throws an IOException.
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  /**
   * Fake append method that throws an IOException.
   * @param csq irrelevant parameter.
   * @param start irrelevant parameter.
   * @param end irrelevant parameter.
   * @return Appendable never returns a value.
   * @throws IOException Appendable always throws an IOException.
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }

  /**
   * Fake append method that throws an IOException.
   * @param c irrelevant parameter.
   * @return Appendable never returns a value.
   * @throws IOException Appendable always throws an IOException.
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }
}


